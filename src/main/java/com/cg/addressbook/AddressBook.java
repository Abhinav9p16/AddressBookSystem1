package com.cg.addressbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class AddressBook {
    ConnectionRetriever con=new ConnectionRetriever();
    PreparedStatement Statement;

    public  List<Contact> readData(){
        String sql="select * from contact c, addressbook a where c.contact_id=a.contact_id ;";
        List<Contact> arr=new ArrayList<Contact>();
        try
        {
            Connection connection=con.getConnection();
            Statement=connection.prepareStatement(sql);
            ResultSet result=Statement.executeQuery();
            while(result.next())
            {
                Contact c=new Contact();
                c.first=result.getString("first_name");
                c.last=result.getString("last_name");
                c.address=result.getString("address");
                c.email=result.getString("email");
                c.phno=result.getString("phone_no");
                c.city=result.getString("city");
                c.state=result.getString("state");
                c.zip=result.getString("zip");
                arr.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arr;
    }
    public void updateContact(String field,String data,int contact_id)
    {
        String sql="update contact c, addressbook a set "+field+"=? where c.contact_id=a.contact_id and c.contact_id=?;";
        try
        {
            Connection connection=con.getConnection();
            Statement=connection.prepareStatement(sql);;
            Statement.setString(1,data);
            Statement.setInt(2,contact_id);
            Statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
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
        if (checkDup(a)) System.out.println("Duplicate Contact");
        else c.add(a);
    }

    public void editContact(String f, String l) {
        for (int i = 0; i < c.size(); i++) {
            if (nameCheck(f, l, i)) {
                c.get(i).editContact();
                break;
            }
        }
    }

    public void viewContact(String f, String l) {
        for (int i = 0; i < c.size(); i++) {
            if (nameCheck(f, l, i)) {
                c.get(i).viewContact();
                break;
            }
        }
    }

    public void deleteContact(String f, String l) {
        for (int i = 0; i < c.size(); i++) {
            if (nameCheck(f, l, i)) {
                c.remove(i);
                break;
            }
        }
    }
}

