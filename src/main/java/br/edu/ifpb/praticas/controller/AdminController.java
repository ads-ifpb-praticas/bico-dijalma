package br.edu.ifpb.praticas.controller;

import br.edu.ifpb.praticas.Exception.ProviderException;
import br.edu.ifpb.praticas.model.Admin;
import br.edu.ifpb.praticas.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/accept/{id}")
    public ResponseEntity approveProvider(@PathVariable Long id) {

        try {
            boolean b = service.approveProvider(id);
            if (b) {
                return new ResponseEntity("Prestador ativado com sucesso", HttpStatus.OK);
            } else {
                return new ResponseEntity("Erro ao ativar prestador!", HttpStatus.NOT_FOUND);
            }
        } catch (ProviderException e) {
            return new ResponseEntity("Operação inválida!", HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
