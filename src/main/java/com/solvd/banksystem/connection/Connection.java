package com.solvd.banksystem.connection;


import com.solvd.banksystem.MainClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection {

    private static final Logger LOGGER = LogManager.getLogger(Connection.class);

    public void create() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " created.");
    }

    public void read() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " read.");
    }

    public void update() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " updated.");
    }

    public void delete() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " deleted.");
    }

}
