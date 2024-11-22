package com.example.MSAddress.controllers;

import com.example.MSAddress.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private com.example.MSAddress.repositories.AddressRepository addressRepository;

    @Autowired
    private com.example.MSAddress.services.AddressService addressService;

    @Autowired
    private JwtUtils jwtUtils;






}
