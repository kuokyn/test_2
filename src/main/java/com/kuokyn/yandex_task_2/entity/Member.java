package com.kuokyn.yandex_task_2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.kuokyn.yandex_task_2.config.View;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    @JsonView(View.Default.class)
    private Long memberId;

    @Column(name = "name")
    @JsonView(View.Default.class)
    private String name;

    @Column(name = "wish")
    @JsonView(View.Default.class)
    private String wish;

//    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient", referencedColumnName = "member_id")
    @JsonIgnoreProperties(value="recipient")
    @JsonView(View.Default.class)
    private Member recipient;

    @ManyToOne
    @JoinColumn(name="group_id", nullable=false)
    @JsonView(View.Ignore.class)
    private Group groupId;
}
