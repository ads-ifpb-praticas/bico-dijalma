package br.edu.ifpb.praticas.controller;

import br.edu.ifpb.praticas.Exception.ClientException;
import br.edu.ifpb.praticas.enums.TypeService;
import br.edu.ifpb.praticas.model.Job;
import br.edu.ifpb.praticas.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 07/05/17.
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;


    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Job finded = jobService.findOne(id);
        if (finded != null) {
            return new ResponseEntity(finded, HttpStatus.OK);
        } else {
            return new ResponseEntity(id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Job job) {
        Job save = jobService.save(job);
        if (save != null) {
            return new ResponseEntity(save, HttpStatus.OK);
        } else {
            return new ResponseEntity("Não foi possível cadastrar serviço!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody Job job) {
        Job edit = jobService.edit(id, job);
        if (edit != null) {
            return new ResponseEntity(edit, HttpStatus.OK);
        } else {
            return new ResponseEntity(id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/open/client/{idClient}")
    public List<Job> jobsClientOpen(@PathVariable Long idClient) {
        try {
            return jobService.jobsClientOpen(idClient);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @GetMapping("/close/client/{idClient}")
    public List<Job> jobsClientClose(@PathVariable Long idClient) {
        try {
            return jobService.jobsClientClose(idClient);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @GetMapping("/finish/client/{idClient}")
    public List<Job> jobsClientFinish(@PathVariable Long idClient) {
        try {
            return jobService.jobsClientFinish(idClient);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @GetMapping("/type/{typeService}")
    public List<Job> jobsTypeService(@PathVariable TypeService typeService) {
        return jobService.jobsTypeServiceOpen(typeService);
    }

    @GetMapping("/close/date/{dealDate}")
    public List<Job> jobsCloseDateDeal(@PathVariable LocalDate dealDate) {
        return jobService.jobsCloseDateDeal(dealDate);
    }

    @GetMapping("/finish/date/{dealDate}")
    public List<Job> jobsFinishDateDeal(@PathVariable LocalDate dealDate) {
        return jobService.jobsFinishDateDeal(dealDate);
    }
}
