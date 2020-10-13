package com.cg.addressbook;
import java.util.*;
import java.util.stream.Collectors;

class Contact {
    Scanner sc=new Scanner(System.in);
    String first,last,address,String,city,state,zip,phno,email,fullName;
    Contact(){}
    Contact(String fullName){this.fullName=fullName;}
    public String toString(){return this.fullName;}
    public boolean equals(Contact check){ return (check.first.equalsIgnoreCase(this.first)&&check.last.equalsIgnoreCase(this.last)); }
    public void addContact()
    {
        System.out.println("Enter First Name");
        this.first=sc.next();
        System.out.println("Enter Last Name");
        this.last=sc.next();
        this.fullName= this.first+ " " +this.last;
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
    public List<Contact> searchCity(String city){return (c.stream().filter(d->d.city.equalsIgnoreCase(city)).collect(Collectors.toList()));}
    public List<Contact> searchState(String state){return (c.stream().filter(d->d.state.equalsIgnoreCase(state)).collect(Collectors.toList()));}
    public List<Contact> sort(){return c.stream().sorted((p1, p2)->p1.fullName.compareTo(p2.fullName)).collect(Collectors.toList()); }
    public List<Contact> sortCity(){return c.stream().sorted((p1, p2)->p1.city.compareTo(p2.city)).collect(Collectors.toList()); }
    public List<Contact> sortState(){return c.stream().sorted((p1, p2)->p1.state.compareTo(p2.state)).collect(Collectors.toList()); }

    public boolean nameCheck(String f, String l, int i)
    {  return (c.get(i).first.equalsIgnoreCase(f) && c.get(i).last.equalsIgnoreCase(l)) ; }
    public void addContact()
    {
        Contact a=new Contact();
        a.addContact();
        if(checkDup(a))  System.out.println("Duplicate Contact");
        else  c.add(a);
    }
    public void editContact(String f, String l)
    {   for(int i = 0; i< c.size(); i++) { if(nameCheck(f,l,i)); c.get(i).editContact(); break; }}

    public void viewContact(String f, String l)
    {   for(int i = 0; i< c.size(); i++) { if(nameCheck(f,l,i)); c.get(i).viewContact(); break; }}

    public void deleteContact(String f, String l)
    {   for(int i = 0; i< c.size(); i++) { if(nameCheck(f,l,i)); c.remove(i); break; }}
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
            System.out.println("6.Search BY CITY");
            System.out.println("7.Search BY STATE");
            System.out.println("8.SORT BY NAME");
            System.out.println("9.SORT BY CITY");
            System.out.println("10.SORT BY STATE");
            x=sc.nextInt();
            String name = new String();
            if(x !=5 && x!= 6 && x!= 7 && x!= 8 && x!= 9 && x!= 10)
            {
                System.out.println("In which address book");
                name = sc.next();
            }

            if(x==1)
            { a.get(name).addContact(); }
            if(x==2)
            {   System.out.println("Enter First Name");
                String f=sc.next();
                System.out.println("Enter Last Name");
                String l=sc.next();
                a.get(name).editContact(f, l);
            }
            if(x==3)
            {
                System.out.println("Enter First Name");
                String f=sc.next();
                System.out.println("Enter Last Name");
                String l=sc.next();
                a.get(name).viewContact(f, l);
            }
            if(x==4)
            {
                System.out.println("Enter First Name");
                String f=sc.next();
                System.out.println("Enter Last Name");
                String l=sc.next();
                a.get(name).deleteContact(f, l);

            }
            if(x==6)
            {
                System.out.println("Enter the city ");
                String city=sc.next();

                List<Contact> b = new ArrayList<>();
                for( Map.Entry<String,AddressBook> i :a.entrySet()) {
                    b.addAll(i.getValue().searchCity(city));     //getKey ,getValue
                }
                if(b.size() == 0)
                { System.out.println("No contacts");}
                else{
                    for(int k=0;k<b.size();k++)
                    {
                        System.out.println(b.get(k).first + " "+b.get(k).last );
                    }
                    System.out.println("Number of people belonging to city " +city+ "is "+ b.size());
                }
            }
            if(x==7)
            { System.out.println("Enter the state ");
                String state=sc.next();

                List<Contact> b = new ArrayList<>();
                for( Map.Entry<String,AddressBook> i :a.entrySet()) {
                    b.addAll(i.getValue().searchState(state));
                }
                if(b.size() == 0)
                { System.out.println("No contacts");}
                else{
                    for(int k=0;k<b.size();k++)
                    {
                        System.out.println(b.get(k).first + " "+b.get(k).last);
                    }
                    System.out.println("Number of people belonging to state " +state+ "is "+ b.size());
                }
            }
            if(x==8){
                List<Contact> b = new ArrayList<>();
                for( Map.Entry<String,AddressBook> i :a.entrySet()) { b.addAll(i.getValue().sort());}
                for(int k=0;k<b.size();k++){ System.out.println(b.get(k).fullName); }
            }
            if(x==9){
                List<Contact> b = new ArrayList<>();
                for( Map.Entry<String,AddressBook> i :a.entrySet()) {b.addAll(i.getValue().sortCity());}
                for(int k=0;k<b.size();k++){ System.out.println(b.get(k).fullName + " of " +b.get(k).city); }
            }
            if(x==10){
                List<Contact> b = new ArrayList<>();
                for( Map.Entry<String,AddressBook> i :a.entrySet()) {b.addAll(i.getValue().sortState());}
                for(int k=0;k<b.size();k++){ System.out.println(b.get(k).fullName + " of " +b.get(k).state); }
            }
        }
        sc.close();
    }
}




