package br.com.zupacademy.armando.mercadolivre.products.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Primary
public class FakerImagesUploader implements ImageUploader {

    public Set<String> send(List<MultipartFile> images) {
        return images.stream()
                .map(multipartFile -> "https://bucket.io/" + multipartFile.getOriginalFilename())
                .collect(Collectors.toSet());
    }

}
