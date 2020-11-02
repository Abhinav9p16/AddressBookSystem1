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

    public int findDoj(String start, String end) {
        int count=0;
        String sql="select * from address where DOJ between ? and ?;";
        try
        {
            Connection connection=con.getConnection();
            Statement=connection.prepareStatement(sql);;
            Statement.setString(1,start);
            Statement.setString(2,end);
            ResultSet r=Statement.executeQuery();
            while(r.next())
            {
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public int RetriveField(String field, String data) {
        int count = 0;
        String sql = "select * from address where "+field+"=?";
        try {
            Connection connection = con.getConnection();
            Statement = connection.prepareStatement(sql);
            ;
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
    public void Insert(Contact c)
    {
        String sql="insert into contact (id,first_name,last_name,phone_no,email) values (?,?,?,?,?);";
        try {
            int id=0;
            Connection connection = con.getConnection();
            Statement = connection.prepareStatement(sql);
            Statement.setInt(1, c.id);
            Statement.setString(2,c.first);
            Statement.setString(3,c.last);
            Statement.setString(4,c.phno);
            Statement.setString(5,c.email);
            Statement.executeUpdate();
            //getting the contact id which is assigned by auto increment
            Statement = connection.prepareStatement("select contact_id from contact where phone_no=?");
            Statement.setString(1,c.phno);
            ResultSet r=Statement.executeQuery();
            while(r.next())
            {
                id=r.getInt(1);
                break;
            }
            //using contact id to assign address table
            Statement = connection.prepareStatement("insert into address (contact_id,address,city,state,zip,DOJ) values (?,?,?,?,?,?);");
            Statement.setInt(1,id);
            Statement.setString(2,c.address);
            Statement.setString(3,c.city);
            Statement.setString(4,c.state);
            Statement.setString(5,c.zip);
            Statement.setString(6,c.DOJ);
            Statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

