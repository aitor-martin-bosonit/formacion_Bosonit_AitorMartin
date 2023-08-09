package com.formacion.block6pathvariableheaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/peticionget2")
public class PeticionGet2 {

    @GetMapping("/header")
    public ResponseEntity<String> header(@RequestHeader(value = "h1", required = false) String h1, @RequestHeader(value = "h2", required = false) String h2) {
        StringBuilder response = new StringBuilder();
        if (h1 != null) {
            response.append("h1: ").append(h1).append("\n");
        }
        if (h2 != null) {
            response.append("h2: ").append(h2).append("\n");
        }
        return ResponseEntity.ok(response.toString());
    }

}
