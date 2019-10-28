import java.util.concurrent.locks.ReentrantLock;

public class JavaTest {
    public static class Bank{
        ReentrantLock mLock = new ReentrantLock();
        private static final Integer initalBalance = 10000;
        private Integer[] accounts = new Integer[100];
        {
            for (int i = 0;i < accounts.length; i++){
                accounts[i] = initalBalance;
            }
        }

        public void transfer(int from,int to,Integer amount){
            mLock.lock();
            try{
                accounts[from] -= amount;
                accounts[to] += amount;
                System.out.println("thread " + Thread.currentThread().getName() +"transfer from " + from + " to " + to + " with money : " + amount);
            }finally {
                mLock.unlock();
            }
        }
        public void allAmount(){
            Integer sum = 0;
            for (Integer item : accounts){
                sum += item;
            }
            System.out.println("All amount is " + sum);
        }
        public int size(){
            return accounts.length;
        }
    }

    public static void main(String[] args){
        Bank bank = new Bank();
        for (int i = 0; i < 100; i++){
            Runnable run = ()->{
                while(true){
                        int from = (int)(Math.random() * bank.size());
                        int to = (int)(Math.random() * bank.size());
                        int amount = (int)(Math.random() * 10000);
                        bank.transfer(from,to,amount);
                        bank.allAmount();
                    try {
                        Thread.sleep((int)(200 * Math.random()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread t = new Thread(run);
            t.start();
        }
    }
}
