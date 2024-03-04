package com.pivinadanangbe.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDTO extends AbstractDTO<CommentDTO> implements Serializable {

    private String content;

    private Timestamp createdDate;

    private Timestamp modifiedDate;

    private PostDTO post;

    private UserDTO user;

}
