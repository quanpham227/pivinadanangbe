package com.pivinadanangbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturerDTO extends AbstractDTO<ManufacturerDTO> implements Serializable {
    @NotEmpty
    private String name;
    private String logo;

    @JsonIgnore
    private MultipartFile logoFile;
}
