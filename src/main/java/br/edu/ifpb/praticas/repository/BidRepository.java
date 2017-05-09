package br.edu.ifpb.praticas.repository;

import br.edu.ifpb.praticas.model.Bid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 09/05/17.
 */
@Repository
public interface BidRepository extends CrudRepository<Bid, Long> {


}
