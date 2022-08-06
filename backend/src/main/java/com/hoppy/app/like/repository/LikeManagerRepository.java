package com.hoppy.app.like.repository;

import com.hoppy.app.community.domain.Post;
import com.hoppy.app.member.domain.Member;
import com.hoppy.app.like.domain.LikeManager;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 태경 2022-07-22
 */
@Repository
public interface LikeManagerRepository extends JpaRepository<LikeManager, Long> {

    @Query("select distinct m from LikeManager as m left join fetch m.meetingLikes where m.member = :member")
    Optional<LikeManager> findLikeManagerWithMeetingLikesByMember(Member member);

    @Query("select distinct m from LikeManager as m left join fetch m.postLikes where m.member = :member")
    Optional<LikeManager> findLikeManagerWithPostLikesByMember(@Param("member") Member member);
}
