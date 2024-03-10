package com.esunbank.Charlie.backend.common.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esunbank.Charlie.backend.common.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Procedure(name = "InsertUser", outputParameterName = "Success")
    Integer InsertUser(@Param("PhoneNumber") String phoneNumber, 
                    @Param("Password") String password, 
                    @Param("UserName") String userName, 
                    @Param("RegistrationTime") LocalDateTime registrationTime);

    @Procedure(name = "findByPhoneNumber")
    Integer findByPhoneNumber(@Param("PhoneNumber") String phoneNumber);
    
    User findByPhoneNumberAndPassword(String phoneNumber, String password);

    

}
