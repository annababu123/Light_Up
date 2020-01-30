package com.sdc.aisat.annajijo.lightup.my_class;

/**
 * Created by Anna Babu on 2/25/2017.
 */
public class PersonalRecord {

    String key, uid, firstName, lastName, bloodGroup, email;

    public PersonalRecord() {
    }

    public PersonalRecord(String key, String uid, String firstName, String lastName, String bloodGroup, String email ) {
        this.key = key;
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bloodGroup = bloodGroup;
        this.email = email;
    }


    public String getKey() {
        return key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}
