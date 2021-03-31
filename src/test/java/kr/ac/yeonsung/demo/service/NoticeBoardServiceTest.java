package kr.ac.yeonsung.demo.service;

import kr.ac.yeonsung.demo.domain.NoticeBoard;
import kr.ac.yeonsung.demo.repository.NoticeBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;


@SpringBootTest
@Transactional
public class NoticeBoardServiceTest {

    @Autowired
    private NoticeBoardRepository noticeBoardRepository;
    @Autowired
    private NoticeBoardService noticeBoardService;
    @Autowired
    private EntityManager em;

    @Test
    public void 게시글작성() throws Exception{
        NoticeBoard noticeBoard = new NoticeBoard();
        noticeBoard.setTitle("제목");
        noticeBoard.setContent("내용");

        noticeBoardService.write(noticeBoard);
        em.flush();

        assertEquals(noticeBoard,noticeBoardRepository.findOne(noticeBoard.getId()));
    }
}