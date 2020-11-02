package com.cg.addressbook;
import java.sql.*;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Address {

    public static ArrayList<Contact> readAll() {
        ArrayList<Contact> c = new ArrayList<Contact>();
        try {
            FileInputStream fin = new FileInputStream("/Users/abhinavthakur/Desktop/adbook.txt");
            //FileInputStream fin = new FileInputStream("C:/Users/aachm/Desktop/adbook.txt");
            ObjectInputStream obj = new ObjectInputStream(fin);
            while (true) {
                try {
                    c.add((Contact) obj.readObject());
                } catch (IOException e) {
                    break;
                }
            }
            obj.close();
            fin.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void writeAll(ArrayList<Contact> c) {
        try {
            FileOutputStream fout = new FileOutputStream("/Users/abhinavthakur/Desktop/adbook.txt");
            //FileOutputStream fout = new FileOutputStream("C:/Users/aachm/Desktop/adbook.txt");
            ObjectOutputStream obj = new ObjectOutputStream(fout);
            for (Contact k : c) obj.writeObject(k);
            obj.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Contact> readAllCSV() {
        ArrayList<Contact> c = new ArrayList<Contact>();
        try {
            FileReader fin = new FileReader("/Users/abhinavthakur/Desktop/adbook.csv");
            //FileReader fin = new FileReader("C:/Users/aachm/Desktop/adbook.csv");
            CSVReader csv = new CSVReaderBuilder(fin).withSkipLines(1).build();
            while (true) {
                try {
                    String[] s = csv.readNext();
                    Contact temp = new Contact();
                    temp.setContact(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7]);
                    c.add(temp);
                } catch (Exception e) {
                    break;
                }
            }
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void writeAllCSV(ArrayList<Contact> c) {
        try {
            FileWriter fout = new FileWriter("/Users/abhinavthakur/Desktop/adbook.csv");
            //FileWriter fout = new FileWriter("C:/Users/aachm/Desktop/adbook.csv");
            CSVWriter csv = new CSVWriter(fout, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            csv.writeNext(new String[]{"FName", "LName", "Address", "City", "State", "Zip", "Phno", "Email"});
            for (Contact k : c) csv.writeNext(k.getContactDetails());
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Contact> readAllJSON() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/abhinavthakur/Desktop/adbook.json"));
            // BufferedReader br = new BufferedReader(new FileReader("C:/Users/aachm/Desktop/adbook.json"));
            Gson gson = new Gson();
            Contact[] list = gson.fromJson(br, Contact[].class);
            return Arrays.asList(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Contact>();
    }

    public static void writeAllJSON(ArrayList<Contact> c) {
        Gson gson = new Gson();
        try {
            FileWriter fout = new FileWriter("/Users/abhinavthakur/Desktop/adbook.json");
            //FileWriter fout = new FileWriter("C:/Users/aachm/Desktop/adbook.json");
            String s = gson.toJson(c); // pehle for loop nmei ek ek contact run karaa rahe the aur ab
            fout.write(s);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        HashMap<String, AddressBook> a = new HashMap<String, AddressBook>();

        System.out.println("Welcome to Address Book program");
        Scanner sc = new Scanner(System.in);
        int x = 0;
        System.out.println("Write the number of the Address Books ");
        int z = sc.nextInt();
        for (int i = 0; i < z; i++) {
            System.out.println("Enter name of ADDRESS book");
            String name = sc.next();
            AddressBook b = new AddressBook();
            a.put(name, b);
        }
        while (x != 5) {
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
            System.out.println("11.WRITE ALL");
            System.out.println("12.READ ALL");
            System.out.println("13.WRITE ALL (CSV)");
            System.out.println("14.READ ALL (CSV)");
            System.out.println("15.WRITE ALL (JSON)");
            System.out.println("16.READ ALL (JSON)");
            x = sc.nextInt();
            String name = new String();
            if (x < 5) {
                System.out.println("In which address book");
                name = sc.next();
            }
            if (x == 1) {
                a.get(name).addContact();
            }
            if (x == 2) {
                System.out.println("Enter First Name");
                String f = sc.next();
                System.out.println("Enter Last Name");
                String l = sc.next();
                a.get(name).editContact(f, l);
            }
            if (x == 3) {
                System.out.println("Enter First Name");
                String f = sc.next();
                System.out.println("Enter Last Name");
                String l = sc.next();
                a.get(name).viewContact(f, l);
            }
            if (x == 4) {
                System.out.println("Enter First Name");
                String f = sc.next();
                System.out.println("Enter Last Name");
                String l = sc.next();
                a.get(name).deleteContact(f, l);
            }
            if (x == 6) {
                System.out.println("Enter the city ");
                String city = sc.next();
                List<Contact> b = new ArrayList<>();
                for (Map.Entry<String, AddressBook> i : a.entrySet()) {
                    b.addAll(i.getValue().searchCity(city));
                }     //getKey ,getValue
                if (b.size() == 0) {
                    System.out.println("No contacts");
                } else {
                    for (int k = 0; k < b.size(); k++) {
                        System.out.println(b.get(k).first + " " + b.get(k).last);
                    }
                    System.out.println("Number of people belonging to city " + city + "is " + b.size());
                }
            }
            if (x == 7) {
                System.out.println("Enter the state ");
                String state = sc.next();
                List<Contact> b = new ArrayList<>();
                for (Map.Entry<String, AddressBook> i : a.entrySet()) {
                    b.addAll(i.getValue().searchState(state));
                }
                if (b.size() == 0) {
                    System.out.println("No contacts");
                } else {
                    for (int k = 0; k < b.size(); k++) {
                        System.out.println(b.get(k).first + " " + b.get(k).last);
                    }
                    System.out.println("Number of people belonging to state " + state + "is " + b.size());
                }
            }
            if (x == 8) {
                List<Contact> b = new ArrayList<>();
                for (Map.Entry<String, AddressBook> i : a.entrySet()) {
                    b.addAll(i.getValue().sort());
                }
                for (int k = 0; k < b.size(); k++) {
                    System.out.println(b.get(k).fullName);
                }
            }
            if (x == 9) {
                List<Contact> b = new ArrayList<>();
                for (Map.Entry<String, AddressBook> i : a.entrySet()) {
                    b.addAll(i.getValue().sortCity());
                }
                for (int k = 0; k < b.size(); k++) {
                    System.out.println(b.get(k).fullName + " of " + b.get(k).city);
                }
            }
            if (x == 10) {
                List<Contact> b = new ArrayList<>();
                for (Map.Entry<String, AddressBook> i : a.entrySet()) {
                    b.addAll(i.getValue().sortState());
                }
                for (int k = 0; k < b.size(); k++) {
                    System.out.println(b.get(k).fullName + " of " + b.get(k).state);
                }
            }
            if (x == 11) {
                ArrayList<Contact> temp = new ArrayList<Contact>();
                for (Map.Entry<String, AddressBook> k : a.entrySet()) temp.addAll(k.getValue().getContacts());
                writeAll(temp);
            }
            if (x == 12) {
                for (Contact k : readAll()) k.viewContact();
            }
            if (x == 13) {
                ArrayList<Contact> temp = new ArrayList<Contact>();
                for (Map.Entry<String, AddressBook> k : a.entrySet()) temp.addAll(k.getValue().getContacts());
                writeAllCSV(temp);
            }
            if (x == 14) {
                for (Contact k : readAllCSV()) k.viewContact();
            }
            if (x == 15) {
                ArrayList<Contact> temp = new ArrayList<Contact>();
                for (Map.Entry<String, AddressBook> k : a.entrySet()) temp.addAll(k.getValue().getContacts());
                writeAllJSON(temp);
            }
            if (x == 16) {
                for (Contact k : readAllJSON()) k.viewContact();
            }
        }
        sc.close();
    }
}