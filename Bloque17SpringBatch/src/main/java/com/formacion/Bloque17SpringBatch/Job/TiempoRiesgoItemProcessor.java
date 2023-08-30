package com.formacion.Bloque17SpringBatch.Job;

import com.formacion.Bloque17SpringBatch.Domain.Tiempo;
import com.formacion.Bloque17SpringBatch.Domain.TiempoRiesgo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class TiempoRiesgoItemProcessor implements ItemProcessor<Tiempo, TiempoRiesgo> {
    private static final Logger log = LoggerFactory.getLogger(TiempoRiesgoItemProcessor.class);

    @Override
    public TiempoRiesgo process(Tiempo tiempo) throws Exception {
        log.info("--> Calculando el riesgo de cada registro");
        TiempoRiesgo tiempoRiesgo = new TiempoRiesgo();
        tiempoRiesgo.setIdTiempoRiesgo(tiempo.getIdTiempo());
        tiempoRiesgo.setTiempo(tiempo);
        tiempoRiesgo.setFechaPrediccion(tiempo.getFecha());

        int temperatura = tiempo.getTemperatura();
        if (temperatura > 36) {
            tiempoRiesgo.setRiesgo("Alto");
        } else if (temperatura >= 32) {
            tiempoRiesgo.setRiesgo("Medio");
        } else {
            tiempoRiesgo.setRiesgo("Bajo");
        }

        return tiempoRiesgo;
    }
}
