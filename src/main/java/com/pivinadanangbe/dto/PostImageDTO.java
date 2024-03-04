package com.pivinadanangbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostImageDTO extends AbstractDTO<PostImageDTO> implements Serializable {


    private String name;

    private String publicId;

    private String fileName;

    private String url;

    private String status;

    private String response = "{\"status\": \"success\"}";
}
