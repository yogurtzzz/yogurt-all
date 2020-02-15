package game;

import lombok.Getter;
import lombok.Setter;
import yogurt.data_structure.base.Pair;
import yogurt.data_structure.queue.PriorityQueue;
import yogurt.data_structure.stack.Stack;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Maze extends JPanel {

	static class Animation extends Thread{
		Maze maze;
		public Animation(Maze maze) {
			this.maze = maze;
		}
		@Override
		public void run() {
			while(true){
				try{
					boolean finish = maze.step();
					if (finish)
						break;
					Thread.sleep(10);
				}catch (Exception e){
					e.printStackTrace();
					break;
				}

			}
		}
	}

	@Getter
	@Setter
	static class Point{
		private int x;
		private int y;
		//下面4个变量，为true表示该方向可通行，为false表示该方向是墙壁
		private boolean accessUp;
		private boolean accessDown;
		private boolean accessLeft;
		private boolean accessRight;
		private boolean visited; // used to create maze
		private Point parent;  //used to find the solution
		public Point(int x,int y){
			this.x = x;
			this.y = y;
		}
	}

	@Getter
	@Setter
	static class Weight implements Comparable{
		//用于 A星 寻路，找出指定2点之间的最短路径
		private int g; //从起点到当前点，已经走了多少步
		private int h; //从当前点到终点，还剩多少步
		private int f; //f = g + h
		private Point point;
		public Weight(Point point,int g,int h){
			this.point = point;
			this.g = g;
			this.h = h;
			this.f = g + h;
		}
		public Weight(Point point){
			this.point = point;
		}

		@Override
		public int compareTo(Object o) {
			return f - ((Weight)o).getF();
		}

		public boolean equals(Object o){
			if (this == o)
				return true;
			if (o == null)
				return false;
			if (!(o instanceof Weight))
				return false;
			Weight other = (Weight)o;
			boolean flag = point.getX() == other.getPoint().getX()
					&& point.getY() == other.getPoint().getY();
			return flag;
		}
		public int hashCode(){
			return Objects.hash(point.getX(),point.getY());
		}
	}

	enum Direction{
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
	enum Level{
		EASY(20),
		MEDIUM(40),
		HARD(60);

		private int scale;
		Level(int scale){
			this.scale = scale;
		}

		public int getScale() {
			return scale;
		}
	}
	static class MyButton extends JButton{
		public MyButton(String text,int fontSize){
			setFont(new Font("仿宋",Font.BOLD,fontSize));
			setVerticalAlignment(SwingConstants.TOP);
			setVerticalTextPosition(SwingConstants.CENTER);
			setHorizontalAlignment(SwingConstants.CENTER);
			setHorizontalTextPosition(SwingConstants.CENTER);
			/** 点击时不发生样式的改变 **/
			setFocusPainted(false);
			setText(text);
		}
	}

	static class Ball{
		Maze maze;
		int x;
		int y;
		int boundX;
		int boundY;
		int ballSize;
		Color ballColor = Color.YELLOW;
		public Ball(int x,int y,int boundX,int boundY,int ballSize,Maze maze){
			this.x = x;
			this.y = y;
			this.ballSize = ballSize;
			this.boundX = boundX;
			this.boundY = boundY;
			this.maze = maze;
		}
		public void moveRight(){
			Point p = maze.mazeMatrix[x][y];
			if (!p.accessRight)
				return ;
			++x;
			fixWhenOut();
		}
		public void moveLeft(){
			Point p = maze.mazeMatrix[x][y];
			if (!p.accessLeft)
				return ;
			--x;
			fixWhenOut();
		}
		public void moveUp(){
			Point p = maze.mazeMatrix[x][y];
			if (!p.accessUp)
				return ;
			y--;
			fixWhenOut();
		}
		public void moveDown(){
			Point p = maze.mazeMatrix[x][y];
			if (!p.accessDown)
				return ;
			y++;
			fixWhenOut();
		}
		private void fixWhenOut(){
			if (x > boundX)
				x = boundX;
			else if (x < 0)
				x = 0;
			if (y > boundY)
				y = boundY;
			else if (y < 0)
				y = 0;
		}
	}

	static final Random RAND = new Random();
	static final String COMMAND_RANDOM_GENERATE = "RANDOM_GEN";
	static final String COMMAND_STEP_GENERATE = "STEP_GEN";
	static final String COMMAND_SHOW_WAY_OUT = "SHOW_WAY_OUT_GEN";
	static final String COMMAND_START_GAME = "START_GAME";
	static final int EASY_SCALE = 20;
	static final int MID_SCALE = 40;
	static final int HARD_SCALE = 60;

	Frame parentFrame;
	int scale;
	int cellSize;
	boolean showBall;
	Ball ball;
	Point[][] mazeMatrix;
	List<Point> mazeList;
	List<Point> shortestRoute;
	boolean showRoute;
	boolean mazeCreated;
	boolean win;
	int width = 820;
	int height = 820;
	int margin = 10;
	Level level;

	Stack<Point> stackUsedToStep;
	Point pointUsedToStep;

	public Maze(int scale){
		init(scale);
		setFocusable(true);
		setSize(new Dimension(width,height));
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("PRESSED");
				if (ball == null) return;
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_LEFT){
					ball.moveLeft();
				}else if (keyCode == KeyEvent.VK_RIGHT){
					if (ball.x == scale - 1 && ball.y == scale - 1){
						UIManager.put("OptionPane.buttonFont",new FontUIResource(new Font("仿宋",Font.PLAIN,25)));
						UIManager.put("OptionPane.messageFont",new FontUIResource(new Font("仿宋",Font.PLAIN,25)));
						JOptionPane.showMessageDialog(
								parentFrame,
								"You win!",
								"Congratulation",
								JOptionPane.INFORMATION_MESSAGE);
					}
					ball.moveRight();
				}else if (keyCode == KeyEvent.VK_UP){
					ball.moveUp();
				}else if (keyCode == KeyEvent.VK_DOWN){
					ball.moveDown();
				}
				repaint();
			}
		});
	}

	public void randomGenerate() {
		startGeneration();
	}

	public void stepGenerate() {
		Animation animation = new Animation(this);
		animation.start();
	}
	public void startGame(){
		if (!mazeCreated){
			warn();
			return ;
		}
		this.showBall = true;
		this.showRoute = false;
		this.shortestRoute = null;
		this.ball = new Ball(0,0,scale - 1,scale - 1,cellSize/2,this);
		repaint();
		//repaint后，需要重新获取焦点，以使得KeyListener生效
		requestFocus();
	}
	private void warn(){
		UIManager.put("OptionPane.buttonFont",new FontUIResource(new Font("仿宋",Font.PLAIN,25)));
		UIManager.put("OptionPane.messageFont",new FontUIResource(new Font("仿宋",Font.PLAIN,25)));
		JOptionPane.showMessageDialog(
				parentFrame,
				"Maze have not been created !",
				"Warn",
				JOptionPane.WARNING_MESSAGE);
	}
	public void showWayOut(){
		if (!mazeCreated){
			warn();
			return ;
		}
		showRoute = !showRoute;
		boolean showAnimation = true;
		if (showRoute){
			if (!showAnimation)
				shortestRoute = AStarSolution();
			else {
				// 以动画形式展示路径
				List<Point> temp = AStarSolution();
				Queue<Point> queue = new LinkedList<>();
				temp.forEach(queue::offer);
				shortestRoute = new ArrayList<>();
				shortestRoute.add(queue.poll());
				new Thread(() -> {
					try{
						while (!queue.isEmpty()){
							int delay = level == Level.EASY ? 80 : level == Level.MEDIUM ? 30 : 5;
							shortestRoute.add(queue.poll());
							repaint();
							Thread.sleep(delay);
						}
					}catch (Exception e){
						e.printStackTrace();
						return;
					}
				}).start();
			}
		}else {
			shortestRoute = null;
		}
	}

	public void init(int scale){
		this.scale = scale;
		this.mazeCreated = false;
		this.showBall = false;
		this.ball = null;
		this.win = false;
		this.showRoute = false;
		this.shortestRoute = null;
		if (scale <= 20)
			this.level = Level.EASY;
		else if (scale <= 40)
			this.level = Level.MEDIUM;
		else
			this.level = Level.HARD;
		cellSize = (width - 2 * margin) / scale;
		initMaze(scale,scale);
		pointUsedToStep = mazeMatrix[0][0];
		stackUsedToStep = new Stack<>();
	}

	private List<Point> generateList(Point[][] mazeMatrix,int width,int height){
		List<Point> list = new ArrayList<>();
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				list.add(mazeMatrix[i][j]);
		return list;
	}

	//this method will be invoked after the constructor
	@Override
	public void paintComponent(Graphics g) {
		//若要设置背景颜色，先set一下，再调super.paintComponent
		setBackground(new Color(179,179,179));
		super.paintComponent(g);
		for (int i = 0; i <= scale; i++)
			g.drawLine(margin + i * cellSize,margin,margin + i * cellSize,margin + scale * cellSize);

		for (int i = 0; i <= scale; i++)
			g.drawLine(margin,margin + i * cellSize,margin + scale * cellSize,margin + i * cellSize);

		// delete the wall
		paintTheWall(g);
		if (showBall){
			g.setColor(ball.ballColor);
			g.fillOval(margin + ball.x * cellSize + cellSize/2 - ball.ballSize/2,margin + ball.y * cellSize + cellSize/2 - ball.ballSize/2,ball.ballSize,ball.ballSize);
		}
		if (showRoute){
			g.setColor(new Color(102,255,0));
			Point pre = shortestRoute.get(0);
			int i = 0;
			while (++i < shortestRoute.size()){
				Point post = shortestRoute.get(i);
				if (pre == mazeMatrix[0][0]){
					g.drawLine(margin,margin + cellSize/2,margin + cellSize/2,margin + cellSize/2);
				}
				/** 将2个cell的中心点连接起来 **/
				int startX = margin + pre.getX()*cellSize + cellSize/2;
				int startY = margin + pre.getY()*cellSize + cellSize/2;
				int endX = margin + post.getX()*cellSize + cellSize/2;
				int endY = margin + post.getY()*cellSize + cellSize/2;
				g.drawLine(startX,startY,endX,endY);
				pre = post;
			}
			//draw the last line
			if (shortestRoute.contains(mazeMatrix[scale - 1][scale - 1])){
				g.drawLine(margin + (scale - 1) * cellSize + cellSize/2,
						margin + (scale - 1) * cellSize + cellSize/2,
						margin + scale * cellSize,
						margin + (scale - 1) * cellSize + cellSize/2);
			}
		}
	}
	private void paintTheWall(Graphics g){
		g.setColor(this.getBackground());
		for (Point p : mazeList){
			if (p.accessLeft)
				g.drawLine(margin + p.getX() * cellSize,
						margin + p.getY() * cellSize,
						margin + p.getX() * cellSize,
						margin + p.getY() * cellSize + cellSize);
			if (p.accessRight)
				g.drawLine(margin + p.getX() * cellSize + cellSize,
						margin + p.getY() * cellSize,
						margin + p.getX() * cellSize + cellSize,
						margin + p.getY() * cellSize + cellSize);
			if (p.accessUp)
				g.drawLine(margin + p.getX() * cellSize,
						margin + p.getY() * cellSize,
						margin + p.getX() * cellSize + cellSize,
						margin + p.getY() * cellSize);
			if (p.accessDown)
				g.drawLine(margin + p.getX() * cellSize,
						margin + p.getY() * cellSize + cellSize,
						margin + p.getX() * cellSize + cellSize,
						margin + p.getY() * cellSize + cellSize);
		}
	}

	/** maze array
	 * 0 1 2 3 4 5 6 7 8 9
	 * 1 * * * * * * * * *
	 * 2 * * * * * * * * *
	 * 3 * * * * * * * * *
	 * 4 * * * * * * * * *
	 * 5 * * * * * * * * *
	 * 6 * * * * * * * * *
	 * 7 * * * * * * * * *
	 * 8 * * * * * * * * *
	 * 9 * * * * * * * * *
	 *
	 * **/
	private void initMaze(int width, int height){
		Point[][] maze = new Point[width][height];
		List<Point> list = new ArrayList<>();
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				maze[i][j] = new Point(i,j);
				list.add(maze[i][j]);
			}
		}
		mazeMatrix = maze;
		mazeList = list;
	}
	private void startGeneration(){
		mazeMatrix[0][0].setAccessLeft(true);
		mazeMatrix[scale - 1][scale - 1].setAccessRight(true);
		Stack<Point> visited = new Stack<>();
		Point current = mazeMatrix[0][0];
		current.setVisited(true);
		while (!isAllPointsVisited(mazeList)){
			//first get the neighbor points that haven't been visited
			List<Point> neighbors = getNeighborCells(current,mazeMatrix,scale,scale)
					.stream()
					.filter(p -> !p.isVisited())
					.collect(Collectors.toList());
			// if the neighbor points which haven't been visited exists
			if (neighbors.size() > 0) {
				//then randomly select one point from the neighbor points
				Point select = randomSelect(neighbors);
				visited.push(current);
				removeTheWall(current, select);
				select.setVisited(true);
				current = select;
			}else if (!visited.isEmpty()){
				current = visited.pop();
			}
		}
		this.mazeCreated = true;
	}

	private boolean step(){
		pointUsedToStep.setVisited(true);
		boolean finish = isAllPointsVisited(mazeList);
		if (!finish){
			//first get the neighbor points that haven't been visited
			List<Point> neighbors = getNeighborCells(pointUsedToStep,mazeMatrix,scale,scale)
					.stream()
					.filter(p -> !p.isVisited())
					.collect(Collectors.toList());
			// if the neighbor points which haven't been visited exists
			if (neighbors.size() > 0) {
				//then randomly select one point from the neighbor points
				Point select = randomSelect(neighbors);
				stackUsedToStep.push(pointUsedToStep);
				removeTheWall(pointUsedToStep, select);
				select.setVisited(true);
				pointUsedToStep = select;
			}else if (!stackUsedToStep.isEmpty()){
				pointUsedToStep = stackUsedToStep.pop();
			}
		}else {
			mazeMatrix[0][0].setAccessLeft(true);
			mazeMatrix[scale - 1][scale - 1].setAccessRight(true);
			this.mazeCreated = true;
		}
		repaint();
		return finish;
	}

	public void findTheWayOutAStar(Point[][] maze){
		int width = maze.length;
		int height = maze[0].length;

	}

	private void removeTheWall(Point a,Point b){
		Direction direction = relativeDirection(a,b);
		switch (direction){
			case LEFT:
				a.setAccessRight(true);
				b.setAccessLeft(true);
				//System.out.println("("+a.getX()+","+a.getY()+")的右墙,("+b.getX()+","+b.getY()+")的左墙");
				break;
			case RIGHT:
				a.setAccessLeft(true);
				b.setAccessRight(true);
				//System.out.println("("+a.getX()+","+a.getY()+")的左墙,("+b.getX()+","+b.getY()+")的右墙");
				break;
			case UP:
				a.setAccessDown(true);
				b.setAccessUp(true);
				//System.out.println("("+a.getX()+","+a.getY()+")的下墙,("+b.getX()+","+b.getY()+")的上墙");
				break;
			case DOWN:
				a.setAccessUp(true);
				b.setAccessDown(true);
				//System.out.println("("+a.getX()+","+a.getY()+")的上墙,("+b.getX()+","+b.getY()+")的下墙");
				break;
			default:
				//System.out.println("Point A and B are not adjacent");
				break;
		}
	}
	/** return the relative direction of a to b **/
	private Direction relativeDirection(Point a,Point b){
		int aX,aY,bX,bY;
		aX = a.getX();
		aY = a.getY();
		bX = b.getX();
		bY = b.getY();
		if (aX == bX - 1 && aY == bY)
			return Direction.LEFT;
		if (aX == bX + 1 && aY == bY)
			return Direction.RIGHT;
		if (aX == bX && aY == bY - 1)
			return Direction.UP;
		if (aX == bX && aY == bY + 1)
			return Direction.DOWN;
		return null;
	}
	private boolean isAllPointsVisited(List<Point> list){
		return list.stream().allMatch(Point::isVisited);
	}
	private Point randomSelect(List<Point> points){
		int size = points.size();
		int pick = RAND.nextInt(size);
		return points.get(pick);
	}

	private List<Point> getNeighborCells(Point p,Point[][] maze,int width,int height){
		if (p == null)
			return null;
		int posX = p.getX();
		int posY = p.getY();
		Pair<Integer,Integer> leftPoint = new Pair<>(posX - 1,posY);
		Pair<Integer,Integer> rightPoint = new Pair<>(posX + 1,posY);
		Pair<Integer,Integer> upPoint = new Pair<>(posX,posY + 1);
		Pair<Integer,Integer> downPoint = new Pair<>(posX,posY - 1);
		List<Pair<Integer,Integer>> pairs = Arrays.asList(leftPoint,rightPoint,upPoint,downPoint)
				.stream()
				.filter(pair -> isPointValid(pair,width,height))
				.collect(Collectors.toList());
		return getPointsFromPair(pairs,maze);

	}
	private boolean isPointValid(Pair<Integer,Integer> pointPos,int width,int height){
		return pointPos.getLeft() >= 0 && pointPos.getLeft() < width
				&& pointPos.getRight() >= 0 && pointPos.getRight() < height;
	}
	private List<Point> getPointsFromPair(List<Pair<Integer,Integer>> pairList,Point[][] maze){
		List<Point> list = new ArrayList<>();
		for (Pair<Integer,Integer> pair : pairList){
			list.add(maze[pair.getLeft()][pair.getRight()]);
		}
		return list;
	}

	private List<Point> AStarSolution(){
		PriorityQueue<Weight> openList = new PriorityQueue<>();
		List<Weight> closedList = new ArrayList<>();
		openList.offer(new Weight(mazeMatrix[0][0],0,2 * scale - 2));
		Weight end = new Weight(mazeMatrix[scale - 1][scale - 1],0,0);
		while (!openList.contains(end)){
			Weight bestPoint = openList.poll();
			closedList.add(bestPoint);
			List<Point> validNeighbors = getNeighborCells(bestPoint.getPoint(),mazeMatrix,scale,scale)
					.stream()
					.filter(e -> {
						//被墙挡住的不算
						Direction direction = relativeDirection(bestPoint.getPoint(),e);
						if (direction == Direction.LEFT)
							return bestPoint.getPoint().isAccessRight();
						if (direction == Direction.RIGHT)
							return bestPoint.getPoint().isAccessLeft();
						if (direction == Direction.UP)
							return bestPoint.getPoint().isAccessDown();
						if (direction == Direction.DOWN)
							return bestPoint.getPoint().isAccessUp();
						return true;
					})
					.filter(e -> !openList.contains(new Weight(e)))
					.filter(e -> !closedList.contains(new Weight(e)))
					.collect(Collectors.toList());
			validNeighbors.forEach(p -> {
				p.setParent(bestPoint.getPoint());
				openList.offer(new Weight(p,bestPoint.getG() + 1,2 * scale - 2 - p.getX() - p.getY()));
			});
		}
		Point traceBack = mazeMatrix[scale - 1][scale - 1];
		Stack<Point> shortestRoute = new Stack<>();
		do {
			shortestRoute.push(traceBack);
			traceBack = traceBack.getParent();
		}while (traceBack != null);
		List<Point> routeList = new ArrayList<>();
		while (!shortestRoute.isEmpty()){
			routeList.add(shortestRoute.pop());
		}
		return routeList;
	}

	public static void main(String[] args) throws IOException {
		int scale = 20;
		int cellSize = 40;
		int panelMargin = 10;
		Maze maze = new Maze(scale);
		JFrame frame = new JFrame();
		maze.setParentFrame(frame);
		URL path = Maze.class.getClassLoader().getResource("maze.png");
		Image image = Toolkit.getDefaultToolkit().createImage(path);
		frame.setIconImage(image);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel mazePanel = new JPanel();
		mazePanel.setLayout(null);
		mazePanel.add(maze);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(mazePanel,BorderLayout.CENTER);
		JPanel bottomPanel = new JPanel();
		JComboBox<Level> levelBox = new JComboBox<>();
		levelBox.setFont(new Font("仿宋",Font.BOLD,35));
		levelBox.addItem(Level.EASY);
		levelBox.addItem(Level.MEDIUM);
		levelBox.addItem(Level.HARD);
		bottomPanel.add(levelBox);
		MyButton randomGenerate = new MyButton("快速生成",35);
		randomGenerate.setActionCommand(COMMAND_RANDOM_GENERATE);
		MyButton showStepGenerate = new MyButton("单步生成",35);
		showStepGenerate.setActionCommand(COMMAND_STEP_GENERATE);
		MyButton gameStart = new MyButton("开始游戏",35);
		gameStart.setActionCommand(COMMAND_START_GAME);
		MyButton hint = new MyButton("偷看答案",35);
		hint.setActionCommand(COMMAND_SHOW_WAY_OUT);

		ActionListener btnListener = e -> {
			Level item = (Level)levelBox.getSelectedItem();
			String command = e.getActionCommand();
			if (command.equals(COMMAND_RANDOM_GENERATE)){
				maze.init(item.getScale());
				maze.randomGenerate();
			}else if (command.equals(COMMAND_STEP_GENERATE)){
				maze.init(item.getScale());
				maze.stepGenerate();
			}else if (command.equals(COMMAND_START_GAME)){
				maze.startGame();
			}else if (command.equals(COMMAND_SHOW_WAY_OUT)){
				maze.showWayOut();
			}
			if (maze.isShowRoute())
				hint.setText("隐藏答案");
			else
				hint.setText("偷看答案");
			maze.repaint();
		};
		/** 绑定监听器 **/
		randomGenerate.addActionListener(btnListener);
		showStepGenerate.addActionListener(btnListener);
		gameStart.addActionListener(btnListener);
		hint.addActionListener(btnListener);
		/** 绑定监听器 **/

		bottomPanel.add(randomGenerate);
		bottomPanel.add(showStepGenerate);
		bottomPanel.add(gameStart);
		bottomPanel.add(hint);
		frame.getContentPane().add(bottomPanel,BorderLayout.SOUTH);

		frame.setSize(1000,1000);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				maze.setLocation((mazePanel.getWidth() - maze.getWidth())/2,(mazePanel.getHeight() - maze.getHeight())/2);
			}
		});
		frame.setVisible(true);
	}
}
