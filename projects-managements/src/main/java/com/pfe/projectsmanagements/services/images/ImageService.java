package com.pfe.projectsmanagements.services.images;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface ImageService {
    public void init();
    public void save(MultipartFile file);
    public Resource load(String filename);
    public void deleteAll();
    public Stream<Path> loadAll();
    public void  uploadMyPicture(MultipartFile file);
    public byte[] getImage( String name) throws IOException;


}
