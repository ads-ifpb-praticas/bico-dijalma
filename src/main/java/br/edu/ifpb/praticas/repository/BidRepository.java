package br.edu.ifpb.praticas.repository;

import br.edu.ifpb.praticas.model.Bid;
import br.edu.ifpb.praticas.model.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 09/05/17.
 */
@Repository
public interface BidRepository extends CrudRepository<Bid, Long> {

    @Query(value = "SELECT * FROM bid b, job j WHERE j.client_id = ?1 AND j.id = b.job_id", nativeQuery = true)
    List<Bid> findBidsByIdClient(Long idClient);

    List<Bid> findBidsByJob(Job job);

    @Query(value = "SELECT b FROM Bid b, Job j WHERE j.status = 'ABERTO' AND j.id = b.job.id AND b.provider.id = :idProvider")
    List<Bid> findByProviderAndJobOpen(@Param("idProvider") Long idProvider);
}
