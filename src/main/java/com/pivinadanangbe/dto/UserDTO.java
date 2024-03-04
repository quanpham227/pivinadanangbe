package com.pivinadanangbe.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends AbstractDTO<UserDTO> implements Serializable {


    private String email;

    private String password;

    private String fullName;

    private String provider;

    private String providerId;

    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedDate;

    private List<CommentDTO> comments = new ArrayList<>();

    private List<RoleDTO> roles = new ArrayList<>();
}
