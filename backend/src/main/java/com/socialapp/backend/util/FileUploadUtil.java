package com.socialapp.backend.util;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
@Log4j2
public class FileUploadUtil {
    private Path originalUploadPath = Paths.get("./src/main/resources/static/images/");

    public String uploadFile(MultipartFile multipartFile,
                             String apiPath,
                             String directoryPath) throws IOException {
        log.info("Inside uploadFile of FileUploadUtil {}", multipartFile);
        Path uploadPath = originalUploadPath.resolve(directoryPath);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            String filename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();

            Path filePath = uploadPath.resolve(filename);

            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            // the response will be the download URL of the image
            // return the download image url as a response entity
            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(apiPath)
                    .path(filename)
                    .toUriString();

        } catch (IOException e) {
            throw new IOException("Could not save image file: " + multipartFile.getOriginalFilename(), e);
        }
    }

    public byte[] load(String directoryPath, String filename) {
        try {
            Path uploadPath = originalUploadPath.resolve(directoryPath);
            Path destination = uploadPath.resolve(filename);
            return IOUtils.toByteArray(destination.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Can not load " + filename);
        }
    }

}
