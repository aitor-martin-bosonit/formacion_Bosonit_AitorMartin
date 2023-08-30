package com.formacion.Bloque17SpringBatch.Mappers;

import com.formacion.Bloque17SpringBatch.Domain.Resultado;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultadoRowMapper implements RowMapper<Resultado> {
    @Override
    public Resultado mapRow(ResultSet rs, int rowNum) throws SQLException {
        Resultado resultado = new Resultado();
        resultado.setAnio(rs.getInt("anio"));
        resultado.setLocalidad(rs.getString("localidad"));
        resultado.setMes(rs.getInt("mes"));
        resultado.setNumeroMediciones(rs.getInt("numero_mediciones"));
        resultado.setTemperaturaMedia(rs.getInt("temperatura_media"));
        return resultado;
    }
}
