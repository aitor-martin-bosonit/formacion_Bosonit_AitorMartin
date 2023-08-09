package com.formacion.block6pathvariableheaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/peticionput1")
public class PeticionPut1 {
    @PutMapping("/put")
    public List<Map<String, Object>> post(@RequestParam("var1") Integer var1, @RequestParam("var2") Integer var2) {
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("var1", var1);
        data.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("var2", var2);
        data.add(map2);
        return data;
    }

}
