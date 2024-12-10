package com.biometric.faces.controller;

import com.biometric.faces.model.Faces;
import com.biometric.faces.model.FacesStringList;
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

    /*@GetMapping(value="/{price}")
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
    }*/
    @GetMapping(value="/{price}")
    public ResponseEntity<FacesStringList> getFaces(
            @PathVariable("companyName") String companyName,
            @PathVariable("price") double price,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale) {
        FacesStringList terminals = facesService.getFaces(companyName, price, locale);
        Faces terminal = terminals.getFacesList().get(0);
        terminals.add(linkTo(methodOn(FacesController.class)
                        .getFaces(companyName, price, locale))
                        .withSelfRel(),
                linkTo(methodOn(FacesController.class)
                        .createFaces(companyName, terminal))
                        .withRel(messages.getMessage("faces.get_create.message", null, locale)),
                linkTo(methodOn(FacesController.class)
                        .modifyFaces(companyName, terminal, null))
                        .withRel(messages.getMessage("faces.get_update.message", null, locale)),
                linkTo(methodOn(FacesController.class)
                        .deleteFaces(companyName, terminal.getModelName(), null))
                        .withRel(messages.getMessage("faces.get_delete.message", null, locale)));
        terminals.getFacesList().clear();
        return ResponseEntity.ok(terminals);
    }

    @PostMapping
    public ResponseEntity<Faces> createFaces(
            @PathVariable("companyName") String companyName,
            @RequestBody Faces request) {
                return ResponseEntity.ok(facesService.createFaces(request, companyName));
    }

    @PutMapping
    public ResponseEntity<String> modifyFaces(
            @PathVariable("companyName") String companyName,
            @RequestBody Faces request,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale) {
                return ResponseEntity.ok(facesService.modifyFaces(request, companyName, locale));
    }

    @DeleteMapping(value="/{modelName}")
    public ResponseEntity<String> deleteFaces(
            @PathVariable("companyName") String companyName,
            @PathVariable("modelName") String modelName,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale) {
                return ResponseEntity.ok(facesService.deleteFaces(companyName, modelName, locale));
    }
}
