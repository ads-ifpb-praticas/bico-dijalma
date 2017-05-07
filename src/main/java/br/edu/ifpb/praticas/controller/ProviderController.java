package br.edu.ifpb.praticas.controller;

import br.edu.ifpb.praticas.Exception.ClientException;
import br.edu.ifpb.praticas.Exception.ProviderException;
import br.edu.ifpb.praticas.model.Client;
import br.edu.ifpb.praticas.model.Provider;
import br.edu.ifpb.praticas.service.ClientService;
import br.edu.ifpb.praticas.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 03/05/17.
 */
@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderService service;

    @GetMapping
    public List<Provider> findAll() {
        try {
            return service.findAll();
        } catch (ProviderException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {

        try {
            return new ResponseEntity(service.findById(id), HttpStatus.OK);
        } catch (ProviderException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody Provider provider) {

        try {
            return new ResponseEntity(service.edit(id, provider), HttpStatus.OK);
        } catch (ProviderException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Provider provider) {
        try {
            return new ResponseEntity(service.save(provider), HttpStatus.OK);
        } catch (ProviderException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity(id, HttpStatus.OK);
        } catch (ProviderException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/inactive")
    public List<Provider> getInactivates() {
        return service.getInactivates();
    }
}
