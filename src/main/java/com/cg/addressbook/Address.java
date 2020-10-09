package com.cg.addressbook;
import java.util.*;
import java.util.stream.Collectors;

class Contact {
    Scanner sc=new Scanner(System.in);
    String first,last,address,String,city,state,zip,phno,email;
    public void addContact()
    {

        System.out.println("Enter First Name");
        this.first=sc.next();
        System.out.println("Enter Last Name");
        this.last=sc.next();
        System.out.println("Enter Address");
        this.address=sc.next();
        System.out.println("Enter city");
        this.city=sc.next();
        System.out.println("Enter state");
        this.state=sc.next();
        System.out.println("Enter zip code");
        this.zip=sc.next();
        System.out.println("Enter phone number");
        String phno=sc.next();
        System.out.println("Enter email");
        String email=sc.next();
    }
}
public class Address {
    public static void main(String args[])
    {
        ArrayList <Contact> c=new ArrayList<Contact>();
        System.out.println("Welcome to Address Book program");
        Scanner sc=new Scanner(System.in);
        int x=0;
        while(x!=2)
        {
            System.out.println("1.ADD A CONTACT");
            System.out.println("2.EXIT");
            x=sc.nextInt();
            if(x==1)
            {
                Contact a=new Contact();
                a.addContact();
                c.add(a);
            }
        }
    }
}