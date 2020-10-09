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
        this.phno=sc.next();
        System.out.println("Enter email");
        this.email=sc.next();
    }
    public void editContact()
    {
        System.out.println("Enter Address");
        this.address=sc.next();
        System.out.println("Enter city");
        this.city=sc.next();
        System.out.println("Enter state");
        this.state=sc.next();
        System.out.println("Enter zip code");
        this.zip=sc.next();
        System.out.println("Enter phone number");
        this.phno=sc.next();
        System.out.println("Enter email");
        this.email=sc.next();
    }
    public void viewContact()
    {
        System.out.println("ADDRESS = "+this.address);
        System.out.println("CITY = "+this.city);
        System.out.println("STATE = "+this.state);
        System.out.println("ZIP = "+this.zip);
        System.out.println("PHONE NUMBER ="+this.phno);
        System.out.println("EMAIL ="+this.email);
    }
}
public class Address {
    public static void main(String args[])
    {
        ArrayList <Contact> c=new ArrayList<Contact>();
        System.out.println("Welcome to Address Book program");
        Scanner sc=new Scanner(System.in);
        int x=0;
        while(x!=4)
        {
            System.out.println("1.ADD A CONTACT");
            System.out.println("2.EDIT A CONTACT BASED ON NAME");
            System.out.println("3.VIEW CONTACT DETAILS BASED ON NAME");
            System.out.println("3.EXIT");
            x=sc.nextInt();
            if(x==1)
            {
                Contact a=new Contact();
                a.addContact();
                c.add(a);
            }
            if(x==2)
            {
                System.out.println("Enter First Name");
                String f=sc.next();
                System.out.println("Enter Last Name");
                String l=sc.next();
                int k=0,i=0;
                for(i=0;i<c.size();i++)
                {
                    if(c.get(i).first.equalsIgnoreCase(f) && c.get(i).last.equalsIgnoreCase(l))
                    {
                        k=1;
                        break;
                    }
                }
                if(k==0)
                {
                    System.out.println("******No Contact found******");
                }
                else
                {
                    c.get(i).editContact();
                }
            }
            if(x==3)
            {
                System.out.println("Enter First Name");
                String f=sc.next();
                System.out.println("Enter Last Name");
                String l=sc.next();
                int k=0,i=0;
                for(i=0;i<c.size();i++)
                {
                    if(c.get(i).first.equalsIgnoreCase(f) && c.get(i).last.equalsIgnoreCase(l))
                    {
                        k=1;
                        break;
                    }
                }
                if(k==0)
                {
                    System.out.println("******No Contact found******");
                }
                else
                {
                    c.get(i).viewContact();
                }
            }
            if(x==4)
            {
                System.out.println("Enter First Name");
                String f=sc.next();
                System.out.println("Enter Last Name");
                String l=sc.next();
                int k=0;
                for(int i=0;i<c.size();i++)
                {
                    if(c.get(i).first.equalsIgnoreCase(f) && c.get(i).last.equalsIgnoreCase(l))
                    {
                        k=1;
                        c.remove(i);
                        System.out.println("Contact Deleted");
                        break;
                    }
                }
                if(k==0)
                {
                    System.out.println("No Contact found");

                }
            }
        }
    }
}
