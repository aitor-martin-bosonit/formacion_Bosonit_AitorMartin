package com.formacion.Bloque17SpringBatch.Job;

import com.formacion.Bloque17SpringBatch.Domain.Tiempo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TiempoItemProcessor {

    private static final Logger log = LoggerFactory.getLogger(TiempoItemProcessor.class);

    @Override
    public Tiempo process(Tiempo tiempo) {
        log.info("--> Procesando registro de tiempo: " + tiempo);
        if (tiempo.getTemperatura() < 50 && tiempo.getTemperatura() > -20) {
            return tiempo;
        } else {
            return null;
        }
    }

}
