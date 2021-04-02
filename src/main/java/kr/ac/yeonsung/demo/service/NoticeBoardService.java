package kr.ac.yeonsung.demo.service;

import java.awt.print.Pageable;
import java.util.List;
import kr.ac.yeonsung.demo.domain.NoticeBoard;
import kr.ac.yeonsung.demo.repository.NoticeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeBoardService {
    private final NoticeBoardRepository noticeBoardRepository;

    //게시글작성
    @Transactional
    public Long write(NoticeBoard noticeBoard){
        noticeBoardRepository.save(noticeBoard);
        return noticeBoard.getId();
    }

    //게시글 전체 조회
    public List<NoticeBoard> findAll(){ return noticeBoardRepository.findAll();}
    //게시글 단건 조회
    public NoticeBoard findOne(Long boardId){
        return noticeBoardRepository.findOne(boardId);
    }
   
    //게시글 삭제
    @Transactional
    public void delete(Long noticeId){
        NoticeBoard notice = noticeBoardRepository.findOne(noticeId);//게시글의 id값으로 해당 게시물을 가지고옴
        noticeBoardRepository.delete(notice);
    }

    //게시글 수정
    @Transactional
    public void update(NoticeBoard noticeBoard){
        noticeBoardRepository.updateNotice(noticeBoard);
    }
}
