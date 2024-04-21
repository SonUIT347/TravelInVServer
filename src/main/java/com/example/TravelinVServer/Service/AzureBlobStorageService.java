/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Service
public class AzureBlobStorageService {

    private static final String storageConnectionString = "DefaultEndpointsProtocol=https;AccountName=travelinvserver;AccountKey=/5e85MPlxMwrES9cH7cY7pqJUCOm+0sz6M8ifjOzSlkhXzHajRFXFKNMBcywVj5g6jFP213fQLVX+AStehBtaA==;EndpointSuffix=core.windows.net";
// e.g., "DefaultEndpointsProtocol=https;AccountName=myaccount;AccountKey=mykey;EndpointSuffix=core.windows.net"
    private static final String containerName = "travelinvimage"; // Name of the container to store blobs

    public String uploadImage(MultipartFile file) throws Exception {
        try {
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
            CloudBlobContainer container = blobClient.getContainerReference(containerName);

            container.createIfNotExists();

            String fileName = file.getOriginalFilename();
            CloudBlockBlob blob = container.getBlockBlobReference(fileName);

            try (InputStream inputStream = file.getInputStream()) {
                blob.upload(inputStream, file.getSize());
            }
            blob.getProperties().setContentType("image/jpeg");
            blob.uploadProperties();
            return blob.getUri().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
