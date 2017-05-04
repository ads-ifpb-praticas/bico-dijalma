package br.edu.ifpb.praticas.service;

import br.edu.ifpb.praticas.Exception.ProviderException;
import br.edu.ifpb.praticas.enums.StatusEnum;
import br.edu.ifpb.praticas.model.Provider;
import br.edu.ifpb.praticas.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 25/04/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProviderServiceTest {

    @Autowired
    private ProviderService service;
    private Provider provider;

    @Before
    public void setUp() throws Exception {
        provider = new Provider();
        provider.setCpf("685.347.391-45");
        provider.setStatus(StatusEnum.ATIVO);
        provider.setEmail("teste@gmail.com");
        provider.setName("Teste");
        provider.setTelephone("(83) 99999-9999");
        provider.setLastName("SobreTeste");
        provider.setId(4012L);
        User user = new User();
        user.setUsername("teste");
        user.setPassword("teste123");
        provider.setUser(user);
    }

    @Test
    public void save() throws ProviderException {
        
    }

    @Test
    public void edit() throws ProviderException {
    }

    @Test
    public void findById() throws ProviderException {
    }

    @Test
    public void findAll() throws ProviderException {
    }

    @Test
    public void delete() throws ProviderException {
    }

}