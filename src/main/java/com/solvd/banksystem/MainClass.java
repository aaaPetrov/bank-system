package com.solvd.banksystem;

import com.solvd.banksystem.bankoperation.client.work.Work;
import com.solvd.banksystem.address.Address;
import com.solvd.banksystem.bank.*;
import com.solvd.banksystem.bank.currency.Currency;
import com.solvd.banksystem.bank.currency.Diamond;
import com.solvd.banksystem.bank.currency.Gold;
import com.solvd.banksystem.bank.currency.Value;
import com.solvd.banksystem.bank.employee.Employee;
import com.solvd.banksystem.bank.organization.JeweleryBank;
import com.solvd.banksystem.bank.organization.TaxPayable;
import com.solvd.banksystem.bankoperation.*;
import com.solvd.banksystem.bankoperation.client.Client;
import com.solvd.banksystem.connection.*;
import com.solvd.banksystem.exception.InvalidClientAgeException;
import com.solvd.banksystem.exception.InvalidHumanDataException;
import com.solvd.banksystem.human.Human;
import com.solvd.banksystem.print.Printable;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import com.solvd.banksystem.bank.currency.Currency.CurrencyType;

public class MainClass {

    private static final Logger LOGGER = LogManager.getLogger(MainClass.class);

    public static <money> void main(String[] args) {
        System.out.println("\n\n///////////////////////////////////////////////////////////////////\n\n");
        // we can create Currency objects which can throw an exception,
        // but we don't need to catch them.
        Currency byn1 = new Currency(1000000, CurrencyType.BYN);
        Currency rub1 = new Currency(1250000, CurrencyType.RUB);
        Currency eur1 = new Currency(150000, CurrencyType.EURO);
        Currency usd1 = new Currency(175000, CurrencyType.USD);
        Currency byn2 = new Currency(2000000, CurrencyType.BYN);
        Currency eur2 = new Currency(250000, CurrencyType.EURO);
        Currency usd2 = new Currency(275000, CurrencyType.USD);
        Currency rub2 = new Currency(2250000, CurrencyType.RUB);
        Currency byn3 = new Currency(3000000, CurrencyType.BYN);
        Currency rub3 = new Currency(3250000, CurrencyType.RUB);
        Currency eur3 = new Currency(350000, CurrencyType.EURO);
        Currency usd3 = new Currency(375000, CurrencyType.USD);
        Currency byn4 = new Currency(4000000, CurrencyType.BYN);
        Currency rub4 = new Currency(4250000, CurrencyType.RUB);
        Currency eur4 = new Currency(450000, CurrencyType.EURO);
        Currency usd4 = new Currency(475000, CurrencyType.USD);

        Address adr1 = new Address("Minsk", "Lenina", 3);
        Address adr2 = new Address("Moscow", "Old Arbat", 12);
        Address adr3 = new Address("London", "Piccadilly", 7);
        Address adr4 = new Address("Berlin", "Unter den Linden", 9);
        Address adr5 = new Address("Barcelona", "La Rambla", 4);
        Address adr6 = new Address("Paris", "Richelieu", 16);
        Address adr7 = new Address("Kiev", "Kreshyatnik", 1);

        CreditBank creditBank1 = new CreditBank("Commercial Banking", adr1,
                LocalDateTime.of(2017, Month.JULY, 9, 12, 0), usd1, eur1, rub1, byn1);
        CreditBank creditBank2 = new CreditBank("Finance", adr2,
                LocalDateTime.of(2000, Month.AUGUST, 26, 0, 0), usd2, eur2, rub2, byn2);
        MortgageBank mortgageBank1 = new MortgageBank("Future's", adr3,
                LocalDateTime.of(1993, Month.MAY, 3, 18, 6), usd3, eur3, rub3, byn3);
        MortgageBank mortgageBank2 = new MortgageBank("Absolute", adr4,
                LocalDateTime.of(1886, Month.DECEMBER, 31, 23, 59), usd4, eur4, rub4, byn4);

        Work work1 = new Work("Google", "Sales Manager", 6500, CurrencyType.USD);
        Work work2 = new Work("KFC", "Restaurant Crew", 850, CurrencyType.BYN);
        Work work3 = new Work("Adidas", "Production Line Engineer", 2800, CurrencyType.EURO);


        CreditType creditType1 = new CreditType(
                "Elementary USD", CurrencyType.USD, 1, 12, 1000, 2000);
        CreditType creditType2 = new CreditType(
                "Light USD", CurrencyType.USD, 1, 9, 2000, 5000);
        CreditType creditType3 = new CreditType(
                "Medium USD", CurrencyType.USD, 2, 8, 5000, 10000);
        CreditType creditType4 = new CreditType(
                "High USD", CurrencyType.USD, 3, 7, 10000, 20000);
        CreditType creditType5 = new CreditType(
                "Inescapable USD", CurrencyType.USD, 5, 7, 20000, 50000);
        CreditType creditType6 = new CreditType(
                "Light EURO", CurrencyType.EURO, 2, 17, 1000, 6000);
        CreditType creditType7 = new CreditType(
                "Medium EURO", CurrencyType.EURO, 2, 14, 6000, 15000);
        CreditType creditType8 = new CreditType(
                "High EURO", CurrencyType.EURO, 2, 11, 15000, 30000);
        CreditType creditType9 = new CreditType(
                "Light RUB", CurrencyType.RUB, 1, 25, 70000, 210000);
        CreditType creditType10 = new CreditType(
                "Medium RUB", CurrencyType.RUB, 3, 23, 210000, 400000);
        CreditType creditType11 = new CreditType(
                "High RUB", CurrencyType.RUB, 5, 20, 400000, 1000000);
        CreditType creditType13 = new CreditType(
                "Medium BYN", CurrencyType.BYN, 1, 8, 20000, 50000);
        CreditType creditType14 = new CreditType(
                "High BYN", CurrencyType.BYN, 2, 11, 50000, 150000);

        creditBank1.addCreditType(creditType1);
        creditBank1.addCreditType(creditType2);
        creditBank1.addCreditType(creditType6);
        creditBank1.addCreditType(creditType9);
        creditBank1.addCreditType(creditType5);
        creditBank1.addCreditType(creditType11);
        creditBank1.addCreditType(creditType14);
        creditBank2.addCreditType(creditType3);
        creditBank2.addCreditType(creditType7);
        creditBank2.addCreditType(creditType10);
        creditBank2.addCreditType(creditType13);
        creditBank2.addCreditType(creditType4);
        creditBank2.addCreditType(creditType8);

        Client client1 = null;
        Client client2 = null;
        Client client3 = null;
        Credit credit1 = null;
        Credit credit2 = null;
        Credit credit3 = null;
        Credit credit4 = null;
        try {
            client1 = new Client("Tom", "Fox", LocalDateTime.of(1990, Month.APRIL, 5, 10, 15), work1);
            client2 = new Client("Alexa", "Dilan", LocalDateTime.of(1998, Month.SEPTEMBER, 18, 18, 27), work2);
            //if client less than 18 years old, or more than 100 (calculations are in "year" parameter), program will throw InvalidClientAge exception ( unchecked runtime exception ).
            //if client less than 0 years old, or more than 150 (calculations are in "year" parameter), program will throw InvalidHumanData exception ( checked exception ).
            client3 = new Client("Fil", "Flinn", LocalDateTime.of(2024, Month.NOVEMBER, 7, 3, 41), work3);
        } catch (InvalidHumanDataException | InvalidClientAgeException exception) {
            LOGGER.error(exception.getMessage());
        } finally {
            if (client1 != null) {
                credit1 = new Credit(client1, creditType3, 6000);
                credit1.setMoneyPaid(580);
                creditBank1.add(credit1);
                LOGGER.info("Client1 created.");
            } else {
                LOGGER.error("Clent1 error.");
            }
            if (client2 != null) {
                credit2 = new Credit(client2, creditType7, 11000);
                creditBank2.add(credit2);
                LOGGER.info("Client2 created.");
            } else {
                LOGGER.error("Clent2 error.");
            }
            if (client3 != null) {
                credit3 = new Credit(client3, creditType14, 80000);
                credit4 = new Credit(client3, creditType1, 1500);
                creditBank2.add(credit3);
                LOGGER.info("Client3 created.");
            } else {
                LOGGER.error("Clent3 error.");
            }
        }

        System.out.println("\n\n/////////////////////////////////FIRST////////////////////////////\n\n");
        credit1.print();

        System.out.println("\n\n/////////////////////////////////SECOND////////////////////////////\n\n");
        creditBank1.print();
        Address address1 = new Address("New York", "Park Avenue", 1);
        BankSystem bankSystem = BankSystem.getInstance("Unions", address1, LocalDateTime.of(1934, Month.MARCH, 8, 18, 0));
        //Always null because off singletone pattern.
        BankSystem bankSystem1 = BankSystem.getInstance("Unions", address1, LocalDateTime.of(1934, Month.MARCH, 8, 18, 0));
        bankSystem.addBank(creditBank1);
        bankSystem.addBank(creditBank2);
        bankSystem.addBank(mortgageBank1);
        bankSystem.addBank(mortgageBank2);

        System.out.println("\n\n/////////////////////////////////THIRD////////////////////////////\n\n");
        bankSystem.searchForCreditType(CurrencyType.USD);
        bankSystem.searchForCreditType(CurrencyType.USD, 1500);
        bankSystem.searchForCreditType(CurrencyType.EURO, 300);
        bankSystem.searchForCreditType(CurrencyType.USD, 1500000);

        System.out.println("\n\n/////////////////////////////////FOURTH////////////////////////////\n\n");
        BankSystem.exchangeRates();

        System.out.println("\n\n/////////////////////////////////FIFTH////////////////////////////\n\n");
        bankSystem.searchForCreditType(CurrencyType.BYN);
        creditBank1.removeCreditType(creditType14);
        bankSystem.searchForCreditType(CurrencyType.BYN);

        System.out.println("\n\n/////////////////////////////////SIXTH////////////////////////////\n\n");
        Work work4 = new Work("Microsoft", "Programming Engineer", 12500, CurrencyType.USD);
        Work work5 = new Work("McDonald's", "Restaurant Crew", 1050, CurrencyType.BYN);
        Work work6 = new Work("Turkish Airlines", "Pilot", 2100, CurrencyType.EURO);

        Client client4 = null;
        Client client5 = null;
        Client client6 = null;
        Mortgage mortgage1 = null;
        Mortgage mortgage2 = null;
        Mortgage mortgage3 = null;
        MortgagedApartment apartment1 = new MortgagedApartment(adr5, 39, new Currency(185000, CurrencyType.EURO));
        MortgagedApartment apartment2 = new MortgagedApartment(adr6, 13, new Currency(240000, CurrencyType.EURO));
        MortgagedApartment apartment3 = new MortgagedApartment(adr7, 47, new Currency(110000, CurrencyType.RUB));

        try {
            client4 = new Client("Bill", "Milligan", LocalDateTime.of(1991, Month.DECEMBER, 1, 12, 44), work4);
            client5 = new Client("Alisha", "Willis", LocalDateTime.of(1989, Month.FEBRUARY, 23, 16, 11), work5);
            client6 = new Client("Richard", "Forester", LocalDateTime.of(1977, Month.AUGUST, 29, 4, 3), work6);
        } catch (InvalidHumanDataException | InvalidClientAgeException exception) {
            LOGGER.error(exception.getMessage());
        } finally {
            if (client4 != null) {
                mortgage1 = new Mortgage(client4, apartment1);
                mortgageBank1.add(mortgage1);
                LOGGER.info("Client4 created.");
            } else {
                LOGGER.error("Client4 error.");
            }
            if (client5 != null) {
                mortgage2 = new Mortgage(client5, apartment2);
                mortgageBank1.add(mortgage2);
                LOGGER.info("Client5 created.");
            } else {
                LOGGER.error("Client5 error.");
            }
            if (client6 != null) {
                mortgage3 = new Mortgage(client6, apartment3);
                mortgageBank2.add(mortgage3);
                LOGGER.info("Client6 created.");
            } else {
                LOGGER.error("Client6 error.");
            }
        }

        Human human1 = null;
        try {
            human1 = new Human(client5.getFirstName(), client5.getLastName(), client5.getBirthday());
        } catch (InvalidHumanDataException exception) {
            LOGGER.error(exception.getMessage());
        } finally {
            if (human1 != null) {
                LOGGER.info("Human1 created.");
            } else {
                LOGGER.error("Human1 error.");
            }
        }
        List<BankOperation> operations1 = mortgageBank1.find(human1);
        printOperations(operations1);

        System.out.println("\n\n/////////////////////////////////SEVENTH////////////////////////////\n\n");
        creditBank1.add(credit4);
        List<BankOperation> operations2 = new ArrayList<>(creditBank1.getCredits());
        printOperations(operations2);

        System.out.println("\n\n/////////////////////////////////EIGHTH////////////////////////////\n\n");
        creditBank1.payTax();
        bankSystem.payTax();

        System.out.println("\n\n/////////////////////////////////NINTH////////////////////////////\n\n");
        Work work7 = new Work("Oxford", "Professor", 3400, CurrencyType.USD);
        Client client7 = null;
        try {
            client7 = new Client("Mick", "Flick", LocalDateTime.of(2001, Month.JANUARY, 14, 1, 15), work7);
        } catch (InvalidHumanDataException | InvalidClientAgeException exception) {
            LOGGER.error(exception.getMessage());
        } finally {
            if (client7 != null) {
                LOGGER.info("Client7 created.");
            } else {
                LOGGER.error("Client7 error.");
            }
        }
        Credit credit5 = new Credit(client7, creditType1, 1500);
        Credit credit6 = new Credit(client7, creditType2, 4000);
        creditBank1.add(credit5);
        creditBank2.add(credit6);

        Address adr8 = new Address("Warshaw", "Nowy Swiat", 11);
        MortgagedApartment apartment4 = new MortgagedApartment(adr8, 24, new Currency(95000, CurrencyType.EURO));
        MortgagedApartment apartment5 = new MortgagedApartment(adr8, 13, new Currency(400000, CurrencyType.USD));
        Mortgage mortgage4 = new Mortgage(client7, apartment4);
        Mortgage mortgage5 = new Mortgage(client7, apartment5);
        mortgageBank1.add(mortgage4);
        mortgageBank2.add(mortgage5);

        Human human2 = null;
        try {
            human2 = new Human(client7.getFirstName(), client7.getLastName(), client7.getBirthday());
        } catch (InvalidHumanDataException exception) {
            LOGGER.error(exception.getMessage());
        } finally {
            if (human2 != null) {
                LOGGER.info("Human2 created.");
            } else {
                LOGGER.error("Human2 error.");
            }
        }
        List<BankOperation> operations3 = bankSystem.find(client7); //or .searchOperation(human2) it works the same
        printOperations(operations3);

        System.out.println("\n\n/////////////////////////////////TENTH////////////////////////////\n\n");
        System.out.println("PAY TAX METHOD: \n");
        payTax(creditBank1);
        payTax(mortgageBank1);
        payTax(bankSystem);

        System.out.println("\nPRINTER METHOD: \n");
        printerMethod(mortgage1);
        printerMethod(credit1);
        printerMethod(client1);
        printerMethod(usd1);
        operations3 = bankSystem.find(client7);
        printerMethod(operations3);

        System.out.println("\nEXCHANGE METHOD: \n");
        Currency currencyUsd = new Currency(200, CurrencyType.USD);
        Currency currencyByn = exchangeToByn(creditBank1, currencyUsd);
        System.out.println("USD to BYN exchange:");
        printerMethod(currencyUsd);
        printerMethod(currencyByn);
        System.out.println("\nRUB to EURO exchange:");
        Currency currencyRub = new Currency(90000, CurrencyType.RUB);
        Currency currencyEuro = exchangeToEuro(mortgageBank2, currencyRub);
        printerMethod(currencyRub);
        printerMethod(currencyEuro);

        System.out.println("\n\n/////////////////////////////////ELEVENTH////////////////////////////\n\n");
        Work work8 = new Work("Dunkin Donates", "Cook", 3000, CurrencyType.USD);
        Client client8 = null;
        try {
            client8 = new Client("Felix", "Irvin", LocalDateTime.of(2003, Month.FEBRUARY, 12, 14, 43), work8);
        } catch (InvalidHumanDataException | InvalidClientAgeException exception) {
            LOGGER.error(exception.getMessage());
        } finally {
            if (client8 != null) {
                LOGGER.info("Client8 created.");
            } else {
                LOGGER.error("Client8 error.");
            }
        }
        Credit credit7 = new Credit(client8, creditType3, 9000);

        add(creditBank2, credit7);
        printerMethod(find(creditBank2, client8));
        remove(creditBank2, credit7);
        printerMethod(find(creditBank2, client8));

        System.out.println("\n\n/////////////////////////////////THIRTEENTH////////////////////////////\n\n");
        try (FileWriter fileWriter = new FileWriter()) {
            fileWriter.write("Hello!\n");
        }

        System.out.println("\n\n/////////////////////////////////FOURTEENTH////////////////////////////\n\n");
        Work work9 = new Work("Baskin Robbins", "Ice-Cream Cook", 2800, CurrencyType.USD);
        Address adr9 = new Address("Los Angeles", "Rodeo Drive", 5);
        MortgagedApartment apartment6 = new MortgagedApartment(adr9, 24, new Currency(95000, CurrencyType.EURO));
        Client client9 = null;
        Mortgage mortgage6 = null;
        try {
            //if client less than 18 years old, or more than 100 (calculations are in "year" parameter), program will throw InvalidClientAge exception ( unchecked runtime exception ).
            //if client less than 0 years old, or more than 150 (calculations are in "year" parameter), program will throw InvalidHumanData exception ( checked exception ).
            client9 = new Client("Kira", "Flow", LocalDateTime.of(2005, Month.APRIL, 3, 16, 27), work9);
        } catch (InvalidHumanDataException exception) {
            LOGGER.error(exception.getMessage());
        }
        //We can throw InvalidClientAge exceptions, but not catch them at compile time, because its RuntimeException.
        catch (InvalidClientAgeException exception) {
            LOGGER.error(exception.getMessage());
        } finally {
            if (client9 != null) {
                mortgage6 = new Mortgage(client9, apartment6);
                add(mortgageBank2, mortgage6);
                printerMethod((Printable) find(mortgageBank2, client9));
                remove(mortgageBank2, mortgage6);
                printerMethod((Printable) find(mortgageBank2, client9));
                LOGGER.info("Client9 created.");
            } else {
                LOGGER.error("Client9 error.");
            }
        }

        System.out.println("\n\n/////////////////////////////////FIFTEENTH////////////////////////////\n\n");
        JeweleryBank<Gold> jeweleryBank = new JeweleryBank<>("JewBank", address1, LocalDateTime.of(2005, Month.APRIL, 1, 0, 0));
        Gold gold1 = new Gold(100, Gold.Content.C999);
        Gold gold2 = new Gold(150, Gold.Content.C916);
        Contribution<Gold> goldContribution1 = new Contribution<>(gold1);
        Contribution<Gold> goldContribution2 = new Contribution<>(gold2);

        jeweleryBank.addContribution(client7, goldContribution1);
        jeweleryBank.addContribution(client8, goldContribution2);
        Contribution<? extends Value> searched = jeweleryBank.findContribution(client8);
        searched.print();

        System.out.println("\n\n/////////////////////////////////SIXTEENTH////////////////////////////\n\n");
        try {
            Employee employee1 = new Employee("Tom", "Bom",
                    LocalDateTime.of(1990, Month.SEPTEMBER, 1, 12, 15),
                    "Trader", new Currency(5000, CurrencyType.USD));
            Employee employee2 = new Employee("Sam", "Fam",
                    LocalDateTime.of(1992, Month.OCTOBER, 7, 16, 41),
                    "Trader", new Currency(5000, CurrencyType.USD));
            Employee employee3 = new Employee("Max", "Pax",
                    LocalDateTime.of(1995, Month.NOVEMBER, 14, 18, 6),
                    "Broker", new Currency(20000, CurrencyType.USD));

            List<Employee> employees = new ArrayList<>();
            employees.add(employee1);
            employees.add(employee2);
            employees.add(employee3);
            creditBank1.setEmployees(employees);
            /*creditBank1.addEmployee(employee1);
            creditBank1.addEmployee(employee2);
            creditBank1.addEmployee(employee3);*/
            Employee searchedEmployee = creditBank1.findEmployee("Max", "Pax");
            employees.remove(searchedEmployee);
            creditBank1.setEmployees(employees);
            /*creditBank1.removeEmployee(searchedEmployee);*/
            printerMethod(creditBank1.getEmployees());
        } catch (InvalidHumanDataException exception) {
            LOGGER.error(exception.getMessage());
        }

        System.out.println("\n\n/////////////////////////////////SEVENTEENTH////////////////////////////\n\n");
        Gold gold = new Gold(500, Gold.Content.C950);
        SafeBox<? extends Value> goldSafeBox = new SafeBox<>(gold, 3);
        Diamond diamond = new Diamond(20, new Currency(500, CurrencyType.BYN), "White", Diamond.Clarity.A);
        SafeBox<? extends Value> diamondSafeBox = new SafeBox<>(diamond, 12);
        Currency currency = new Currency(120000, CurrencyType.EURO);
        SafeBox<? extends Value> currencySafeBox = new SafeBox<>(currency, 7);
        goldSafeBox.print();
        System.out.println("\n");
        diamondSafeBox.print();
        System.out.println("\n");
        currencySafeBox.print();
        System.out.println("\n");

        JeweleryBank<? extends Value> jeweleryBank2 = new JeweleryBank<>("JewBank", address1, LocalDateTime.of(2005, Month.APRIL, 1, 0, 0));
        Contribution<? extends Value> diamondContribution1 = new Contribution<>(diamond);
        jeweleryBank2.addContribution(client1, diamondContribution1);
        jeweleryBank2.addContribution(client2, goldContribution1);
        Contribution<? extends Value> searched2 = jeweleryBank2.findContribution(client2);
        searched2.print();

        //WORD COUNTER
        try {
            WordCounter.countWordsInFile("./src/main/resources/Text.txt", "./src/main/java/com/solvd/banksystem/WordCount.txt");
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }

        System.out.println("\n\n/////////////////////////////////LAMBDA////////////////////////////\n\n");
        Printable printable = () -> {
            JeweleryBank<? extends Value> jeweleryBank3 = new JeweleryBank<>("JewBank", address1, LocalDateTime.of(2005, Month.APRIL, 1, 0, 0));

            Gold gold3 = new Gold(100, Gold.Content.C999);
            Diamond diamond1 = new Diamond(20, new Currency(500, CurrencyType.BYN), "White", Diamond.Clarity.A);

            Contribution<Gold> goldContribution3 = new Contribution<>(gold3);
            Contribution<? extends Value> diamondContribution2 = new Contribution<>(diamond1);

            Client client10 = null;
            Client client11 = null;
            try {
                client10 = new Client("Bill", "Milligan", LocalDateTime.of(1991, Month.DECEMBER, 1, 12, 44), work4);
                client11 = new Client("Alisha", "Willis", LocalDateTime.of(1989, Month.FEBRUARY, 23, 16, 11), work5);
                jeweleryBank3.addContribution(client10, diamondContribution2);
                jeweleryBank3.addContribution(client11, goldContribution3);
                Contribution<? extends Value> searched3 = jeweleryBank2.findContribution(client10);
                searched2.print();
            } catch (InvalidHumanDataException exception) {
                LOGGER.error(exception.getMessage());
            }
        };
        printerMethod(printable);

        System.out.println("\n\n/////////////////////////////////OTHER VARIATION OF LAMBDA////////////////////////////\n\n");
        printerMethod(() -> {
            JeweleryBank<? extends Value> jeweleryBank3 = new JeweleryBank<>("JewBank", address1, LocalDateTime.of(2005, Month.APRIL, 1, 0, 0));

            Gold gold3 = new Gold(100, Gold.Content.C999);
            Diamond diamond1 = new Diamond(20, new Currency(500, CurrencyType.BYN), "White", Diamond.Clarity.A);

            Contribution<Gold> goldContribution3 = new Contribution<>(gold3);
            Contribution<? extends Value> diamondContribution2 = new Contribution<>(diamond1);

            Client client10 = null;
            Client client11 = null;
            try {
                client10 = new Client("Bill", "Milligan", LocalDateTime.of(1991, Month.DECEMBER, 1, 12, 44), work4);
                client11 = new Client("Alisha", "Willis", LocalDateTime.of(1989, Month.FEBRUARY, 23, 16, 11), work5);
                jeweleryBank3.addContribution(client10, diamondContribution2);
                jeweleryBank3.addContribution(client11, goldContribution3);
                Contribution<? extends Value> searched3 = jeweleryBank2.findContribution(client10);
                searched2.print();
            } catch (InvalidHumanDataException exception) {
                LOGGER.error(exception.getMessage());
            }
        });

        System.out.println("\n\n/////////////////////////////////ANOTHER VARIATION OF LAMBDA////////////////////////////\n\n");
        payTax(() -> {
            System.out.println("I paid some tax with Lambda.");
        });

        System.out.println("\n\n/////////////////////////////////THE SAME WITH UNNAMED CLASS////////////////////////////\n\n");
        payTax(new TaxPayable() {
            @Override
            public void payTax() {
                System.out.println("I paid some tax with Unnamed Class.");
            }
        });

        System.out.println("\n\n/////////////////////////////////REFLECTION////////////////////////////\n\n");
        Reflection reflection = null;
        try {
            Class<?> myClass = Class.forName(Reflection.class.getName());
            reflection = (Reflection) myClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException exception) {
            exception.printStackTrace();
        }

        try {
            Field stringField = reflection.getClass().getDeclaredField("stringField");
            Field intField = reflection.getClass().getDeclaredField("intField");
            Field humanField = reflection.getClass().getDeclaredField("humanField");
            stringField.setAccessible(true);
            intField.setAccessible(true);
            humanField.setAccessible(true);
            stringField.set(reflection, "Value");
            intField.set(reflection, 10);
            humanField.set(reflection, new Human(client7.getFirstName(), client7.getLastName(), client7.getBirthday()));
        } catch (NoSuchFieldException | IllegalAccessException | InvalidHumanDataException exception) {
            exception.printStackTrace();
        }

        try {
            Method getStringField = reflection.getClass().getDeclaredMethod("getStringField");
            Method getIntField = reflection.getClass().getDeclaredMethod("getIntField");
            Method getHumanField = reflection.getClass().getDeclaredMethod("getHumanField");
            System.out.println(getStringField.invoke(reflection));
            System.out.println("\n" + getIntField.invoke(reflection) + "\n");
            printerMethod((Printable) getHumanField.invoke(reflection));
        } catch (NoSuchMethodException | SecurityException exception) {
            exception.printStackTrace();
        } catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }


