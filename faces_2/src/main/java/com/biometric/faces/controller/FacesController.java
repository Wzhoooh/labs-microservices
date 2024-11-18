package com.biometric.faces.controller;

import com.biometric.faces.model.Faces;
import com.biometric.faces.service.FacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.context.MessageSource;

import java.util.Locale;

@RestController
@RequestMapping(value="biometric/{companyName}/faces")
public class FacesController {

    @Autowired
    private FacesService facesService;
    @Autowired
    MessageSource messages;

    @GetMapping(value="/{modelName}/{price}/{technology}")
    public ResponseEntity<Faces> getFaces(
            @PathVariable("companyName") String companyName,
            @PathVariable("modelName") String modelName,
            @PathVariable("price") double price,
            @PathVariable("technology") String technology,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale) {
                Faces terminal = facesService.getFaces(companyName, modelName, price, technology);
                terminal.add(linkTo(methodOn(FacesController.class)
                        .getFaces(companyName, modelName, price, technology, locale))
                        .withSelfRel(),
                        linkTo(methodOn(FacesController.class)
                                .createFaces(companyName, terminal, null))
                                .withRel(messages.getMessage("faces.get_create.message", null, locale)),
                        linkTo(methodOn(FacesController.class)
                                .modifyFaces(companyName, terminal, null))
                                .withRel(messages.getMessage("faces.get_update.message", null, locale)),
                        linkTo(methodOn(FacesController.class)
                                .deleteFaces(companyName, modelName, price, null))
                                .withRel(messages.getMessage("faces.get_delete.message", null, locale)));
                return ResponseEntity.ok(terminal);
    }

    @PostMapping
    public ResponseEntity<String> createFaces(
            @PathVariable("companyName") String companyName,
            @RequestBody Faces request,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale) {
                return ResponseEntity.ok(facesService.createFaces(request, companyName, locale));
    }

    @PutMapping
    public ResponseEntity<String> modifyFaces(
            @PathVariable("companyName") String companyName,
            @RequestBody Faces request,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale) {
                return ResponseEntity.ok(facesService.modifyFaces(request, companyName, locale));
    }

    @DeleteMapping(value="/{modelName}/{price}")
    public ResponseEntity<String> deleteFaces(
            @PathVariable("companyName") String companyName,
            @PathVariable("modelName") String modelName,
            @PathVariable("price") double price,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale) {
                return ResponseEntity.ok(facesService.deleteFaces(companyName, modelName, price, locale));
    }
}
