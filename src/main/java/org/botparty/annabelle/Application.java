package org.botparty.annabelle;

public class Application {

    public static void main(String[] args) {

        new Runnable() {
            @Override
            public void run() {
                // start chat instance
                ChatServer.getInstance();

                // run indefinitely until ctrl+c
                //noinspection InfiniteLoopStatement
                do {
                    Thread.yield();
                } while (true);
            }
        };
    }
}
