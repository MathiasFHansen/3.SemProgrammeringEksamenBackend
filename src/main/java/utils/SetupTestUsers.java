package utils;


import entities.Car;
import entities.Race;
import entities.Role;
import entities.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("user", "testHest");
    User admin = new User("admin", "testHest");
    User both = new User("user_admin", "testHest");
    Race race1 = new Race("Tokyo International", "13-01-2022", "11:00", "Tokyo");
    Race race2 = new Race("Berlin International", "13-02-2022", "12:00", "Berlin");
    Car car = new Car("Celica","Toyota","Sport",1970);
    Car car2 = new Car("Supra","Toyota","Sport",1978);
    Car car3 = new Car("Skyline GT-R", "Nissan", "Sport", 1969);
    Car car4 = new Car("Lancer Evolution", "Mitsubishi", "Sport", 1992);


    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);

    race1.addCar(car);
    race1.addCar(car2);
    race2.addCar(car3);
    race2.addCar(car4);

    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(race1);
    em.persist(race2);
    em.persist(both);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("testHest"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   
  }

}