        System.out.println("\n\n/////////////////////////////////STREAMS////////////////////////////\n\n");
        System.out.println("/////////////////////////////////////////////////////////////");
        List<Work> works = Arrays.asList(work1, work2, work3, work4, work5, work6, work7, work8, work9);
        List<?> list = works.stream()
                .filter(work -> work.getSalary() > 1000 && CurrencyType.USD.getType().equals(work.getMoneyType()))
                .map(work -> work.getPositionName())
                .collect(Collectors.toList());
        list
                .forEach(System.out::println);

        System.out.println("/////////////////////////////////////////////////////////////");
        int result = works.stream()
                .filter(work -> work.getSalary() > 1000)
                .map(work -> work.getSalary())
                .findFirst().orElse(-1);
        if (result != -1) {
            System.out.println("First filtered salary: " + result);
        }

        System.out.println("/////////////////////////////////////////////////////////////");
        String str = works.stream()
                .map(work -> work.getCompanyName())
                .peek(companyName -> companyName.toUpperCase(Locale.ROOT))
                .filter(companyName -> companyName.equals("WONDERLAND"))
                .findAny()
                .orElse("THERE IS NO WONDERLAND.");
        System.out.println(str);

        System.out.println("/////////////////////////////////////////////////////////////");
        List<Address> adresses = Arrays.asList(adr1, adr2, adr3, adr4, adr5, adr6, adr7, adr8, adr9);
        adresses.stream()
                .map(address -> address.getCity())
                .filter(city -> city.startsWith("M") || city.startsWith("L"))
                .map(city -> city.concat(" city."))
                .forEach(city -> System.out.println(city));

