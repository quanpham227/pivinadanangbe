package com.pivinadanangbe.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post_image")
public class PostImageEntity extends AbstractEntity{
    @Column(name = "public_id")
    private String publicId;

    @Column(name = "name")
    private String name;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "url")
    private String url;


}
