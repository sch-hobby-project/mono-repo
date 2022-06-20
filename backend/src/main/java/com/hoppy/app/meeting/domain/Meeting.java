package com.hoppy.app.meeting.domain;

import com.hoppy.app.meeting.common.Category;
import com.hoppy.app.member.domain.MemberMeeting;
import com.hoppy.app.member.domain.MemberMeetingLike;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long owner;

    private String url;

    @Column(nullable = false) // length 옵션 추가 논의 필요
    private String title;

    @Column(nullable = false) // length 옵션 추가 논의 필요
    private String content;

    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private int limit;

    @OneToMany(mappedBy = "meeting")
    @Builder.Default
    private Set<MemberMeeting> participants = new HashSet<>();

    @OneToMany(mappedBy = "meeting")
    @Builder.Default
    private Set<MemberMeetingLike> myMeetingLikes = new HashSet<>();
}