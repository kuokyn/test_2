package com.kuokyn.yandex_task_2.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.kuokyn.yandex_task_2.config.View;
import com.kuokyn.yandex_task_2.entity.Group;
import com.kuokyn.yandex_task_2.entity.Member;
import com.kuokyn.yandex_task_2.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private final GroupRepository groupRepository;

    @JsonView(View.Default.class)
    @GetMapping("/")
    public ResponseEntity<List<Group>> getGroups() {
        List<Group> groups = groupRepository.findAll();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @JsonView(View.Groups.class)
    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroup(@PathVariable("id") Long id) {
        Optional<Group> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent()) {
            Group group = groupOptional.get();
            return new ResponseEntity<>(group, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @JsonView(View.Default.class)
    @PostMapping("/")
    public ResponseEntity<Long> createGroup(@RequestBody Group group) {
        try {
            Group newGroup = groupRepository.save(group);
            return new ResponseEntity<>(newGroup.getGroupId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

//    @JsonView(View.Default.class)
//    @PostMapping("/group/{id}/toss")
//    public ResponseEntity<Group> createToss(@PathVariable("id") Long id) {
//        try {
//            Group group = groupRepository.findById(id).get();
//            List<Member> members = group.getParticipants();
//            if (members.size() >= 3) {
//                List<Long> ids = new ArrayList<>();
//                for (Member member : members) {
//                    ids.add(member.getMemberId());
//                }
//                Collections.shuffle(ids);
//                for (Member member : members) {
//                    member.setRecipient();
//                }
//
//            } else {
//                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
//            }
////            return new ResponseEntity<>(newGroup.getGroupId(), HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
//        }
//    }

    @JsonView(View.Default.class)
    @PutMapping("/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable("id") Long id, @RequestBody Group group) {
        Optional<Group> groupData = groupRepository.findById(id);
        if (groupData.isPresent()) {
            Group _group = groupData.get();
            _group.setDescription(group.getDescription());
            _group.setName(group.getName());
            return new ResponseEntity<>(groupRepository.save(_group), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGroup(@PathVariable("id") Long id) {
        try {
            groupRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