        System.out.println("/////////////////////////////////////////////////////////////");
        /*List<? extends Bank> banks = Arrays.asList(creditBank1, creditBank2, mortgageBank1, mortgageBank2);
        banks.stream()
                .filter(bank -> bank instanceof  MortgageBank)
                .map(bank -> ((MortgageBank) bank).getMortgages())
                .flatMap(mortgages -> mortgages.stream())
                .filter(mortgage -> CurrencyType.EURO.getType().equals(mortgage.getMoneyType()))
                .peek(mortgage -> printerMethod(mortgage))
                .filter(mortgage -> mortgage.getMoneyPaid() > 0.0)
                .findAny()
                .orElseThrow(() -> new RuntimeException("SOMETHING GOES WRONG Exception."));*/

        System.out.println("\n\n/////////////////////////////////THREADS////////////////////////////\n\n");
        List<Thread> threads = new ArrayList<>();
        IntStream.range(0, 5).boxed()
                .forEach(index -> {
                    //NOT LAZY
                    //ConnectionPool connectionPool = ConnectionPool.getInstance(5);
                    LazyConnectionPool connectionPool = LazyConnectionPool.getInstance(5);
                    Thread thread = new Thread(() -> {
                        Connection task = get(connectionPool);
                        if (task != null) {
                            task.create();
                            connectionPool.releaseTask(task);
                        }
                    });
                    threads.add(thread);
                    thread.start();
                });
        join(threads);
        interrupt(threads);

