package br.com.zupacademy.armando.mercadolivre.core.imageuploaders;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface ImageUploader {

    Set<String> send(List<MultipartFile> images);

}
