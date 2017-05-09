package br.edu.ifpb.praticas.controller;

import br.edu.ifpb.praticas.form.BidVO;
import br.edu.ifpb.praticas.model.Bid;
import br.edu.ifpb.praticas.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 09/05/17.
 */
@RestController
@RequestMapping("/bid")
public class BidController {

    @Autowired
    private BidService service;

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable Long id) {
        Bid one = service.findOne(id);
        if (one != null) {
            return new ResponseEntity(one, HttpStatus.OK);
        } else {
            return new ResponseEntity(id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody BidVO bidVO) {
        Bid save = service.save(bidVO.toBid());
        if (save != null) {
            return new ResponseEntity(save, HttpStatus.OK);
        } else {
            return new ResponseEntity("Não foi possível fazer a proposta!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity save(@PathVariable Long id, @RequestBody BidVO bidVO) {
        Bid save = service.edit(id, bidVO.toBid());
        if (save != null) {
            return new ResponseEntity(save, HttpStatus.OK);
        } else {
            return new ResponseEntity("Não foi possível atualizar a proposta!", HttpStatus.NOT_FOUND);
        }
    }
}
