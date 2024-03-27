package com.productser.productservicemaven.repository;

import com.productser.productservicemaven.modal.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
