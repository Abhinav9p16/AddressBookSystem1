package com.cg.addressbook;

import org.junit.*;

import java.util.ArrayList;

public class TestAddressBook {

    @Test
    public void test1Read() {
        AddressBook a = new AddressBook();
        Assert.assertEquals(6, a.readData().size());
    }

    @Test
    public void test1Update() {
        AddressBook a = new AddressBook();
        a.updateContact("address", "Z street", "Akhandanand", "Tripathi");
    }

    @Test
    public void test3CheckDOJ() {
        AddressBook a = new AddressBook();
        Assert.assertEquals(6, a.findDoj("2015-01-01", "2020-01-01"));
    }

    @Test
    public void test4CheckCity() {
        AddressBook a = new AddressBook();
        Assert.assertEquals(2, a.FetchField("city", "Mirzapur"));
    }

    @Test
    public void test5CheckState() {
        AddressBook a = new AddressBook();
        Assert.assertEquals(6, a.FetchField("state", "Uttar Pradesh"));
    }

    @Test
    public void test6CheckInsert() {
        AddressBook a = new AddressBook();
        Contact c = new Contact();
        c.setContact("Munna", "Tripathi", "M Street", "Mirzapur", "Uttar Pradesh", "434343", "9988776655", "mt@gmail.com");
        c.DOJ = "2019-07-04";
        a.Insert(c);
        Assert.assertEquals(7, a.readData().size());
    }

    @Test
    public void test7MultiThreadInsert() {
        ArrayList<Contact> contacts = new ArrayList<>();

        char d = 'a';
        for (int i = 0; i < 3; ++i) {
            Contact c = new Contact();
            c.setContact(Character.toString(d),
                    Character.toString(d),
                    Character.toString(d),
                    Character.toString(d),
                    Character.toString(d),
                    Character.toString(d),
                    Character.toString(d),
                    Character.toString(d)
            );
            c.DOJ = "2000-01-01";
            contacts.add(c);
            d++;
        }

        AddressBook a = new AddressBook();
        for (Thread t : a.MultithreadedInsert(contacts)) {
            t.start();
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
