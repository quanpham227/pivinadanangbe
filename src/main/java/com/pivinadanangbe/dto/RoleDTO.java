package com.pivinadanangbe.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pivinadanangbe.entity.UserEntity;
import jakarta.persistence.*;
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
public class RoleDTO extends AbstractDTO<RoleDTO> implements Serializable {

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedDate;

    private List<UserDTO> users = new ArrayList<>();
}
