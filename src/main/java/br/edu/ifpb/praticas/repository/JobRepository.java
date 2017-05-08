package br.edu.ifpb.praticas.repository;

import br.edu.ifpb.praticas.enums.StatusJob;
import br.edu.ifpb.praticas.enums.TypeService;
import br.edu.ifpb.praticas.model.Client;
import br.edu.ifpb.praticas.model.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 07/05/17.
 */
@Repository
public interface JobRepository extends CrudRepository<Job, Long> {


    List<Job> findByClientAndStatus(Client client, StatusJob status);

    List<Job> findByStatusAndTypeOfService(StatusJob status, TypeService typeOfService);

    List<Job> findByStatusAndDealDate(StatusJob status, LocalDate dealDate);
}