        System.out.println("\n\n/////////////////////////////////////////////////////////////");
        ThreadPool threadPool = ThreadPool.getInstance(5);
        IntStream.range(0, 5).boxed()
                .forEach(index -> {
                    Task task = new Task("Task " + index);
                    threadPool.releaseConnection(task);
                });
        threadPool.waitUntilAllTasksFinished();
        threadPool.shotdown();

        System.out.println("\n\n//////////////////////////EXECUTORSERVICE + COMPLETABLEFUTURE///////////////////////////////////");
        /*ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(0, 50).boxed()
                .forEach(index -> {
                    CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(
                            () -> new Connection(), executorService).thenAccept(connection -> connection.read());
                });*/
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(0, 50).boxed()
                .map(index -> {
                    CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(
                            () -> new Connection(), executorService).thenAccept(connection -> connection.read());
                    return completableFuture;
                })
                .collect(Collectors.toList()).forEach(voidCompletableFuture -> voidCompletableFuture.join());
        executorService.shutdown();
        System.out.println("program ended.");
    }

    private static void join(List<Thread> threads) {
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        });
    }

    private static void interrupt(List<Thread> threads) {
        threads.forEach(thread -> {
            if (thread.isAlive()) {
                thread.interrupt();
            }
        });
    }

    private static Connection get(ConnectionPool connectionPool) {
        Connection connection = null;
        try {
            connection = connectionPool.getTask();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
        return connection;
    }

    private static Connection get(LazyConnectionPool connectionPool) {
        Connection connection = null;
        try {
            connection = connectionPool.getTask();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
        return connection;
    }

    private static void printOperations(List<BankOperation> bankOperations) {
        bankOperations
                .forEach(bankOperation -> bankOperation.print());
        /*for (BankOperation bankOperation : bankOperations) {
            bankOperation.print();
        }*/
    }

    private static void payTax(TaxPayable taxPayable) {
        taxPayable.payTax();
    }

    private static void printerMethod(Printable printable) {
        printable.print();
    }

    private static void printerMethod(List<? extends Printable> printables) {
        printables
                .forEach(printable -> printable.print());
        /*for (Printable element : printables) {
            element.print();
        }*/
    }

    public static Currency exchangeToUsd(Exchangable exchangable, Currency currency) {
        return exchangable.exchangeToUsd(currency);
    }

    public static Currency exchangeToEuro(Exchangable exchangable, Currency currency) {
        return exchangable.exchangeToEuro(currency);
    }

    public static Currency exchangeToRub(Exchangable exchangable, Currency currency) {
        return exchangable.exchangeToRub(currency);
    }

    public static Currency exchangeToByn(Exchangable exchangable, Currency currency) {
        return exchangable.exchangeToByn(currency);
    }

    private static void add(Operationable operationable, BankOperation bankOperation) {
        operationable.add(bankOperation);
    }

    private static void remove(Operationable operationable, BankOperation bankOperation) {
        operationable.remove(bankOperation);
    }

    private static List<BankOperation> find(Findable findable, Human human) {
        if (findable.find(human).isEmpty()) {
            System.out.println("\nNOT FOUND.\n");
        }
        return findable.find(human);
    }
}