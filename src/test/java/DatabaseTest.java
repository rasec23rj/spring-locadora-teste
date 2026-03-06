import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseTest {
    static Connection connection;

    @BeforeAll
    static void setUpDataBase() throws Exception{
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "SA", "");
        connection.createStatement().execute("CREATE TABLE users (id INT, name VARCHAR)");
    }


    @BeforeEach
    void insertUserTest () throws Exception{
        connection.createStatement().execute("insert into users (id, name) values (1, 'Jose')");    }


    @Test
    void testeUserExists() throws  Exception{
        var result =   connection.createStatement().executeQuery("select * from users where id = 1 ");
        Assertions.assertTrue(result.next());
    }


    @AfterAll
    static void closedDataBase() throws Exception{
        connection.close();
    }







}
