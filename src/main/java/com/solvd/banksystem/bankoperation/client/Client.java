package com.solvd.banksystem.bankoperation.client;

import com.solvd.banksystem.exception.InvalidClientAgeException;
import com.solvd.banksystem.exception.InvalidHumanDataException;
import com.solvd.banksystem.human.Human;
import com.solvd.banksystem.bankoperation.client.work.Work;
import java.time.LocalDateTime;
import java.util.Objects;

public class Client extends Human {

    public static int count = 0;

    private Work work;

    public Client(String firstName, String lastName, LocalDateTime birthDay, Work work) throws InvalidHumanDataException {
        super(firstName, lastName, birthDay);
        if (super.getAge() < 18 || super.getAge() > 100) {
            throw new InvalidClientAgeException("Runtime Exception: Ivalid Client Age.");
        }
        this.work = work;
        count++;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public Work getWork() {
        return work;
    }

    @Override
    public void print() {
        System.out.println("Client:");
        super.print();
        this.work.print();
    }

    @Override
    public String toString() {
        return "Class Client [firstName = " + getFirstName() + ", lastName = "
                + getLastName() + ", age = " + getAge() + ", birthday = "
                + getBirthday().getDayOfMonth() + "." + getBirthday().getMonth() + "."
                + getBirthday().getYear() + ", count = " + count + ", work = " + work + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Client client = (Client) object;
        return work.equals(client.getWork()) && getFirstName().equals(client.getFirstName()) &&
                getLastName().equals(client.getLastName()) && getAge() == client.getAge() &&
                getBirthday().equals(client.getBirthday());
    }

    @Override
    public int hashCode() {
        return Objects.hash(work, getFirstName(), getLastName(), getBirthday(), getAge());
    }
}
