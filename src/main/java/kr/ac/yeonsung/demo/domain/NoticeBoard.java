package kr.ac.yeonsung.demo.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class NoticeBoard {

    @Id @GeneratedValue
    @Column(name = "boardNotice_id")
    private Long id;//게시글번호

    @Column(columnDefinition = "varchar(100) default '관리자'")
    private String name;//게시자

    private String title;//제목

    private String content;//게시글 내용

    @ManyToOne(fetch = LAZY)//n:1
    @JoinColumn(name = "member_id")
    private Member boardMember;

    public void setMember(Member member){
        this.boardMember = member;
        boardMember.getNoticeBoardList().add(this);
    }
}
