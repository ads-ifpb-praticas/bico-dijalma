package br.edu.ifpb.praticas.controller;

import br.edu.ifpb.praticas.Exception.UserException;
import br.edu.ifpb.praticas.form.UserVO;
import br.edu.ifpb.praticas.model.User;
import br.edu.ifpb.praticas.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 04/05/17.
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        try {
            UserVO userVO = service.login(user);
            return new ResponseEntity(userVO, HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
