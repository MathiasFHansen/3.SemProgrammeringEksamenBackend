package facades;

import dtos.RaceDTO;
import entities.Race;
import entities.Role;
import entities.User;
import utils.EMF_Creator;
import entities.RenameMe;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeExampleTest {

    private static EntityManagerFactory emf;
    private static UserFacade facade;
    private static RaceCarFacade raceCarFacade;

    public FacadeExampleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = UserFacade.getUserFacade(emf);
       raceCarFacade = RaceCarFacade.getRaceCarFacade(emf);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("delete from User").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

//    @AfterAll
//    public static void tearDownClass() {
////        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.createQuery("delete from User").executeUpdate();
//            em.createQuery("delete from Role").executeUpdate();
//        } finally {
//            em.close();
//        }
//    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testCreateUser() {
        facade = UserFacade.getUserFacade(emf);
        User actual = facade.createUser("thias", "thias123");
        User expected = new User("thias", "thias123");
        Role userRole = new Role("user");
        expected.addRole(userRole);
        assertEquals(expected.getUserName(), actual.getUserName());
    }

    @Test
    public void createRaceTest(){
        raceCarFacade = RaceCarFacade.getRaceCarFacade(emf);
        Race raceTest = new Race("Los Angeles International","13-01-2022","22:01","Los Angeles");
        RaceDTO actual = raceCarFacade.createRace(new RaceDTO(raceTest));
        RaceDTO expected = new RaceDTO(raceTest);

        assertEquals(expected.getName(),actual.getName());
    }

}
