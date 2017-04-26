package br.edu.ifpb.praticas.repository;

import br.edu.ifpb.praticas.model.Client;
import br.edu.ifpb.praticas.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 17/02/17 - 10:51
 */
@Repository
public interface ClienteRepository extends CrudRepository<Client, Long>{

    Client findByUser(User user);
}
