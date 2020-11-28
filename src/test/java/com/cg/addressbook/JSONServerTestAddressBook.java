package com.cg.addressbook;

import io.restassured.http.ContentType;
import org.junit.*;
import io.restassured.*;

import java.util.*;

public class JSONServerTestAddressBook {
    @Before
    public void setup() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;

    }
    @Test
    public void givenContact_WhenWrittenToJsonServer_ShouldReturn() {
        AddressBook a = new AddressBook();
        List<Contact> contacts = a.readData();
        for (Contact contact : contacts) {
            HashMap<String, String> map = new HashMap<>();
            String fname = contact.first;
            String lname = contact.last;
            String phNo = contact.phno;
            String email = contact.email;
            String date = contact.DOJ;
            map.put("firstName", fname);
            map.put("lastName", lname);
            map.put("Phone", phNo);
            map.put("Email", email);
            map.put("Date", date);
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(map)
                    .when()
                    .post("/contacts/create");
        }
    }
}
