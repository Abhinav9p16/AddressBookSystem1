package com.cg.addressbook;

import org.junit.*;

import java.util.ArrayList;

public class TestAddressBook {

    @Test
    public void test_retrieveData() {
        AddressBook a = new AddressBook();
        Assert.assertEquals(7, a.readData().size());
    }

    @Test
    public void test_UpdateDetailsInDatabase() {
        AddressBook a = new AddressBook();
        a.updateContact("address", "Z street", "Akhandanand", "Tripathi");
    }

    @Test
    public void test__RetrieveContactWithinDateRange() {
        AddressBook a = new AddressBook();
        Assert.assertEquals(7, a.findDoj("1999-01-01", "2020-01-01"));
    }

    @Test
    public void test_RetrieveNumContactsByCity() {
        AddressBook a = new AddressBook();
        Assert.assertEquals(3, a.FetchField("city", "Mirzapur"));
    }

    @Test
    public void test_RetrieveNumContactsByState() {
        AddressBook a = new AddressBook();
        Assert.assertEquals(7, a.FetchField("state", "Uttar Pradesh"));
    }

    @Test
    public void test_InsertContactDetailsToDatabase() {
        AddressBook a = new AddressBook();
        Contact c = new Contact();
        c.setContact("Munna", "Tripathi", "M Street", "Mirzapur", "Uttar Pradesh", "434343", "9988776655", "mt@gmail.com");
        c.DOJ = "2019-07-04";
        a.Insert(c);
        Assert.assertEquals(7, a.readData().size());
    }

    @Test
    public void test_InsertContactDetailsToDatabaseByMultiThread() {
        ArrayList<Contact> contacts = new ArrayList<>();
        char d = 'a';
        Contact z = new Contact();
        z.setContact("Abc", "def", "M Street", "Ghi", "Uttar Pradesh", "434343", "9988776655", "abc@gmail.com");
        z.DOJ = "2018-07-04";
        Contact y = new Contact();
        y.setContact("Ace", "Dfg", "N Street", "GhJ", "Uttar Pradesh", "434344", "9988776656", "abcd@gmail.com");
        /*for (int i = 0; i < 3; ++i) {
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
            contacts.add(z);
            contacts.add(c);
            d++;
        }*/
        contacts.add(z);
        contacts.add(y);
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
