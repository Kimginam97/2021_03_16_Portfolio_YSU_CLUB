package kr.ac.yeonsung.demo.service;

import kr.ac.yeonsung.demo.domain.Join;
import kr.ac.yeonsung.demo.domain.JoinClub;
import kr.ac.yeonsung.demo.domain.JoinclubMapping;
import kr.ac.yeonsung.demo.domain.club.Club;
import kr.ac.yeonsung.demo.repository.ClubRepository;
import kr.ac.yeonsung.demo.repository.JoinClubRepository;
import kr.ac.yeonsung.demo.repository.JoinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class JoinClubService {

    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private JoinRepository joinRepository;
    @Autowired
    private JoinClubRepository joinClubRepository;

    @Transactional
    public Optional<JoinClub> findOne(Long clubId){  return joinClubRepository.findById(clubId);}

    @Transactional
    public void chageStatus(Club club){
        Long clubId = club.getId();
        log.info("갑이야갑ㄱ박박바갑갑가박ㅂ가"+clubId);
        List<JoinclubMapping> byClub = joinClubRepository.findByClub(club);
        int size = byClub.size();
        for(int i=0; i<=byClub.size();i++) {
            log.info("나와라요~~~" + byClub.get(i).getJoin_Id());
            Join join = joinRepository.findOne(byClub.get(i).getJoin_Id());
                join.cancel();
        }
//        log.info("나와라아아아아아아아 조인아이디 !!! "+id);
//        Join join = joinRepository.findOne(id);
//        join.cancel();
    }
}
