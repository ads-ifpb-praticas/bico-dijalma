package br.edu.ifpb.praticas.controller;

import br.edu.ifpb.praticas.Exception.ClientException;
import br.edu.ifpb.praticas.model.Client;
import br.edu.ifpb.praticas.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 03/05/17.
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public List<Client> findAll() {
        try {
            return service.findAll();
        } catch (ClientException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {

        try {
            return new ResponseEntity(service.findById(id), HttpStatus.OK);
        } catch (ClientException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, Client client) {

        try {
            return new ResponseEntity(service.edit(id, client), HttpStatus.OK);
        } catch (ClientException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity save(Client client) {
        try {
            return new ResponseEntity(service.save(client), HttpStatus.OK);
        } catch (ClientException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity(id, HttpStatus.OK);
        } catch (ClientException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
