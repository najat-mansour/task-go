package com.taskgo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.taskgo.constants.Priority;
import com.taskgo.constants.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "sub_tasks")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private Priority priority;

    @Column(name = "starting_timestamp", nullable = false)
    private LocalDateTime startingTimestamp;

    @Column(name = "ending_timestamp", nullable = false)
    private LocalDateTime endingTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}