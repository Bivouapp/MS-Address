package com.example.MSAddress.services;

import com.example.MSAddress.dtos.UpdateAddressRequest;
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

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found with ID: " + id));
    }

    public Address updateAddressById(Long id, UpdateAddressRequest addressDetails) {
        Address existingAddress = getAddressById(id); // Récupère l'entité existante

        if (addressDetails.getNumber() != null) {
            existingAddress.setNumber(addressDetails.getNumber());
        }
        if (addressDetails.getStreet() != null) {
            existingAddress.setStreet(addressDetails.getStreet());
        }
        if (addressDetails.getCity() != null) {
            existingAddress.setCity(addressDetails.getCity());
        }
        if (addressDetails.getPostalCode() != null) {
            existingAddress.setPostalCode(addressDetails.getPostalCode());
        }
        if (addressDetails.getLatitude() != null) {
            existingAddress.setLatitude(addressDetails.getLatitude());
        }
        if (addressDetails.getLongitude() != null) {
            existingAddress.setLongitude(addressDetails.getLongitude());
        }

        return addressRepository.save(existingAddress);
    }


    public void deleteAddressById(Long id) {
        Address existingAddress = getAddressById(id);
        addressRepository.delete(existingAddress);
    }
}
