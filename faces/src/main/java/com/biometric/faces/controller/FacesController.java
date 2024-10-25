package com.biometric.faces.controller;

import com.biometric.faces.model.Faces;
import com.biometric.faces.service.FacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="biometric/{companyName}/faces")
public class FacesController {

    @Autowired
    private FacesService facesService;

    @GetMapping(value="/{price}/{technology}")
    public ResponseEntity<Faces> getFaces(
            @PathVariable("companyName") String companyName,
            @PathVariable("price") double price,
            @PathVariable("technology") String technology) {

        Faces fractures = facesService.getFaces(companyName, price, technology);
        return ResponseEntity.ok(fractures);
    }

    @PostMapping
    public ResponseEntity<String> createFaces(
            @PathVariable("companyName") String companyName,
            @RequestBody Faces request) {
                return ResponseEntity.ok(facesService.createFaces(request, companyName));
    }
}
