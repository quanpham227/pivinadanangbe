package com.pivinadanangbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractDTO<T>{
    private Long id;

    private String createdBy;

    private String modifiedBy;
}
