package com.sdc.aisat.annajijo.lightup.main_things;

/**
 * Created by Anna on 02-03-2018.
 */

public class User {
    public String firstname;
    public String lastname;
    public String email;
    public String password;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String firstname,String lastname,String password, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
    }
}
