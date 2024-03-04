package com.pivinadanangbe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pivinadanangbe.entity.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO extends AbstractDTO<PostDTO> implements Serializable {


    private String title;


    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedDate;


    private PostStatus status;


    private String slug;


    private String excerpt;


    private PostImageDTO image;


    private CategoryDTO category;

    private List<CommentDTO> comments = new ArrayList<>();


    private Set<TagDTO> tags = new HashSet<>();
}
