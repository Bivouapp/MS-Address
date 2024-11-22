package com.example.MSAddress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<com.example.MSAddress.models.Address, Long> {

    List<com.example.MSAddress.models.Address> findAll();

}
