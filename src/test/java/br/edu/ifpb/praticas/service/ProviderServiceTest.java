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
        User user = new User();
        user.setUsername("teste");
        user.setPassword("teste123");
        provider.setUser(user);
    }

    @Test
    public void save() throws ProviderException {
        Provider save = service.save(provider);
        assertNotNull(save);
    }

    @Test
    public void edit() throws ProviderException {
        Provider finded = service.findById(101L);
        finded.setCpf("403.181.861-01");
        finded.setName("Teste2");
        Provider edit = service.edit(101L, finded);
        assertEquals("Teste2", finded.getName());
    }

    @Test
    public void findById() throws ProviderException {
        Provider finded = service.findById(105L);
        assertNotNull(finded);
    }

    @Test
    public void findAll() throws ProviderException {
        assertTrue(service.findAll().size() >= 9);
    }

    @Test
    public void delete() throws ProviderException {
        provider.setName("TesteDel");
        provider.getUser().setUsername("testeDel");
        provider.setCpf("478.042.371-62");
        Provider save = service.save(provider);
        Long id = save.getId();
        service.delete(id);
        Provider byId = service.findById(id);
        assertNull(byId);
    }

    @Test(expected = ProviderException.class)
    public void saveError() throws ProviderException {
        provider.setCpf("399.291.123-12");
        Provider save = service.save(provider);
    }

    @Test(expected = ProviderException.class)
    public void editError() throws ProviderException {
        Provider finded = service.findById(108L);
        finded.setId(106L);
        Provider edit = service.edit(108L, finded);
    }

    @Test(expected = ProviderException.class)
    public void deleteError() throws ProviderException {
        service.delete(1000L);
    }

}