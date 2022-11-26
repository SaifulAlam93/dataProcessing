package com.customer.dataProcessing.entitys;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String state;
    private String city;
    private String postCode;
    //    @Column(unique=true)
    private String phoneNumber;
    //    @Column(unique=true)
    private String mail;
    private String ip;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer user = (Customer) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    @Override
    public String toString(){
        StringBuilder dataBuilder = new StringBuilder();
        appendFieldValue(dataBuilder, String.valueOf(id));
        appendFieldValue(dataBuilder, firstName);
        appendFieldValue(dataBuilder, lastName);
        appendFieldValue(dataBuilder, state);
        appendFieldValue(dataBuilder, city);
        appendFieldValue(dataBuilder, postCode);
        appendFieldValue(dataBuilder, phoneNumber);
        appendFieldValue(dataBuilder, mail);
        appendFieldValue(dataBuilder, ip);


        return dataBuilder.toString();
    }

    private void appendFieldValue(StringBuilder dataBuilder, String fieldValue) {
        if(fieldValue != null) {
            dataBuilder.append(fieldValue).append(",");
        } else {
            dataBuilder.append("").append(",");
        }
    }
}
