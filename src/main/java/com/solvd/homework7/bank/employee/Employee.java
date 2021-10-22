package com.solvd.homework7.bank.employee;

import com.solvd.homework7.bank.currency.Currency;
import com.solvd.homework7.exception.InvalidHumanDataException;
import com.solvd.homework7.human.Human;
import print.Printable;
import java.time.LocalDateTime;
import java.util.Objects;


public class Employee extends Human implements Printable {

    public static int count = 0;
    private final Currency salary;
    private final String position;

    public Employee(String firstName, String lastName, LocalDateTime birthDay, String position, Currency salary) throws InvalidHumanDataException {
        super(firstName, lastName, birthDay);
        this.position = position;
        this.salary = salary;
        count++;
    }

    public String getPosition() {
        return position;
    }

    public Currency getSalary() {
        return salary;
    }

    @Override
    public void print() {
        System.out.println("Employee: " + super.getLastName() + " " + super.getFirstName());
        System.out.println("Position: " + position);
        System.out.println("Salary: " + salary.getAmount() + " " + salary.getType());
        System.out.println("Born: " + super.getBirthday().getDayOfMonth() + "." +
                super.getBirthday().getMonth() + "." + super.getBirthday().getYear());
        System.out.println("Age: " + super.getAge());
    }

    @Override
    public String toString() {
        return "Class Employee [firstName = " + getFirstName() + ", lastName = "
                + getLastName() + ", age = " + getAge() + ", birthday = "
                + getBirthday().getDayOfMonth() + "." + getBirthday().getMonth() + "."
                + getBirthday().getYear() + ", count = " + count
                + ", salary = " + salary + ", position = " + position + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Employee employee = (Employee) object;
        return salary.equals(employee.getSalary())
                && (position != null && position.equals(employee.getPosition()))
                && getFirstName().equals(employee.getFirstName())
                && getLastName().equals(employee.getLastName())
                && getAge() == employee.getAge()
                && getBirthday().equals(employee.getBirthday());
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, position, salary, getFirstName(), getLastName(), getAge(), getBirthday());
    }
}
