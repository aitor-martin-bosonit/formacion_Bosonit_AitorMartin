package com.formacion.block6pathvariableheaders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse {

    private String body;
    private List<String> headers;
    private List<String> requestParams;

}
