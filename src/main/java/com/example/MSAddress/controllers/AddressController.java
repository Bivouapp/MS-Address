package com.example.MSAddress.controllers;

import com.example.MSAddress.models.Address;
import com.example.MSAddress.services.AddressService;
import com.example.MSAddress.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private com.example.MSAddress.repositories.AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        try {
            Address createdAddress = addressService.saveAddress(address);
            return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create address", e);
        }
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
}
