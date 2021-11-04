package com.solvd.banksystem.connection;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LazyConnectionPool {

    private volatile static LazyConnectionPool instance;
    private volatile static int counter;

    private final Queue<Connection> connections;
    private final int size;

    private LazyConnectionPool(int sizeOfPool) {
        this.size = sizeOfPool;
        counter = sizeOfPool;
        this.connections = new ConcurrentLinkedQueue<>();
    }

    public Connection getTask() throws InterruptedException {
        synchronized (this.connections) {
            if(counter != 0) {
                counter--;
                this.connections.offer(new Connection());
            }
            while (this.connections.isEmpty()) {
                connections.wait();
            }
            return this.connections.poll();
        }
    }

    public void releaseTask(Connection task) {
        synchronized (this.connections) {
            this.connections.offer(task);
            this.connections.notify();
        }
    }

    public static LazyConnectionPool getInstance(int sizeOfPool) {
        if (instance == null) {
            synchronized (LazyConnectionPool.class) {
                if (instance == null) {
                    instance = new LazyConnectionPool(sizeOfPool);
                }
            }
        }
        return instance;
    }
}
