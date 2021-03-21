package kr.ac.yeonsung.demo.repository;

import kr.ac.yeonsung.demo.domain.club.Club;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClubRepository {

    private final EntityManager em;

    public void save(Club club){
        if (club.getId() ==null){
            em.persist(club);
        }else {
            em.merge(club);
        }
    }

    public Club findOne(Long id){
        return em.find(Club.class,id);
    }

    public List<Club> findAll(){
        return em.createQuery("select c from Club c",Club.class).getResultList();
    }


    public void deleteOne(Club club){
        em.remove(club);
    }


}