package br.edu.ifpb.praticas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 02/03/17 - 18:20
 */
@Controller
public class MainController {

    @RequestMapping(value = {"/", "/index"})
    private String index() {
        return "index";
    }

    @RequestMapping(value = "/client/index")
    private String indexClient() {
        return "client/index";
    }

    @RequestMapping(value = "/provider/index")
    private String indexProvider() {
        return "provider/index";
    }
}
