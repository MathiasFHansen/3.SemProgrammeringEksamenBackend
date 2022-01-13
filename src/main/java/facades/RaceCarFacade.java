package facades;

import dtos.CarDTO;
import dtos.CarsDTO;
import dtos.RaceDTO;
import entities.Car;
import entities.Race;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class RaceCarFacade {

    public static RaceCarFacade instance;
    private static EntityManagerFactory emf;

    public RaceCarFacade() {
    }

    public static RaceCarFacade getRaceCarFacade(EntityManagerFactory _emf) {
        if(instance == null) {
            emf = _emf;
            instance = new RaceCarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<RaceDTO> getAllRaces(){
        List<RaceDTO> raceList = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Race> query = em.createQuery("select r from Race r", entities.Race.class);
            List<Race> races = query.getResultList();

            for (Race r: races) {
                raceList.add(new RaceDTO(r));
            }
            return raceList;
        } finally {
            em.close();
        }
    }

    public RaceDTO createRace(RaceDTO raceDTO) {
        EntityManager em = emf.createEntityManager();
        Race race = new Race(raceDTO);

        try {
            em.getTransaction().begin();
            em.persist(race);
            em.getTransaction().commit();
            return new RaceDTO(race);
        } finally {
            em.close();
        }
    }

    public CarsDTO getCarFromRaceID(long raceID){
        EntityManager em = emf.createEntityManager();

        TypedQuery<Car> query = em.createQuery("SELECT c FROM Car c JOIN c.raceList s WHERE s.id = :raceID", Car.class);
        query.setParameter("raceID", raceID);
        List<Car> cars = query.getResultList();

        if(cars != null){
            CarsDTO carsDTO = new CarsDTO(cars);
            return carsDTO;
        } else {
            return new CarsDTO(cars);
        }

    }


}
