package com.cg.addressbook;
import org.junit.*;

public class TestAddressBook {
    @Test
    public void test1ReadData() {
        AddressBook a = new AddressBook();
        Assert.assertEquals(8, a.readData().size());
    }
    @Test
    public void test1CheckUpdate()
    {
        AddressBook a=new AddressBook();
        a.updateContact("address","h",2);
    }
    @Test
    public void test3CheckDoj()
    {
        AddressBook a=new AddressBook();
        Assert.assertEquals(4,a.findDoj("2015-01-01","2019-01-01"));
    }
}
