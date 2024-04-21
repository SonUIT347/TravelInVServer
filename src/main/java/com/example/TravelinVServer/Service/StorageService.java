/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Service;

import com.example.TravelinVServer.Repository.StorageRepository;
import com.example.TravelinVServer.Until.StorageProperties;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public class StorageService implements StorageRepository {

    private final Path rootLocation;

    @Autowired
    public StorageService(StorageProperties properties) {

        if (properties.getLocation().trim().length() == 0) {
//            throw new StorageException("File upload location can not be Empty.");
            System.out.println("File upload location can not be Empty");
        }

        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
//            throw new StorageException("Could not initialize storage", e);
            System.out.println("Could not initialize storage" + e);
        }
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
//                throw new StorageException("Failed to store empty file.");
                System.out.println("File not found");
            }
            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                System.out.println("Cannot store file outside current directory.");
//                throw new StorageException(
//                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
//            throw new StorageException("Failed to store file.", e);
            System.out.println("Failed to store file." + e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
//            throw new StorageException("Failed to read stored files", e);
            System.out.println("Failed to read stored files" + e);
        }
        return null;
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
//                throw new StorageFileNotFoundException(
//                        "Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
//            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
