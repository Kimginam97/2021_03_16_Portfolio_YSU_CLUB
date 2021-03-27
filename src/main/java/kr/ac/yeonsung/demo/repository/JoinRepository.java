package kr.ac.yeonsung.demo.repository;

import kr.ac.yeonsung.demo.domain.Join;
import kr.ac.yeonsung.demo.domain.JoinClub;
import kr.ac.yeonsung.demo.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.awt.*;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class JoinRepository {

    private final EntityManager em;

    //동아리 신청
    public void save(Join join){

        em.persist(join);
    }

    //동아리 조회
    public Join findOne(Long id){
        return em.find(Join.class,id);
    }


}
