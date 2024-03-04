package com.pivinadanangbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RoleEntity extends AbstractEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "modified_date")
    private Date modifiedDate;



    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<UserEntity> users = new ArrayList<>();

    @PreUpdate
    public void preUpdate() {
        modifiedDate = new Date();
    }

    @PrePersist
    public void prePersist() {
        createdDate = new Date();
    }

    public void addUser(UserEntity user) {
        this.users.add(user);
        user.getRoles().add(this);
    }

    public void removeUser(UserEntity user) {
        this.users.remove(user);
        user.getRoles().remove(this);
    }

    public void removeUser() {
        for (UserEntity user : new ArrayList<>(users)) {
            removeUser(user);
        }
    }


}
