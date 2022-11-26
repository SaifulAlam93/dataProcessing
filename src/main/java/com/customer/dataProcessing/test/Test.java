//package com.customer.dataProcessing.test;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.text.DecimalFormat;
//import java.text.NumberFormat;
//import java.util.Scanner;
//
//public class Test {
//
//    public static void main(String[] args) throws FileNotFoundException {
////        Scanner input = new Scanner(new File("Stock.txt"));
//        Scanner input = new Scanner(new File("G:/My_PROJECT/test.txt"));
//        input.useDelimiter(",\n");
//
//        Product[] products = new Product[0];
//        while (input.hasNext()) {
//            System.out.println();
//            String id = input.next(input.next());
//            String department = input.next();
//            String name = input.next();
////            String price = Double.valueOf(input.next().substring(1));
//            String price = input.next();
//            String stock = input.next();
//
//            Product newProduct = new Product(name, price, department, id, stock);
//            products = addProduct(products, newProduct);
//        }
//
//        for (Product product : products) {
//            System.out.println(product);
//        }
//    }
//
//    private static Product[] addProduct(Product[] products, Product productToAdd) {
//        Product[] newProducts = new Product[products.length + 1];
//        System.arraycopy(products, 0, newProducts, 0, products.length);
//        newProducts[newProducts.length - 1] = productToAdd;
//
//        return newProducts;
//    }
//
//    public static class Product {
//        protected String name;
//        protected String price;
//        protected String department;
//        protected String id;
//        protected String stock;
//
//        private static NumberFormat formatter = new DecimalFormat("#0.00");
//
//        public Product(String n, String p, String d, String i, String s) {
//            name = n;
//            price = p;
//            department = d;
//            id = i;
//            stock = s;
//        }
//
//        @Override
//        public String toString() {
//            return String.format("ID: %d\r\nDepartment: %s\r\nName: %s\r\nPrice: %s\r\nStock: %d\r\n",
//                    id, department, name, formatter.format(price), stock);
//        }
//
//    }
//}