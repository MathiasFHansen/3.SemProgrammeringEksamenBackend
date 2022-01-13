package facades;

import dtos.RaceDTO;
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

    public List<Race> getAllRaces(){
        List<Race> raceList = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Race> query = em.createQuery("select r from Race r", entities.Race.class);
            List<Race> races = query.getResultList();

            for (Race r: races) {
                raceList.add(r);
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
}
