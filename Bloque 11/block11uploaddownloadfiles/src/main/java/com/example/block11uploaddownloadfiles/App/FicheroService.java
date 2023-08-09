package com.example.block11uploaddownloadfiles.App;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FicheroService {

    ResponseEntity<?> uploadFichero(String tipe, MultipartFile file) throws IOException;
    ResponseEntity<String> modifyPath(String path);
    ResponseEntity<?> downloadFicheroId(int id);
    ResponseEntity<?> downloadFicheroNombre(String nombre);
}
