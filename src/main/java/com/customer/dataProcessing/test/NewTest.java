//package com.customer.dataProcessing.test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//@Service
//public class NewTest {
//
//    @Autowired
//    ProductRepository productRepository;
//
//
//    public static void main(String[] args) throws FileNotFoundException {
//        NewTest n = new NewTest();
////        n.test();
//        validateNumber("1 555 ");
//    }
//
//    String startTime;
//    String exception;
//    String duplicket;
//    String endTime;
//
//    Long count = 0L;
//    Long duplicketCount = 0L;
//    int totalLines = 0;
//    public void test() {
//        startTime= String.valueOf(new Date());
//        try {
//            List<String> productLines = Files.readAllLines(java.nio.file.Paths.get("D:/Exam_17_11_2022/1M-customers.txt"), StandardCharsets.UTF_8);
//            List<ProductTest> productList= new ArrayList<>();
//            ProductTest product = new ProductTest();
//            totalLines= productLines.size();
//            for (String line : productLines) {
//                product = new ProductTest();
//                String[] tokens = line.split(",");
//                try {
//                product.setFirstName(tokens[0]);
//                product.setLastName(tokens[1]);
//                product.setState(tokens[2]);
//                product.setCity(tokens[3]);
//                product.setPostCode(tokens[4]);
//                product.setPhoneNumber(tokens[5]);
//                product.setMail(tokens[6]);
//                product.setMail(tokens.length > 7?tokens[7]:"");
//                    count++;
//                   if (!productList.contains(product)){
//                       productList.add(product);
//                   }else {
//                       duplicketCount++;
//                       duplicket = duplicket +"\r\n"+ product.toString();
//                   }
////                System.out.println(product);
////                    productRepository.save(product);
//                } catch (Exception e) {
//                    exception = exception+"\r\n"+ Arrays.toString(tokens);
//                    System.out.println(Arrays.toString(tokens));
//                    e.printStackTrace();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        endTime = String.valueOf(new Date());
//        System.out.println("Start Time: "+startTime +"\r\n" + "End Time: " +endTime +"\r\n"+ "Total Lines: " +totalLines +"\r\n"+ "Total count: " +count);
//        System.out.println("-----------------------------------------------");
//        System.out.println(exception);
//        System.out.println("---------------------duplicket--------------------------");
//        System.out.println(duplicket);
//    }
//
//    private static void validateNumber( String number)
//    {
//        String regexStr = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";
//
//        Pattern pattern = Pattern.compile(regexStr);
//        Matcher matcher = pattern.matcher(number);
//
//        if (matcher.matches()) {
//            System.out.println("Phone Number Valid");
//        }
//        else
//        {
//            System.out.println("Phone Number must be in the form XXX-XXXXXXX");
//        }
//    }
//}