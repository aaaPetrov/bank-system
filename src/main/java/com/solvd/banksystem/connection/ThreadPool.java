package com.solvd.banksystem.connection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    private volatile static ThreadPool instance;

    private final int sizeOfPool;
    private final List<Connection> connections;
    private final LinkedBlockingQueue<Runnable> queue;

    private ThreadPool(int sizeOfPool) {
        this.sizeOfPool = sizeOfPool;
        this.queue = new LinkedBlockingQueue();
        this.connections = new ArrayList<>();
        for (int i = 0; i < sizeOfPool; i++) {
            this.connections.add(new Connection());
            new Thread(this.connections.get(i)).start();
        }
    }

    public void releaseConnection(Runnable task) {
        synchronized (this.queue) {
            this.queue.add(task);
            this.queue.notify();
        }
    }

    public Runnable getConnection() {
        synchronized (this.queue) {
            while (this.queue.isEmpty()) {
                try {
                    this.queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return this.queue.poll();
        }
    }

    public static ThreadPool getInstance(int sizeOfPool) {
        if (instance == null) {
            synchronized (ThreadPool.class) {
                if (instance == null) {
                    instance = new ThreadPool(sizeOfPool);
                }
            }
        }
        return instance;
    }

    public class Connection implements Runnable {

        public void run() {
            Runnable task;
            while (true) {
                task = getConnection();
                try {
                    task.run();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
