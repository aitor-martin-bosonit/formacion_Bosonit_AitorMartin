package com.formacion.Bloque17SpringBatch.Mappers;

import com.formacion.Bloque17SpringBatch.Domain.Tiempo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TiempoRowMapper implements RowMapper<Tiempo> {
    @Override
    public Tiempo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tiempo tiempo = new Tiempo();
        tiempo.setIdTiempo(rs.getInt("id_Tiempo"));
        tiempo.setLocalidad(rs.getString("localidad"));
        tiempo.setTemperatura(rs.getInt("temperatura"));
        tiempo.setFecha(rs.getDate("fecha"));

        return tiempo;
    }
}
