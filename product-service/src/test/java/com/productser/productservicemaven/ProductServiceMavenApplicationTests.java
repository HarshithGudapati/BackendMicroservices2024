package com.productser.productservicemaven;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productser.productservicemaven.dto.ProductRequest;
import com.productser.productservicemaven.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceMavenApplicationTests {

 @Container
 static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
@Autowired
private MockMvc mockMvc;
@Autowired
private ObjectMapper objectMapper;
@Autowired
private ProductRepository productRepository;
 static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
     dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
 }

 @BeforeEach
 public void removeAll(){
     productRepository.deleteAll();
   }

    @Test
    void shouldCreateProduct() throws Exception {
           ProductRequest productRequest = getProductRequest();
         String productRequestString =  objectMapper.writeValueAsString(productRequest);
       mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
               .contentType(MediaType.APPLICATION_JSON)
               .content(productRequestString))
               .andExpect(status().isCreated());
        Assertions.assertEquals(1, productRepository.findAll().size());

    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name("iphone13")
                .description("iphone13")
                .price(BigDecimal.valueOf(1200))
                .build();
    }

}
