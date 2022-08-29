package com.hoppy.app.community.domain;

import com.hoppy.app.like.domain.MemberPostLike;
import com.hoppy.app.meeting.domain.Meeting;
import com.hoppy.app.member.domain.Member;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.annotations.BatchSize;

/**
 * @author 태경 2022-07-21
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @Exclude
    private Member author;

    @ManyToOne(fetch = FetchType.LAZY)
    @Exclude
    private Meeting meeting;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @BatchSize(size = 100)
    @Default
    @Exclude
    private Set<Reply> replies = new HashSet<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @BatchSize(size = 100)
    @Default
    @Exclude
    private Set<MemberPostLike> likes = new HashSet<>();
}
