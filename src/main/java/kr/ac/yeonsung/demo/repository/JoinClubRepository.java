package kr.ac.yeonsung.demo.repository;

import kr.ac.yeonsung.demo.domain.Join;
import kr.ac.yeonsung.demo.domain.JoinClub;
import kr.ac.yeonsung.demo.domain.JoinclubMapping;
import kr.ac.yeonsung.demo.domain.club.Club;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface JoinClubRepository extends JpaRepository<JoinClub,Long> {
    List<JoinclubMapping> findByClub(Club club);
    //클럽삭제할떄 클럽 id 가져와서 디비 쿼리들가는게 id 하나만찾게
    // 해당되는 클럽 id 가지고있는 신청현황 id만 list로 가져옴
    // 가져온거 반복문으로 돌려삭제
    // 아니다 돌랴야게따 while롣 돌리샘

}

