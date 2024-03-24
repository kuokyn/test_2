package com.kuokyn.yandex_task_2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.kuokyn.yandex_task_2.config.View;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "group_id")
    @JsonView(View.Default.class)
    private Long groupId;

    @Column(name = "name")
    @JsonView(View.Default.class)
    private String name;

    @Column(name = "description")
    @JsonView(View.Default.class)
    private String description;

    @JsonView(View.Groups.class)
    @OneToMany(mappedBy="groupId")
    private List<Member> participants;
}