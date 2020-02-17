package GUI;


import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class GuiTest {
	static class MyButton extends JButton{
		public MyButton(String text){
			setFont(new Font("仿宋",Font.BOLD,25));
			setVerticalAlignment(SwingConstants.CENTER);
			setVerticalTextPosition(SwingConstants.CENTER);
			setHorizontalAlignment(SwingConstants.CENTER);
			setHorizontalTextPosition(SwingConstants.CENTER);
			/** 点击时不发生样式的改变 **/
			setFocusPainted(false);
			setText(text);
		}
	}
	static class MyFrame extends JFrame{
		public MyFrame()  {
			Image image = Toolkit.getDefaultToolkit().createImage("C:\\Users\\Vergi\\Downloads\\maze.png");
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setIconImage(image);
			setSize(500,600);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			setLocation((int)screenSize.getWidth()/2 - 350,(int)screenSize.getHeight()/2 - 350);
			setTitle("Maze Game");
//			JLabel label = new JLabel("Hello,Swing",JLabel.CENTER);
//			Font font = new Font("Yogurt",Font.ITALIC,20);
//			label.setFont(font);
//			getContentPane().add(label);
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(4,0,5,5));
			panel.add(new MyButton("7"));
			panel.add(new MyButton("8"));
			panel.add(new MyButton("9"));
			panel.add(new MyButton("/"));
			panel.add(new MyButton("4"));
			panel.add(new MyButton("5"));
			panel.add(new MyButton("6"));
			panel.add(new MyButton("*"));
			panel.add(new MyButton("1"));
			panel.add(new MyButton("2"));
			panel.add(new MyButton("3"));
			panel.add(new MyButton("-"));
			panel.add(new MyButton("0"));
			panel.add(new MyButton("C"));
			panel.add(new MyButton("="));
			panel.add(new MyButton("+"));
			setContentPane(panel);
		}

	}


	static class JJP extends JPanel{
		public void paint(Graphics g){
			g.drawLine(50,50,500,50);
		}
	}

	public static void main(String[] args)   {
		JFrame jf = new JFrame();
		jf.setSize(700,700);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.getContentPane().add(new JJP());
		jf.setVisible(true);
	}
}
