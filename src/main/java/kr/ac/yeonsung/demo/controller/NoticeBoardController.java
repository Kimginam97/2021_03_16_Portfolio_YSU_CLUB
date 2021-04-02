package kr.ac.yeonsung.demo.controller;

import kr.ac.yeonsung.demo.domain.NoticeBoard;
import kr.ac.yeonsung.demo.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class NoticeBoardController {

    private final NoticeBoardService noticeBoardService;

    @GetMapping("/noticeBoard/write")
    public String writeForm(Model model){
        model.addAttribute("noticeBoardForm",new NoticeBoardForm());
        return "noticeBoard/noticeWrite";
    }
    //게시글 작성
    @PostMapping("/noticeBoard/write")
    public String write(@Valid NoticeBoardForm form, BindingResult result) {//BindingResult form에서 오류가 있을시 오류가 result에 담김
        // result에 에러가 있으면 noticeBoard/noticeWrite로 다시 반환
        if (result.hasErrors()) {
            return "noticeBoard/noticeWrite";
        }
        NoticeBoard noticeBoard = new NoticeBoard();
        noticeBoard.setTitle(form.getTitle());
        noticeBoard.setContent(form.getContent());
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        noticeBoard.setWriteDate(dateTime);

        noticeBoardService.write(noticeBoard);
        return "redirect:/"; // 첫 번째 페이지로 넘어가게함
    }

    //전체 게시글 가지고오기
    @GetMapping("/noticeBoard/list")
    public String getNoticeBoardList(Model model){
        List<NoticeBoard> noticeBoardList = noticeBoardService.findAll();
        model.addAttribute("noticeList",noticeBoardList);
        return "noticeBoard/noticeList";
    }

    //게시글 보기
    @GetMapping("/noticeBoard/view/{noticeId}")
    public String noticeView(@PathVariable("noticeId") Long noticeId,Model model){
        NoticeBoard noticeBoard = noticeBoardService.findOne(noticeId);
        model.addAttribute("noticeId",noticeBoard);
        return "noticeBoard/noticeView";
    }

    //게시글 삭제
    @GetMapping("/noticeBoard/cancel/{noticeId}")//게시글 번호를 가지고옴
    public String cancel(@PathVariable("noticeId") Long noticeId){
        noticeBoardService.delete(noticeId);
        return "redirect:/noticeBoard/list";
    }

    //게시글 수정
    @GetMapping("/noticeBoard/update/{noticeId}")
    public String update(@PathVariable("noticeId") Long noticeId, Model model){
        NoticeBoard findNotice = noticeBoardService.findOne(noticeId);
        model.addAttribute("noticeBoardForm",findNotice);
        return "noticeBoard/noticeUpdate";
    }
    @PostMapping("/noticeBoard/update/{noticeId}")
    public String update(@Valid NoticeBoardForm form, BindingResult result,@PathVariable("noticeId") Long id) {//BindingResult form에서 오류가 있을시 오류가 result에 담김
        // result에 에러가 있으면 noticeBoard/noticeWrite로 다시 반환
        if (result.hasErrors()) {
            System.out.println(id);
            return "noticeBoard/noticeUpdate";
        }

        NoticeBoard updateNotice = noticeBoardService.findOne(id);//id로 이전 작성한 게시물을 가져옴
        updateNotice.setTitle(form.getTitle());//값을 update
        updateNotice.setContent(form.getContent());
        //수정한 날짜를 저장
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        updateNotice.setUpdateDate(dateTime);
        noticeBoardService.update(updateNotice);//새로 저장한값을 저장시킴
        return "redirect:/noticeBoard/list"; // 게시글 목록으로 이동
    }
}
