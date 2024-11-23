package com.example.MSAddress.controllers;

import com.example.MSAddress.dtos.UpdateAddressRequest;
import com.example.MSAddress.models.Address;
import com.example.MSAddress.services.AddressService;
import com.example.MSAddress.services.GeocodingService;
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

    @Autowired
    private GeocodingService geocodingService;

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        try {
            // Calculer les coordonnées GPS
            double[] coordinates = geocodingService.getCoordinates(
                    address.getNumber(),
                    address.getStreet(),
                    address.getCity(),
                    address.getPostalCode(),
                    "France"
            );

            // Définir les coordonnées dans l'objet Address
            address.setLatitude(coordinates[0]);
            address.setLongitude(coordinates[1]);

            // Sauvegarder l'adresse
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

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        try {
            Address address = addressService.getAddressById(id);
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddressById(@PathVariable Long id, @RequestBody UpdateAddressRequest addressDetails) {
        try {
            Address updatedAddress = addressService.updateAddressById(id, addressDetails);
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to update address", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable Long id) {
        try {
            addressService.deleteAddressById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to delete address", e);
        }
    }
}
