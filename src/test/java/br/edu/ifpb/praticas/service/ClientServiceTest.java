package br.edu.ifpb.praticas.service;

import br.edu.ifpb.praticas.Exception.ClientException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 25/04/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClientServiceTest {

    @Before
    public void setUp() {
    }

    @Test
    public void save() throws ClientException {
    }

    @Test
    public void edit() throws ClientException {
    }

    @Test
    public void findById() throws ClientException {
    }

    @Test
    public void findAll() throws ClientException {
    }

    @Test
    public void delete() throws ClientException {
    }

}