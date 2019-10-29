package multiThread;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProducerAndConsumer{
    class Producer implements Runnable{
        private List<String> products;
        private int MAX_CAPACITY;
        public Producer(List<String> products,int MAX_CAPACITY){
            this.products = products;
            this.MAX_CAPACITY = MAX_CAPACITY;
        }
        @Override
        public void run() {
            int num = 0;
            while (true){
                synchronized (products){
                    while (products.size() > MAX_CAPACITY){
                        System.out.println("生产者进入等待");
                        try {
                            products.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String productName = "product@" + num++;
                    products.add(productName);
                    System.out.println("生产者生产了 " + productName);
                    products.notify();
                }
                int rand = (int)(Math.random() * 1000);
                try {
                    Thread.sleep(rand);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable{
        private List<String> products;
        public Consumer(List<String> products){
            this.products = products;
        }
        @Override
        public void run() {
            while(true){
                synchronized (products){
                    while (products == null || products.size() == 0){
                        System.out.println("消费者进入等待");
                        try {
                            products.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String str = products.remove(0);
                    System.out.println("消费者消费了 " + str);
                    products.notify();
                }
                int rand = (int)(Math.random() * 1000);
                try {
                    Thread.sleep(rand);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void test() throws IOException {
        List<String> products = new ArrayList<>();
        int max = 5;
        Thread producer = new Thread(new Producer(products,max));
        Thread consumer = new Thread(new Consumer(products));
        producer.start();
        consumer.start();
        System.in.read();
        
    }

    public static void main(String[] args) throws IOException {
        ProducerAndConsumer test = new ProducerAndConsumer();
        test.test();
    }
}
