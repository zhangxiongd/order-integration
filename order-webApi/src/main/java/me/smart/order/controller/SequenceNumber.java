package me.smart.order.controller;

/**
 * Created by zhangxiong on 16/2/5.
 */
public class SequenceNumber {
    private static ThreadLocal<Integer> swqNum = new ThreadLocal<Integer>() {
        @Override
        public Integer initialValue() {
            return 0;
        }
    };


    public int getNumb() {
        swqNum.set(swqNum.get() + 1);
        return swqNum.get();
    }

    public static void main(String[] args) {
        SequenceNumber sequenceNumber = new SequenceNumber();
        TestClient t1 = new TestClient(sequenceNumber);
        TestClient t2 = new TestClient(sequenceNumber);
        TestClient t3 = new TestClient(sequenceNumber);
        t1.start();
        t2.start();
        t3.start();
    }

    private static class TestClient extends Thread {
        private SequenceNumber sequenceNumber;

        public TestClient(SequenceNumber sequenceNumber) {
            this.sequenceNumber = sequenceNumber;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("Thread[" +
                        Thread.currentThread().getName() + "]sn[" + sequenceNumber.getNumb() + "]");
            }

        }
    }
}