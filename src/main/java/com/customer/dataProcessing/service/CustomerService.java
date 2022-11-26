package com.customer.dataProcessing.service;

import com.customer.dataProcessing.dto.Response;
import com.customer.dataProcessing.entitys.Customer;
import com.customer.dataProcessing.entitys.InvalidCustomer;
import com.customer.dataProcessing.repository.CustomerRepository;
import com.customer.dataProcessing.repository.InvalidCustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private InvalidCustomerRepository invalidCustomerRepository;
    @Autowired
    private AsyncService asyncService;
    Logger logger = LoggerFactory.getLogger(CustomerService.class);


    public void saveData(final MultipartFile file) throws Exception {
        try {
            long start = System.currentTimeMillis();
            System.out.println("Number of threads before execution: " + Thread.activeCount());
            List<Customer> validCustomer ;
            List<InvalidCustomer> invalidCustomers ;
            HashMap<String,Customer> mapCustomers=new HashMap<String,Customer>();
            HashMap<String, InvalidCustomer> mapInvalidCustomers=new HashMap<String,InvalidCustomer>();

//            List<String> productLines = Files.readAllLines(java.nio.file.Paths.get(), StandardCharsets.UTF_8);
            List<String> productLines ;
            try (BufferedReader buffer = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                productLines = buffer.lines()
                        .collect(Collectors.toList());
            }
            int size = productLines.size();
            size = size/100;
            Collection<List<String>> listList = partitionBasedOnSize(productLines,size);
            
            for (List<String> subList:listList
            ) {
                asyncService.processData2(subList, mapCustomers, mapInvalidCustomers);
            }

            System.out.println("Number of threads after data processing-- " + Thread.activeCount());

            Thread.sleep(5000);
            Collection<Customer> mapCustomersValues = mapCustomers.values();
            validCustomer = new ArrayList<>(mapCustomersValues);
            Collection<InvalidCustomer> mapInvalidCustomersValues = mapInvalidCustomers.values();
            invalidCustomers = new ArrayList<>(mapInvalidCustomersValues);

            validCustomer = customerRepository.saveAll(validCustomer);
            invalidCustomers = invalidCustomerRepository.saveAll(invalidCustomers);
            logger.info("saving list of validCustomer of size {}", validCustomer.size(), "" + Thread.currentThread().getName());
            logger.info("saving list of invalidCustomers of size {}", invalidCustomers.size(), "" + Thread.currentThread().getName());
            long end = System.currentTimeMillis();
            logger.info("Total time {}", (end - start)/1000/60," Min");
        }catch (Exception e){
            e.printStackTrace();

        }
    }
    
    static <T> Collection<List<T>> partitionBasedOnSize(List<T> inputList, int size) {
        final AtomicInteger counter = new AtomicInteger(0);
        return inputList.stream()
                .collect(Collectors.groupingBy(s -> counter.getAndIncrement()/size))
                .values();
    }

    @Async
    public CompletableFuture<List<Customer>> findAllCustomers(){
        logger.info("get list of user by "+Thread.currentThread().getName());
        List<Customer> validCustomer=customerRepository.findAll();
        return CompletableFuture.completedFuture(validCustomer);
    }


    public CompletableFuture<Response> getCSV() throws IOException {
        logger.info("get list of user by "+Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        long startingThread = Thread.activeCount();

        List<Customer> validCustomer=customerRepository.findAll();
        if (validCustomer.size()>100000){
            int fileSize = validCustomer.size()/100000;
            Collection<List<Customer>> listList = partitionBasedOnSize(validCustomer,fileSize);
            for (List<Customer> customerList : listList
            ){
                asyncService.writeVCToCsv(customerList);
            }
        }else {
            asyncService.writeVCToCsv(validCustomer);
        }

        List<InvalidCustomer> invalidCustomers=invalidCustomerRepository.findAll();

        if (invalidCustomers.size()>100000){
            int fileSize = invalidCustomers.size()/100000;
            Collection<List<InvalidCustomer>> listList = partitionBasedOnSize(invalidCustomers,fileSize);
            for (List<InvalidCustomer> invalidCustomers1 : listList
            ){
                asyncService.writeInVCToCsv(invalidCustomers1);
            }
        }else {
            asyncService.writeInVCToCsv(invalidCustomers);
        }
        long end = System.currentTimeMillis();
        logger.info("Total time {}", (end - start)/1000/60," Min");
        long endingThread = Thread.activeCount();

        return CompletableFuture.completedFuture(new Response(((end - start)/1000/60),(endingThread-startingThread)));
    }



}
