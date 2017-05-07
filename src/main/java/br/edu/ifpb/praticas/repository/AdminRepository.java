package br.edu.ifpb.praticas.repository;

import br.edu.ifpb.praticas.model.Admin;
import br.edu.ifpb.praticas.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 06/05/17.
 */
@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {

    Admin findByUser(User user);
}
