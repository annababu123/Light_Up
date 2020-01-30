package com.sdc.aisat.annajijo.lightup.my_class;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Anna Babu on 2/26/2017.
 */
public class PostRecord {

    String name, email, uid, push;

    float distance,calory;
    String ddmmyy;
    String t;

    String blood,urgency,country,state,city,hospital,relation,contact,info;

    public PostRecord(String name, String email, String uid, String push, float distance, float calory, String ddd, String ttt) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.push = push;
        this.distance = distance;
        this.calory = calory;
        this.ddmmyy = ddd;
        this.t = ttt;
   }

   public PostRecord() {
    }

    public void setPush(String push) {
        this.push = push;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

   public String getUid() {
        return uid;
    }

    public String getPush() {
        return push;
    }

    public float getDistance() {
        return distance;
    }

    public float getCalory() {
        return calory;
    }

    public String getDate() {
        return ddmmyy;
    }

    public String getTime() {
        return t;
    }


   }
