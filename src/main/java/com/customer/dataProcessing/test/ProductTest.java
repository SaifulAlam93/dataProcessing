package com.customer.dataProcessing.test;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

@Data
@Entity
public class ProductTest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long ID;
    protected String firstName;
    protected String lastName;
    protected String state;
    protected String city;
    protected String postCode;
//    @Column(unique=true)
    protected String phoneNumber;
//    @Column(unique=true)
    protected String mail;
    protected String ip;

//    private static NumberFormat formatter = new DecimalFormat("#0.00");

//    public ProductTest(String n, String p, String d, String i, String s) {
//        name = n;
//        price = p;
//        department = d;
//        id = i;
//        stock = s;
//    }

    @Override
    public String toString() {
        return String.format("First Name: %s\r\nLast Nane: %s\r\nState: %s\r\nCity: %s\r\nPost Code: %s\r\nPhone Number: %s\r\nEmail: %s\r\nIp Address: %s\n",
                firstName, lastName, state, city, postCode,phoneNumber,mail,ip);
    }
}