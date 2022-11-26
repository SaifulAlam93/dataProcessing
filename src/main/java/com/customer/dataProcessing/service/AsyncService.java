package com.customer.dataProcessing.service;

import com.customer.dataProcessing.entitys.Customer;
import com.customer.dataProcessing.entitys.InvalidCustomer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AsyncService {
    @Value("${file.upload-dir}")
    private String filePath;


    public Customer setCustomer(String[] data) {
        final Customer customer = new Customer();

        customer.setFirstName(data[0]);
        customer.setLastName(data[1]);
        customer.setState(data[2]);
        customer.setCity(data[3]);
        customer.setPostCode(data[4]);
        customer.setPhoneNumber(data[5]);
        customer.setMail(data[6]);
        customer.setIp(data.length > 7 ? data[7] : "");

        return customer;
    }

    public InvalidCustomer setInvalidCustomer(String[] data) {
        final InvalidCustomer invalidCustomer = new InvalidCustomer();

        invalidCustomer.setFirstName(data[0]);
        invalidCustomer.setLastName(data[1]);
        invalidCustomer.setState(data[2]);
        invalidCustomer.setCity(data[3]);
        invalidCustomer.setPostCode(data[4]);
        invalidCustomer.setPhoneNumber(data[5]);
        invalidCustomer.setMail(data[6]);
        invalidCustomer.setIp(data.length > 7 ? data[7] : "");

        return invalidCustomer;
    }
    final static String mailRegexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    final static String usNumberRegexPattern = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";

    public static boolean patternMatches(String phoneNumber, String emailAddress) {

        return Pattern.compile(mailRegexPattern)
                .matcher(emailAddress)
                .matches() && Pattern.compile(usNumberRegexPattern)
                .matcher(phoneNumber)
                .matches();
    }

    @Async
    public CompletableFuture<String> processData2( List<String> productLines, HashMap<String,Customer> mapCustomers, HashMap<String, InvalidCustomer> mapInvalidCustomers) {
        try {
            for (String line : productLines) {
                final String[] data = line.split(",");
//                if (!patternMatches(usNumberRegexPattern, data[5]) || !patternMatches(mailRegexPattern, data[6])) {
                if (patternMatches(data[5], data[6]) ) {
                    mapCustomers.put(data[5] + data[6], setCustomer(data));
                } else {
                    mapInvalidCustomers.put(data[5] + data[6], setInvalidCustomer(data));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture("OK");
    }




    @Async
    public CompletableFuture<String> processData3( List<String> productLines, HashMap<String, Customer> mapCustomers, HashMap<String, InvalidCustomer> mapInvalidCustomers) {
        try {
            for (String line : productLines) {
                final String[] data = line.split(",");
//                if (!patternMatches(usNumberRegexPattern, data[5]) || !patternMatches(mailRegexPattern, data[6])) {
                if (patternMatches(data[5], data[6]) ) {
                    mapCustomers.put(data[5] + data[6], setCustomer(data));
                } else {
                    mapInvalidCustomers.put(data[5] + data[6], setInvalidCustomer(data));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture("OK");
    }



    @Async
    public void writeVCToCsv(List<Customer> validCustomerList) throws IOException {
        File file = new File(filePath+"/validCustomer");
        if (!file.exists()) {
            file.mkdirs();
                PrintWriter writer = new PrintWriter(file+"/"+System.currentTimeMillis()+".csv");
//        writer.println("header");
                for (Customer customer : validCustomerList) {
                    writer.println(customer.toString());
                }
                writer.close();
            }
    }
    @Async
    public void writeInVCToCsv(List<InvalidCustomer> InvalidCustomer) throws IOException {
        File file = new File(filePath+"/invalidCustomer");
        if (!file.exists()){
            file.mkdirs();
                PrintWriter writer = new PrintWriter(file+"/"+System.currentTimeMillis()+".csv");
//        writer.println("header");
                for (InvalidCustomer invalidCustomer : InvalidCustomer) {
                    writer.println(InvalidCustomer.toString());
                }
                writer.close();
        }
    }
}
