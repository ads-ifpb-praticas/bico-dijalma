package br.edu.ifpb.praticas.service;

import br.edu.ifpb.praticas.Exception.ClientException;
import br.edu.ifpb.praticas.enums.StatusEnum;
import br.edu.ifpb.praticas.model.Client;
import br.edu.ifpb.praticas.model.PlaceOfCare;
import br.edu.ifpb.praticas.model.User;
import org.junit.Assert;
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
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;
    private Client client;

    @Before
    public void setUp() {
        client = new Client();
        PlaceOfCare place = new PlaceOfCare();
        place.setCity("Cajazeiras");
        place.setDistrict("Casas Populares");
        client.setPlaceOfCare(place);
        client.setStatus(StatusEnum.ATIVO);
        User user = new User();
        user.setUsername("dijalmaf");
        user.setPassword("1233ddd");
        client.setUser(user);
        client.setCpf("136.828.384-57");
        client.setName("Dijalma");
        client.setLastName("Silva");
        client.setEmail("dijalmacz@gmail.com");
        client.setTelephone("(83) 99900-5077");
    }

    @Test
    public void save() throws ClientException {
        Client save = clientService.save(client);
        assertNotNull(save);
    }

    @Test
    public void edit() throws ClientException {
        Client finded = clientService.findById(40L);
        finded.setName("Chanaína");
        finded.setCpf("926.729.717-14");
        Client edit = clientService.edit(40L, finded);
        assertEquals("Chanaína", edit.getName());
    }

    @Test
    public void findById() throws ClientException {
        Client finded = clientService.findById(20L);
        assertNotNull(finded.getId());
    }

    @Test
    public void findAll() throws ClientException {
        assertTrue(clientService.findAll().size() >= 9);
    }

    @Test
    public void delete() throws ClientException {
        client.setName("Joinhaa");
        client.getUser().setUsername("joinhaa");
        client.setCpf("221.585.606-81");
        Client save = clientService.save(client);
        Long id = save.getId();
        clientService.delete(id);
        Client finded = clientService.findById(id);
        assertNull(finded);
    }

    @Test(expected = ClientException.class)
    public void saveError() throws ClientException {
        client.setCpf("399.291.123-12");
        Client save = clientService.save(client);
    }

    @Test(expected = ClientException.class)
    public void editError() throws ClientException {
        Client finded = clientService.findById(40L);
        finded.setId(30L);
        Client edit = clientService.edit(40L, finded);
    }

    @Test(expected = ClientException.class)
    public void deleteError() throws ClientException {
        clientService.delete(1000L);
    }
}