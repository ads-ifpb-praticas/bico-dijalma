package br.edu.ifpb.praticas.service;

import br.edu.ifpb.praticas.Exception.ProviderException;
import br.edu.ifpb.praticas.enums.StatusEnum;
import br.edu.ifpb.praticas.model.Admin;
import br.edu.ifpb.praticas.model.Provider;
import br.edu.ifpb.praticas.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 06/05/17.
 */
@Service
public class AdminService {

    @Autowired
    private AdminRepository dao;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private EmailTask emailTask;

    public Admin findOne(Long id) {
        return dao.findOne(id);
    }

    public boolean approveProvider(Long idProvider) throws ProviderException {
        Provider provider = providerService.findById(idProvider);
        provider.setStatus(StatusEnum.ATIVO);
        providerService.edit(idProvider, provider);
        emailTask.sendEMailToUpdateStatusProvider(provider.getEmail());
        return true;
    }
}
