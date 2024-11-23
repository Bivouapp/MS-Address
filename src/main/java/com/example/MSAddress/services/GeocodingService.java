package com.example.MSAddress.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class GeocodingService {

    private static final String GEOCODING_API_URL = "https://nominatim.openstreetmap.org/search";

    public double[] getCoordinates(String number, String street, String city, String postalCode, String country) {
        RestTemplate restTemplate = new RestTemplate();

        // Construire l'adresse complète
        String address = String.format("%s %s, %s, %s, %s", number, street, city, postalCode, country);

        // Paramètres de la requête
        Map<String, String> params = new HashMap<>();
        params.put("q", address);
        params.put("format", "json");

        // Requête vers l'API Nominatim
        String url = GEOCODING_API_URL + "?q={q}&format={format}";
        Object[] response = restTemplate.getForObject(url, Object[].class, params);

        // Vérifiez si une réponse est retournée
        if (response != null && response.length > 0) {
            Map<String, Object> result = (Map<String, Object>) response[0];
            double latitude = Double.parseDouble((String) result.get("lat"));
            double longitude = Double.parseDouble((String) result.get("lon"));
            return new double[]{latitude, longitude};
        } else {
            throw new RuntimeException("Unable to find coordinates for the address: " + address);
        }
    }
}
