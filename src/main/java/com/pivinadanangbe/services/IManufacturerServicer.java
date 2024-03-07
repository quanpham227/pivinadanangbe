package com.pivinadanangbe.services;


import com.pivinadanangbe.dto.ManufacturerDTO;
import com.pivinadanangbe.entity.ManufacturerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IManufacturerServicer {


    ManufacturerDTO insertManufacturer(ManufacturerDTO manufacturerDTO);
    ManufacturerDTO updateManufacturer(Long id ,ManufacturerDTO manufacturerDTO);

    Page<ManufacturerEntity> getAllManufacturers (Pageable pageable);
    Page<ManufacturerDTO> getAllManufacturerPaginged (Pageable pageable);
    Page<ManufacturerDTO> getAllManufacturerPaginged (String keyword, Pageable pageable);

    ManufacturerDTO findById (Long id);

    void deleteById(Long id);
}