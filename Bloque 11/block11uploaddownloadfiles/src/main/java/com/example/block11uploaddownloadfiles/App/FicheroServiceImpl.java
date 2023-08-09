package com.example.block11uploaddownloadfiles.App;

import com.example.block11uploaddownloadfiles.Entity.Fichero;
import com.example.block11uploaddownloadfiles.Repository.FicheroRepository;
import jakarta.annotation.PostConstruct;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

@Service
public class FicheroServiceImpl implements FicheroService {

    @Autowired
    private FicheroRepository ficheroRepository;

    @Autowired
    private ApplicationArguments args;

    private Path basePath;

    @PostConstruct
    public void configurePath() {
        String[] argumentos = args.getSourceArgs();
        basePath = argumentos.length > 0 ? Paths.get(argumentos[0]) : Paths.get(System.getProperty("user.dir"));
    }

    @Override
    public ResponseEntity<?> uploadFichero(String tipe, MultipartFile file) throws IOException {
        String nombreFichero = file.getOriginalFilename();

        if (!nombreFichero.endsWith(tipe)) {
            return ResponseEntity.badRequest().body("El fichero debe tener extensión " + tipe);
        }

        if (!Files.isDirectory(basePath)) {
            return ResponseEntity.badRequest().body("El path: " + basePath + " no es un directorio");
        }

        Path filePath = basePath.resolve(nombreFichero);
        Files.write(filePath, file.getBytes());

        Fichero fichero = new Fichero();
        fichero.setNombre(nombreFichero);
        fichero.setFechaSubida(new Date());
        fichero.setCategoria(tipe);
        fichero = ficheroRepository.save(fichero);

        return ResponseEntity.ok(fichero);
    }

    @Override
    public ResponseEntity<String> modifyPath(String ruta) {
        Path newPath = Paths.get(ruta);
        if (!Files.exists(newPath)) {
            return ResponseEntity.badRequest().body("El path: " + newPath + " no existe");
        }
        basePath = newPath;
        return ResponseEntity.ok("El path ha cambiado a ser: " + basePath);
    }

    public ResponseEntity<?> downloadFicheroId(int id) {
        Optional<Fichero> posiblefichero = ficheroRepository.findById(id);
        Fichero fichero = posiblefichero.orElseThrow(() -> new IllegalArgumentException("No se ha encontrado el fichero con ID: " + id));
        return downloadFichero(fichero);
    }

    @Override
    public ResponseEntity<?> downloadFicheroNombre(String nombre) {
        Optional<Fichero> posiblefichero = ficheroRepository.findByNombre(nombre);
        Fichero fichero = posiblefichero.orElseThrow(() -> new IllegalArgumentException("No se ha encontrado el fichero con nombre: " + nombre));
        return downloadFichero(fichero);
    }

    private ResponseEntity<?> downloadFichero(Fichero fichero) {
        String rutaFichero = basePath.resolve(fichero.getNombre()).toString();
        File sourceFile = new File(rutaFichero);

        if (!sourceFile.exists() || sourceFile.isDirectory()) {
            return ResponseEntity.badRequest().body("El fichero no se encuentra disponible en la ruta especificada.");
        }

        try {
            String nombreFichero = "Copia-" + fichero.getNombre();
            File targetFile = basePath.resolve(nombreFichero).toFile();
            FileUtils.copyFile(sourceFile, targetFile);

            Fichero ficheroCopia = new Fichero();
            ficheroCopia.setNombre(nombreFichero);
            ficheroCopia.setFechaSubida(new Date());
            ficheroCopia.setCategoria(fichero.getCategoria());
            ficheroCopia = ficheroRepository.save(ficheroCopia);

            return ResponseEntity.ok(ficheroCopia);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al leer el contenido del fichero.");
        }
    }
}
