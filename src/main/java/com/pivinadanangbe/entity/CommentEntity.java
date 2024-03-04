package com.pivinadanangbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "comments")
public class CommentEntity extends AbstractEntity{
    @Column(name = "content")
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Timestamp createdDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "modified_date")
    private Timestamp modifiedDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


    @PreUpdate
    public void preUpdate() {
        this.modifiedDate = new Timestamp(System.currentTimeMillis());
    }

    @PrePersist
    public void prePersist() {
        createdDate = new Timestamp(System.currentTimeMillis());
    }

}
