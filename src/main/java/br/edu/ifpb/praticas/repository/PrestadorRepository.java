package br.edu.ifpb.praticas.repository;

import br.edu.ifpb.praticas.model.Provider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 17/02/17 - 10:51
 */
@Repository
public interface PrestadorRepository extends CrudRepository<Provider, Long>{
}
