package br.edu.ifpb.praticas.service;

import br.edu.ifpb.praticas.Exception.ClientException;
import br.edu.ifpb.praticas.Exception.ClientException;
import br.edu.ifpb.praticas.model.Client;
import br.edu.ifpb.praticas.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 25/04/17.
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client) throws ClientException {
        try {
            return clientRepository.save(client);
        } catch (Exception ex) {
            throw new ClientException(ex.getMessage());
        }
    }

    public Client edit(Long id, Client client) throws ClientException {
        try {
            Client one = clientRepository.findOne(id);
            client.setId(one.getId());
            one = client;
            return clientRepository.save(one);
        } catch (Exception ex) {
            throw new ClientException(ex.getMessage());
        }
    }

    public Client findById(Long id) throws ClientException {
        try {
            return clientRepository.findOne(id);
        } catch (Exception ex) {
            throw new ClientException(ex.getMessage());
        }
    }

    public List<Client> findAll() throws ClientException {
        try {
            return (List<Client>) clientRepository.findAll();
        } catch (Exception ex) {
            throw new ClientException(ex.getMessage());
        }
    }

    public void delete(Long id) throws ClientException {
        try {
            clientRepository.delete(id);
        } catch (Exception ex) {
            throw new ClientException(ex.getMessage());
        }
    }
}
