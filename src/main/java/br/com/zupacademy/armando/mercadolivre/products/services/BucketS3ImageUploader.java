package br.com.zupacademy.armando.mercadolivre.products.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BucketS3ImageUploader implements ImageUploader{

    @Override
    public Set<String> send(List<MultipartFile> images) {
        return images.stream()
                .map(multipartFile -> "https://amazon.s3/" + multipartFile.getOriginalFilename())
                .collect(Collectors.toSet());
    }

}
