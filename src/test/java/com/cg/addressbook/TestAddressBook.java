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
}
