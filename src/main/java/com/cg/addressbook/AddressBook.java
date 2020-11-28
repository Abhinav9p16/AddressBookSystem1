package com.cg.addressbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//import io.restassured.*;


class AddressBook {
    ConnectionRetriever con = new ConnectionRetriever();
    PreparedStatement Statement;


    ArrayList<Contact> c = new ArrayList<Contact>();

    public ArrayList<Contact> getContacts() {
        return c;
    }

    public boolean checkDup(Contact x) {
        return (c.stream().anyMatch(d -> d.equals(x)));
    }

    public List<Contact> searchCity(String city) {
        return (c.stream().filter(d -> d.city.equalsIgnoreCase(city)).collect(Collectors.toList()));
    }

    public List<Contact> searchState(String state) {
        return (c.stream().filter(d -> d.state.equalsIgnoreCase(state)).collect(Collectors.toList()));
    }

    public List<Contact> sort() {
        return c.stream().sorted((p1, p2) -> p1.fullName.compareTo(p2.fullName)).collect(Collectors.toList());
    }

    public List<Contact> sortCity() {
        return c.stream().sorted((p1, p2) -> p1.city.compareTo(p2.city)).collect(Collectors.toList());
    }

    public List<Contact> sortState() {
        return c.stream().sorted((p1, p2) -> p1.state.compareTo(p2.state)).collect(Collectors.toList());
    }

    public boolean nameCheck(String f, String l, int i) {
        return (c.get(i).first.equalsIgnoreCase(f) && c.get(i).last.equalsIgnoreCase(l));
    }

    public void addContact() {
        Contact a = new Contact();
        a.addContact();
        if (checkDup(a)) System.out.println("Duplicate Contact!!!");
        else c.add(a);
    }

    public void editContact(String f, String l) {
        for (int i = 0; i < c.size(); i++) {
            if (nameCheck(f, l, i)) {
                c.get(i).editContact();
                break;
            } else System.out.println("Contact not found!!!");
        }
    }

    public void viewContact(String f, String l) {
        for (int i = 0; i < c.size(); i++) {
            if (nameCheck(f, l, i)) {
                c.get(i).viewContact();
                break;
            } else System.out.println("Contact not found!!!");
        }
    }

    public void deleteContact(String f, String l) {
        for (int i = 0; i < c.size(); i++) {
            if (nameCheck(f, l, i)) {
                c.remove(i);
                break;
            } else System.out.println("Contact not found!!!");
        }
    }


    public List<Contact> readData() {
        String sql = "select * from addressbook ;";
        List<Contact> arr = new ArrayList<Contact>();
        try {
            Connection connection = con.getConnection();
            Statement = connection.prepareStatement(sql);
            ResultSet result = Statement.executeQuery();
            while (result.next()) {
                Contact c = new Contact();
                c.first = result.getString("first_name");
                c.last = result.getString("last_name");
                c.address = result.getString("address");
                c.email = result.getString("email");
                c.phno = result.getString("phone_no");
                c.city = result.getString("city");
                c.state = result.getString("state");
                c.zip = result.getString("zip");
                arr.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arr;
    }

    public void updateContact(String field, String data, String first, String last) {
        String sql = "update addressbook set " + field + "=? where first_name = ? and last_name=? ;";
        try {
            Connection connection = con.getConnection();
            Statement = connection.prepareStatement(sql);
            Statement.setString(1, data);
            Statement.setString(2, first);
            Statement.setString(3, last);
            Statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int findDoj(String beg, String end) {
        int count = 0;
        String sql = "select * from addressbook where date_added between ? and ?;";
        try {
            Connection connection = con.getConnection();
            Statement = connection.prepareStatement(sql);
            Statement.setString(1, beg);
            Statement.setString(2, end);
            ResultSet r = Statement.executeQuery();
            while (r.next()) {
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public int FetchField(String field, String data) {
        int count = 0;
        String sql = "select * from addressbook where " + field + "=?";
        try {
            Connection connection = con.getConnection();
            Statement = connection.prepareStatement(sql);
            Statement.setString(1, data);
            ResultSet r = Statement.executeQuery();
            while (r.next()) {
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;

    }

    public void Insert(Contact c) {
        String sql = "insert into Addressbook values (?,?,?,?,?,?,?,?,?);";
        try {
            Connection connection = con.getConnection();
            Statement = connection.prepareStatement(sql); // isko java se convert karke sql readable format pe 39:00
            Statement.setString(1, c.first);
            Statement.setString(2, c.last);
            Statement.setString(3, c.address);
            Statement.setString(4, c.city);
            Statement.setString(5, c.state);
            Statement.setString(6, c.zip);
            Statement.setString(7, c.phno);
            Statement.setString(8, c.email);
            Statement.setString(9, c.DOJ);
            Statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Thread> MultithreadedInsert(ArrayList<Contact> list) {
        Connection connection = con.getConnection();
        ArrayList<Thread> threads = new ArrayList<>();
        for (Contact c : list) {
            Runnable task = () -> {
                try {
                    String sql = "insert into addressbook values (?,?,?,?,?,?,?,?,?);";
                    Statement = connection.prepareStatement(sql);
                    Statement.setString(1, c.first);
                    Statement.setString(2, c.last);
                    Statement.setString(3, c.address);
                    Statement.setString(4, c.city);
                    Statement.setString(5, c.state);
                    Statement.setString(6, c.zip);
                    Statement.setString(7, c.phno);
                    Statement.setString(8, c.email);
                    Statement.setString(9, c.DOJ);
                    Statement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            };
            Thread t = new Thread(task);
            threads.add(t);
        }
        return threads;
    }
}

