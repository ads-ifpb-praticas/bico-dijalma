package br.edu.ifpb.praticas.service;

import br.edu.ifpb.praticas.Exception.ProviderException;
import br.edu.ifpb.praticas.model.Client;
import br.edu.ifpb.praticas.model.Provider;
import br.edu.ifpb.praticas.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 25/04/17.
 */
@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public Provider save(Provider provider) throws ProviderException {
        try {
            return providerRepository.save(provider);
        } catch (Exception ex) {
            throw new ProviderException(ex.getMessage());
        }
    }

    public Provider edit(Long id, Provider provider) throws ProviderException {
        try {
            Provider one = providerRepository.findOne(id);
            provider.setId(one.getId());
            one = provider;
            return providerRepository.save(one);
        } catch (Exception ex) {
            throw new ProviderException(ex.getMessage());
        }
    }

    public Provider findById(Long id) throws ProviderException {
        try {
            return providerRepository.findOne(id);
        } catch (Exception ex) {
            throw new ProviderException(ex.getMessage());
        }
    }

    public List<Provider> findAll() throws ProviderException {
        try {
            return (List<Provider>) providerRepository.findAll();
        } catch (Exception ex) {
            throw new ProviderException(ex.getMessage());
        }
    }

    public void delete(Long id) throws ProviderException {
        try {
            providerRepository.delete(id);
        } catch (Exception ex) {
            throw new ProviderException(ex.getMessage());
        }
    }
}
