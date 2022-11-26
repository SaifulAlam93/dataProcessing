package com.customer.dataProcessing.repository;

import com.customer.dataProcessing.entitys.Customer;
import com.customer.dataProcessing.entitys.InvalidCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Long> {

}
