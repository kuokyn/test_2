package com.kuokyn.yandex_task_2.repository;

import com.kuokyn.yandex_task_2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value="SELECT * FROM member\n" +
            "where group_id=:id",
            nativeQuery = true)
    List<Member> findAllByGroupId(@Param("id") Long id);
}
