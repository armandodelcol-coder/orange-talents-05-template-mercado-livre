package br.com.zupacademy.armando.mercadolivre.core.imageuploaders;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class FakerImagesUploader implements ImageUploader {

    public Set<String> send(List<MultipartFile> images) {
        return images.stream()
                .map(multipartFile -> "https://bucket.io/" + multipartFile.getOriginalFilename())
                .collect(Collectors.toSet());
    }

}
