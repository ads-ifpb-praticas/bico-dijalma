package br.edu.ifpb.praticas.service;

import br.edu.ifpb.praticas.Exception.UserException;
import br.edu.ifpb.praticas.enums.StatusEnum;
import br.edu.ifpb.praticas.enums.TypePeople;
import br.edu.ifpb.praticas.form.UserVO;
import br.edu.ifpb.praticas.model.Admin;
import br.edu.ifpb.praticas.model.Client;
import br.edu.ifpb.praticas.model.Provider;
import br.edu.ifpb.praticas.model.User;
import br.edu.ifpb.praticas.repository.AdminRepository;
import br.edu.ifpb.praticas.repository.ClientRepository;
import br.edu.ifpb.praticas.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 25/04/17.
 */
@Service
public class LoginService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private AdminRepository adminRepository;

    public UserVO login(User user) throws UserException {
        Client clientByUser = clientRepository.findByUserAndStatusEquals(user, StatusEnum.ATIVO);

        if (clientByUser == null) {
            Provider providerByUser = providerRepository.findByUserAndStatusEquals(user, StatusEnum.ATIVO);

            if (providerByUser == null) {

                Admin admin = adminRepository.findByUser(user);

                if (admin == null) {
                    throw new UserException("Usuário ou senha inválidos!");
                }

                return constructUserVO(admin.getId(), admin.getUser(), TypePeople.ADMIN);
            }

            return constructUserVO(providerByUser.getId(), providerByUser.getUser(), TypePeople.PROVIDER);
        }

        return constructUserVO(clientByUser.getId(), clientByUser.getUser(), TypePeople.CLIENT);
    }

    private UserVO constructUserVO(Long id, User user, TypePeople typePeople) {
        UserVO userVO = new UserVO();
        userVO.setUser(user);
        userVO.setType(typePeople);
        userVO.setId(id);
        return userVO;
    }
}
