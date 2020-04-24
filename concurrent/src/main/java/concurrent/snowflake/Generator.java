package concurrent.snowflake;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author yogurtzzz
 * @date 2020/4/17 16:47
 **/
public class Generator {
    public static void main(String[] args) throws InterruptedException, IOException {

        FileWriter writer = new FileWriter("E:\\test.txt");
        Thread[] ts = new Thread[100000];
        for (int i = 0; i < ts.length; i++){
            ts[i] = new Thread(()->{
                String s = "this is " + Thread.currentThread().getName() + " running with cid : " + SnowFlake.nextCid() + "\n";
                System.out.print(s);
                try {
                    writer.write(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            ts[i].start();
        }


        for (Thread t : ts){
            t.join();
        }
        writer.close();
    }
}
