package com.example.block11uploaddownloadfiles.Controller;

import com.example.block11uploaddownloadfiles.App.FicheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FicheroController {

    @Autowired
    FicheroService ficheroService;

    @PostMapping("/upload/{tipo}")
    public ResponseEntity<?> uploadFichero(@PathVariable String tipe,
                                          @RequestParam("file") MultipartFile file) throws IOException {
        return ficheroService.uploadFichero(tipe,file);
    }

    @GetMapping("/setpath")
    public ResponseEntity<String> changePath(@RequestParam("path") String path){
        return ficheroService.modifyPath(path);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> returnFicheroId(@PathVariable int id){
        return ficheroService.downloadFicheroId(id);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> returnFicheroNombre(@PathVariable String nombre){
        return ficheroService.downloadFicheroNombre(nombre);
    }

}
