package com.solvd.banksystem.human;

import com.solvd.banksystem.exception.InvalidHumanDataException;
import print.Printable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Human implements Printable {

    private String firstName;
    private String lastName;
    private int age;
    private LocalDateTime birthday;

    public Human(String firstName, String lastName, LocalDateTime birthday) throws InvalidHumanDataException {
        int age = (int) ChronoUnit.YEARS.between(birthday, LocalDateTime.now());
        if (firstName.equals("") || lastName.equals("") || age < 0 || age > 150) {
            throw new InvalidHumanDataException("Exception: InvalidHumanData.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.age = age;
    }

    public void setFirstName(String firstName) throws InvalidHumanDataException {
        if (firstName.equals("")) {
            throw new InvalidHumanDataException("Exception: InvalidHumanData.");
        }
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) throws InvalidHumanDataException {
        if (lastName.equals("")) {
            throw new InvalidHumanDataException("Exception: InvalidHumanData");
        }
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAge(int age) throws InvalidHumanDataException {
        if (age < 0 || age > 150) {
            throw new InvalidHumanDataException("Exception: InvalidHumanData");
        }
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    @Override
    public void print() {
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("Born: " + birthday);
        System.out.println("Age: " + age);
    }

    @Override
    public String toString() {
        return "Class Human [firstName = " + firstName + ", lastName = "
                + lastName + ", age = " + age + ", birthday = "
                + birthday.getDayOfMonth() + "." + birthday.getMonth() + "."
                + birthday.getYear() + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Human human = (Human) object;
        return age == human.getAge()
                && (firstName != null && firstName.equals(human.getFirstName()))
                && (lastName != null && lastName.equals(human.getLastName()))
                && (birthday.equals(human.getBirthday()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, birthday);
    }
}
