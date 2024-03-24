package com.kuokyn.yandex_task_2.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kuokyn.yandex_task_2.config.View;
import com.kuokyn.yandex_task_2.entity.Group;
import com.kuokyn.yandex_task_2.entity.Member;
import com.kuokyn.yandex_task_2.repository.GroupRepository;
import com.kuokyn.yandex_task_2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;

    @JsonView(View.Default.class)
    @GetMapping("/participants/{id}")
    public ResponseEntity<List<Member>> getMembersByGroup(@PathVariable("id") Long id) {
        List<Member> members = memberRepository.findAllByGroupId(id);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }
//
//    @GetMapping("/groups/{id}")
//    public ResponseEntity<Group> getGroup(@PathVariable("id") Long id) {
//        Optional<Group> groupOptional = groupRepository.findById(id);
//        if (groupOptional.isPresent()) {
//            Group group = groupOptional.get();
//            return new ResponseEntity<>(group, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @PostMapping("/group/{id}/participant")
    public ResponseEntity<Long> createMember(@PathVariable("id") Long id, @RequestBody Member member) {
        member.setGroupId(groupRepository.findById(id).get());
            Member newMember = memberRepository.save(member);
            return new ResponseEntity<>(newMember.getMemberId(), HttpStatus.CREATED);

    }

//    @PutMapping("/group/{id}")
//    public ResponseEntity<Group> updateGroup(@PathVariable("id") Long id, @RequestBody Group group) {
//        Optional<Group> groupData = groupRepository.findById(id);
//        if (groupData.isPresent()) {
//            Group _group = groupData.get();
//            _group.setDescription(group.getDescription());
//            _group.setName(group.getName());
//            return new ResponseEntity<>(groupRepository.save(_group), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    @DeleteMapping("/group/{groupId}/participant/{participantId}")
//    public ResponseEntity<HttpStatus> deleteParticipant(@PathVariable("groupId") Long groupId,
//                                                        @PathVariable("participantId") Long participantId) {
//        try {
//            memberRepository.deleteById(participantId);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
