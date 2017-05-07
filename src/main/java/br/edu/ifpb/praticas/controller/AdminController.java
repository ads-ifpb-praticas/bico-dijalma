package br.edu.ifpb.praticas.controller;

import br.edu.ifpb.praticas.model.Admin;
import br.edu.ifpb.praticas.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 06/05/17.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable Long id) {

        Admin one = service.findOne(id);

        if (one != null) {
            return new ResponseEntity(one, HttpStatus.OK);
        } else {
            return new ResponseEntity("Usuário não existe", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/accept/{id}")
    public ResponseEntity approveProvider(@PathVariable Long id) {

        boolean b = service.approveProvider(id);
        if (b) {
            return new ResponseEntity(id, HttpStatus.OK);
        } else {
            return new ResponseEntity("Erro ao ativar prestador!", HttpStatus.NOT_FOUND);
        }

    }

}
