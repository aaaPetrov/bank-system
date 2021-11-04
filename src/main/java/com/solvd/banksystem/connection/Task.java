package com.solvd.banksystem.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Task.class);

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            create();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void create() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " created " + name + ".");
    }

    public void read() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " read.");
    }

    public void update() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " updated.");
    }

    public void delete() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " deleted.");
    }

}
