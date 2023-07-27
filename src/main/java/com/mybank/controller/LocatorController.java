package com.mybank.controller;

import java.util.List;

import com.mybank.model.LocationWrapper;
import com.mybank.service.LocatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class LocatorController {

    @GetMapping("/bank-locations")
    public ResponseEntity<List<LocationWrapper>> getBankLocations(@RequestParam String postCode, @RequestParam String serviceType) {
        try {
            LocatorService locatorService = new LocatorService();
            return new ResponseEntity<>(locatorService.getLocations(postCode, serviceType), HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
