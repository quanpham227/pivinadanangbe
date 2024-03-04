package com.pivinadanangbe.services;

import com.pivinadanangbe.dto.UploadedFileInfo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileStogareService {

    String storeLogoFile(MultipartFile file);

    String storePostImageFile(MultipartFile file);

    UploadedFileInfo storeUploadedPostImageFile(MultipartFile file);

    Resource loadLogoFileAsResource(String filename);

    Resource loadProductImageFileAsResource(String filename);

    void deleteLogoFile(String filename);

    void deleteProductImageFile(String filename);
}
