package com.pivinadanangbe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity (name = "gallery")
public class GalleryEntity extends AbstractEntity{
    @Column (name = "name")
    private String name;

    @Column(name = "url")
    private String url;

}
