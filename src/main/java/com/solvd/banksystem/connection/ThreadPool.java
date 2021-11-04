package com.solvd.banksystem.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    private static final Logger LOGGER = LogManager.getLogger(ThreadPool.class);
    private volatile static ThreadPool instance;

    private volatile boolean isActive = true;
    private final int sizeOfPool;
    private final List<Thread> connections;
    private final LinkedBlockingQueue<Runnable> queue;

    private ThreadPool(int sizeOfPool) {
        this.sizeOfPool = sizeOfPool;
        this.queue = new LinkedBlockingQueue();
        this.connections = new ArrayList<>();
        for (int i = 0; i < sizeOfPool; i++) {
            this.connections.add(new Thread(new Connection()));
            this.connections.get(i).start();
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
                    LOGGER.error(e.getMessage());
                }
            }
            return this.queue.poll();
        }
    }

    public synchronized void waitUntilAllTasksFinished() {
        while(this.queue.size() > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        }
        this.isActive = false;
    }

    public synchronized void shotdown() {
        if(!this.isActive) {
            this.connections.stream()
                    .forEach(connection -> {
                        if(connection.isAlive()) {
                            try {
                                connection.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            connection.interrupt();
                        }
                    });
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
            while (isActive) {
                task = getConnection();
                try {
                    task.run();
                } catch (RuntimeException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }

    }

}
