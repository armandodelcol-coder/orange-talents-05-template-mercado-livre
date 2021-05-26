package br.com.zupacademy.armando.mercadolivre.products.imageuploaders;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BucketS3ImageUploader implements ImageUploader{

    @Override
    public Set<String> send(List<MultipartFile> images) {
        return images.stream()
                .map(multipartFile -> "https://amazon.s3/" + multipartFile.getOriginalFilename())
                .collect(Collectors.toSet());
    }

}
