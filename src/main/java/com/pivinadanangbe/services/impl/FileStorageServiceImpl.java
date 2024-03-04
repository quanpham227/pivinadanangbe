package com.pivinadanangbe.services.impl;


import com.pivinadanangbe.config.FileStorageProperties;
import com.pivinadanangbe.dto.UploadedFileInfo;
import com.pivinadanangbe.exception.FileNotFoundExeption;
import com.pivinadanangbe.exception.FileStorageException;
import com.pivinadanangbe.services.IFileStogareService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements IFileStogareService {
    private final Path fileLogoStorageLocation;
    private final Path filePostImageStorageLocation;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileLogoStorageLocation = Paths.get(fileStorageProperties.getUploadLogoDir()).toAbsolutePath().normalize();
        this.filePostImageStorageLocation = Paths.get(fileStorageProperties.getUploadPostImageDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(fileLogoStorageLocation);
            Files.createDirectories(filePostImageStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory for storing files", ex);
        }
    }


    @Override
    public String storeLogoFile(MultipartFile file) {
        return storeFile(fileLogoStorageLocation, file);
    }

    @Override
    public String storePostImageFile(MultipartFile file) {
        return storeFile(filePostImageStorageLocation, file);
    }

    @Override
    public UploadedFileInfo storeUploadedPostImageFile(MultipartFile file) {
        return storeUploadedFile(filePostImageStorageLocation, file);
    }

    @Override
    public Resource loadLogoFileAsResource(String filename) {
        return loadFileAsResource(fileLogoStorageLocation, filename);
    }

    @Override
    public Resource loadProductImageFileAsResource(String filename) {
        return loadFileAsResource(filePostImageStorageLocation, filename);
    }

    @Override
    public void deleteLogoFile(String filename) {
        deleteFile(fileLogoStorageLocation, filename);
    }

    @Override
    public void deleteProductImageFile(String filename) {
        deleteFile(filePostImageStorageLocation, filename);
    }

    private String storeFile(Path location, MultipartFile file) {
        String uuid = UUID.randomUUID().toString();
        String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String filename = uuid + "." + fileExtension;
        try {
            if (filename.contains("..")) {
                throw new FileStorageException("Filename contains invalid path sequence: " + filename);
            }
            Path targetLocation = location.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (Exception ex) {
            throw new FileStorageException("Could not store file " + filename + ". Please try again!", ex);
        }
    }

    private UploadedFileInfo storeUploadedFile(Path location, MultipartFile file) {
        String uuid = UUID.randomUUID().toString();
        String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String filename = uuid + "." + fileExtension;
        try {
            if (filename.contains("..")) {
                throw new FileStorageException("Filename contains invalid path sequence: " + filename);
            }
            Path targetLocation = location.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            UploadedFileInfo fileInfo = new UploadedFileInfo();
            fileInfo.setFileName(filename);
            fileInfo.setUid(uuid);
            fileInfo.setName(StringUtils.getFilename(file.getOriginalFilename()));
            return fileInfo;
        } catch (Exception ex) {
            throw new FileStorageException("Could not store file " + filename + ". Please try again!", ex);
        }
    }

    private Resource loadFileAsResource(Path location, String filename) {
        try {
            Path filePath = location.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundExeption("File not found: " + filename);
            }
        } catch (Exception ex) {
            throw new FileNotFoundExeption("File not found: " + filename, ex);
        }
    }

    private void deleteFile(Path location, String filename) {
        try {
            Path filePath = location.resolve(filename).normalize();
            if (!Files.exists(filePath)) {
                throw new FileNotFoundExeption("File not found: " + filename);
            }
            Files.delete(filePath);
        } catch (Exception ex) {
            throw new FileNotFoundExeption("File not found: " + filename, ex);
        }
    }


}
