package kr.ac.yeonsung.demo.repository;

import kr.ac.yeonsung.demo.domain.NoticeBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeBoardRepository{
    private final EntityManager em;

    //게시글저장
    public void save(NoticeBoard noticeBoard){
        em.persist(noticeBoard);
    }
    //게시글 전체 조회

    public List<NoticeBoard> findAll(){
        List<NoticeBoard> result = em.createQuery("select n from NoticeBoard n",NoticeBoard.class).getResultList();
        //setFirstResult(page).setMaxResults(1).getResultList();
        return result;
    }

    //게시글 정보 조회(단건)
    public NoticeBoard findOne(Long id) {return em.find(NoticeBoard.class, id);}
    //게시글 삭제
    public void delete(NoticeBoard notice){
        em.remove(notice);
    }//remove로 게시글 삭제
    //게시글 수정
    public void updateNotice(NoticeBoard noticeBoard){
        em.persist(noticeBoard);
    }
}
