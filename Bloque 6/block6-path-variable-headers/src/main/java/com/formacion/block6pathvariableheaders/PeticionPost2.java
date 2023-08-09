package com.formacion.block6pathvariableheaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/peticionpost2")
public class PeticionPost2 {

    @PostMapping("/all")

    public DataResponse all(@RequestBody(required = false) String body,
                       @RequestHeader Map<String, String> headers,
                       @RequestParam Map<String, String> requestParams) {
        DataResponse allData = new DataResponse();
        allData.setBody(body);
        allData.setHeaders(new ArrayList<>(headers.values()));
        allData.setRequestParams(new ArrayList<>(requestParams.values()));
        return allData;
    }

}
