package br.edu.ifpb.praticas.service;

import br.edu.ifpb.praticas.Exception.UserException;
import br.edu.ifpb.praticas.enums.TypePeople;
import br.edu.ifpb.praticas.form.UserVO;
import br.edu.ifpb.praticas.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 25/04/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class LoginServiceTest {

    private User user;
    @Autowired
    private LoginService service;

    @Before
    public void setUp() {
        user = new User();
        user.setUsername("Colleen");
        user.setPassword("N31sM6E");
    }

    @Test
    public void loginCorrect() throws UserException {
        UserVO login = service.login(user);
        assertEquals(user.getUsername(), login.getUser().getUsername());
    }

    @Test
    public void loginProvider() throws UserException {
        user.setUsername("Reuben");
        user.setPassword("d11dd3R");
        UserVO login = service.login(user);
        assertEquals(TypePeople.PROVIDER.getName(), login.getType().getName());
    }

    @Test
    public void loginClient() throws UserException {
        user.setUsername("Caesar");
        user.setPassword("w54Rq1N");
        UserVO login = service.login(user);
        assertEquals(TypePeople.CLIENT.getName(), login.getType().getName());
    }

    @Test(expected = UserException.class)
    public void loginFailed() throws UserException {
        user.setUsername("dijalma");
        UserVO login = service.login(user);
    }
}