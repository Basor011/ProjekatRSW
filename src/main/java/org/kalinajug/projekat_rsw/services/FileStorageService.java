package org.kalinajug.projekat_rsw.services;

import lombok.RequiredArgsConstructor;


import org.kalinajug.projekat_rsw.config.StorageProperties;
import org.kalinajug.projekat_rsw.exceptions.BadRequestException;
import org.kalinajug.projekat_rsw.exceptions.ResourceNotFoundException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;


@Service
public class FileStorageService {
    private final Path root;

    public FileStorageService(StorageProperties props) throws IOException {
        this.root = Path.of(props.root()).toAbsolutePath().normalize();
        Files.createDirectories(root);
    }

    public String save(MultipartFile file, String podfolder) throws IOException {
        if(file == null || file.isEmpty()) throw new BadRequestException("Fajl je prazan.");

        String og = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename() ));
        if(!og.toLowerCase().endsWith(".zip")) throw new BadRequestException("Dozvoljeni su samo zipovani folderi.");

        Path dir = root.resolve(podfolder).normalize();
        if(!dir.startsWith(root)) throw new BadRequestException("Neispravna putanja.");

        try{
            Files.createDirectories(dir);
            Path target = dir.resolve(UUID.randomUUID() + ".zip");
            try(InputStream in = file.getInputStream()){
                Files.copy(in, target);
            }
            return root.relativize(target).toString();
        }catch(IOException e){
            throw new UncheckedIOException("Greska pri cuvanju fajla.", e);
        }
    }
    public Resource load(String relativnaPutanja){
        Path p = root.resolve(relativnaPutanja).normalize();
        if(!p.startsWith(root) || !Files.exists(p)) throw new ResourceNotFoundException("Fajl ne postoji.");
        return new FileSystemResource(p.toFile());
    }
    public void delete(String relativnaPutanja){
        try{
            Files.deleteIfExists(root.resolve(relativnaPutanja).normalize());
        }catch(IOException e){}
    }
}
