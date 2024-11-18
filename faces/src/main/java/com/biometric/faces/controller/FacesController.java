package com.biometric.faces.controller;

import com.biometric.faces.model.Faces;
import com.biometric.faces.service.FacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value="biometric/{companyName}/faces")
public class FacesController {

    @Autowired
    private FacesService facesService;

    @GetMapping(value="/{price}/{technology}")
    public ResponseEntity<String/*List<Faces>*/> getFaces(
            @PathVariable("companyName") String companyName,
            @PathVariable("price") double price,
            @PathVariable("technology") String technology) {
                //List<Faces> terminals = facesService.getFaces(companyName, price, technology);
                return ResponseEntity.ok(facesService.getFaces(companyName, price, technology)/*terminals*/);
    }

    @PostMapping
    public ResponseEntity<String> createFaces(
            @PathVariable("companyName") String companyName,
            /*@PathVariable("modelName") String modelName,
            @PathVariable("modelCode") String modelCode,
            @PathVariable("price") double price,
            @PathVariable("accuracy") double accuracy,
            @PathVariable("energyConsumption") double energyConsumption,
            @PathVariable("technology") String technology,
            @PathVariable("speed") double speed,*/
            @RequestBody Faces request,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale) {
                return ResponseEntity.ok(facesService.createFaces(request, companyName, locale));
    }
    /*, modelName, modelCode, technology,
                        energyConsumption, price, accuracy, speed*/

    @PutMapping(value="/{modelCode}")
    public ResponseEntity<String> modifyFaces(
            @PathVariable("companyName") String companyName,
            @PathVariable("modelCode") String modelCode,
            @RequestBody Faces request,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale) {
                return ResponseEntity.ok(facesService.modifyFaces(request, companyName, modelCode, locale));
    }

    @DeleteMapping(value="/{modelCode}")
    public ResponseEntity<String> deleteFaces(
            @PathVariable("modelCode") String modelCode,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale) {
                return ResponseEntity.ok(facesService.deleteFaces(modelCode, locale));
    }
}
