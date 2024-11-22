package com.example.MSAddress.services;

import com.example.MSAddress.models.Address;
import com.example.MSAddress.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;


    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }


    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }


    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }


    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
