package com.customer.dataProcessing.repository;

import com.customer.dataProcessing.entitys.InvalidCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidCustomerRepository  extends JpaRepository<InvalidCustomer,Long> {

}
