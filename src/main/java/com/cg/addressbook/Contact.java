package com.cg.addressbook;

import java.io.Serializable;
import java.util.Scanner;

class Contact implements Serializable {
    String first, last, address, city, state, zip, phno, email, fullName, DOJ;

    public void setContact(String first, String last, String address, String city, String state, String zip, String phno, String email) {
        this.first = first;
        this.last = last;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phno = phno;
        this.email = email;
    }

    public String[] getContactDetails() {
        return new String[]{first, last, address, city, state, zip, phno, email};
    }

    public String toString() {
        return this.fullName;
    }

    public boolean equals(Contact check) {
        return (check.first.equalsIgnoreCase(this.first) && check.last.equalsIgnoreCase(this.last));
    }

    public void addContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First Name");
        this.first = sc.next();
        System.out.println("Enter Last Name");
        this.last = sc.next();
        System.out.println("Enter Address");
        this.address = sc.next();
        System.out.println("Enter city");
        this.city = sc.next();
        System.out.println("Enter state");
        this.state = sc.next();
        System.out.println("Enter zip code");
        this.zip = sc.next();
        System.out.println("Enter phone number");
        this.phno = sc.next();
        System.out.println("Enter email");
        this.email = sc.next();
        this.fullName = this.first + " " + this.last;
    }

    public void editContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Address");
        this.address = sc.next();
        System.out.println("Enter city");
        this.city = sc.next();
        System.out.println("Enter state");
        this.state = sc.next();
        System.out.println("Enter zip code");
        this.zip = sc.next();
        System.out.println("Enter phone number");
        this.phno = sc.next();
        System.out.println("Enter email");
        this.email = sc.next();
    }

    public void viewContact() {
        System.out.println("ADDRESS = " + this.address);
        System.out.println("CITY = " + this.city);
        System.out.println("STATE = " + this.state);
        System.out.println("ZIP = " + this.zip);
        System.out.println("PHONE NUMBER =" + this.phno);
        System.out.println("EMAIL =" + this.email);
    }
}

