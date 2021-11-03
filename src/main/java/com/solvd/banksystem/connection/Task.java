package com.solvd.banksystem.connection;

public class Task implements Runnable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            create();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void create() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " created.");
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
