package com.cg.addressbook;
import java.util.*;
import java.util.stream.Collectors;

class Contact {
    Scanner sc=new Scanner(System.in);
    String first,last,address,String,city,state,zip,phno,email;
    public boolean equals(Contact check){
        return (check.first.equalsIgnoreCase(this.first)&&check.last.equalsIgnoreCase(this.last));
       }
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
class AddressBook {
    ArrayList <Contact>  c=new ArrayList<Contact>();
    public boolean checkDup(Contact x){ return (c.stream().anyMatch(d -> d.equals(x))); }
    public boolean nameCheck(String f, String l, int i)
    {
        int k=0;
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
            return false;
        }
        return true;
    }
    public void addContact()
    {
        Contact a=new Contact();
        a.addContact();
        if(checkDup(a))  System.out.println("Duplicate Contact");
        else  c.add(a);
    }
    public void editContact(String f, String l, int i)
    {
        if(nameCheck(l, f, i));
        c.get(i).editContact();
    }
    public void viewContact(String f, String l, int i)
    {
        if(nameCheck(l, f, i));
        c.get(i).viewContact();
    }
    public void deleteContact(String f, String l,int i)
    {
        if(nameCheck(l, f, i));
        c.remove(i);
    }
}
public class Address {
    public static void main(String args[])
    {
        HashMap<String,AddressBook> a = new HashMap<String,AddressBook>();
        System.out.println("Welcome to Address Book program");

        Scanner sc=new Scanner(System.in);
        int x=0;
        System.out.println("Write the number of the Address Books ");
        int z= sc.nextInt();
        for (int i=0; i< z; i++)
        {
            System.out.println("Enter name of ADDRESSbook");
            String name = sc.next();
            AddressBook b = new AddressBook();
            a.put(name, b);
        }
        while(x!=5)

        {
            System.out.println("1.ADD A CONTACT");
            System.out.println("2.EDIT A CONTACT BASED ON NAME");
            System.out.println("3.VIEW CONTACT DETAILS BASED ON NAME");
            System.out.println("4.DELETE A CONTACT");
            System.out.println("5.EXIT");
            x=sc.nextInt();
            String name = new String();
            if(x !=5)
            {
                System.out.println("In which address book");
                name = sc.next();
            }

            if(x==1)
            { a.get(name).addContact();

            }
            if(x==2)
            {   int i = 0 ;
                System.out.println("Enter First Name");
                String f=sc.next();
                System.out.println("Enter Last Name");
                String l=sc.next();
                a.get(name).editContact(f, l, i);
            }
            if(x==3)
            {   int i = 0 ;
                System.out.println("Enter First Name");
                String f=sc.next();
                System.out.println("Enter Last Name");
                String l=sc.next();
                a.get(name).viewContact(f, l , i);

            }
            if(x==4)
            {   int i = 0;
                System.out.println("Enter First Name");
                String f=sc.next();
                System.out.println("Enter Last Name");
                String l=sc.next();
                a.get(name).deleteContact(f, l, i);

            }
        }
    }
}
