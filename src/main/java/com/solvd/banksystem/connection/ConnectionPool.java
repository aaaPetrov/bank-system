package com.solvd.banksystem.connection;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

public class ConnectionPool {

    private volatile static ConnectionPool instance;

    private final Queue<Connection> connections;
    private final int size;

    private ConnectionPool(int sizeOfPool) {
        this.size = sizeOfPool;
        this.connections = new ConcurrentLinkedQueue<>();
        IntStream.range(0, this.size).boxed()
                .forEach(index -> {
                    this.connections.offer(new Connection());
                });
    }

    public Connection getTask() throws InterruptedException {
        synchronized (this.connections) {
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

    public static ConnectionPool getInstance(int sizeOfPool) {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool(sizeOfPool);
                }
            }
        }
        return instance;
    }
}
