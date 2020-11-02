package com.cg.addressbook;
import org.junit.*;

public class TestAddressBook {
    @Test
    public void test1ReadData() {
        AddressBook a = new AddressBook();
        Assert.assertEquals(5, a.readData().size());
    }
}
