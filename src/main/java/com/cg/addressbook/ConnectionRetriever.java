package com.cg.addressbook;
import java.sql.*;
import java.util.*;
public class ConnectionRetriever {
    public Connection getConnection(){
        String url="jdbc:mysql://localhost:3306/address_book_service?useSSL=false";
        String userName="root";
        String password="abhi9p16";
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        listDrivers();
        try{
            System.out.println("Connecting to database:"+url);
            connection=DriverManager.getConnection(url,userName,password);
            System.out.println("Connected "+connection);
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private static void listDrivers(){
        Enumeration<Driver> driverList=DriverManager.getDrivers();
        while (driverList.hasMoreElements()){
            Driver driverClass=(Driver)driverList.nextElement();
            System.out.println(" "+driverClass.getClass().getName());
        }
    }
}
