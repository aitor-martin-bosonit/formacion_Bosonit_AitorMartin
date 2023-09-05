package com.formacion.Bloque17SpringBatch;

import com.formacion.Bloque17SpringBatch.Domain.Resultado;
import com.formacion.Bloque17SpringBatch.Domain.Tiempo;
import com.formacion.Bloque17SpringBatch.Domain.TiempoRiesgo;
import com.formacion.Bloque17SpringBatch.Job.ComprobacionesItemProcessor;
import com.formacion.Bloque17SpringBatch.Job.ResultadoItemProcessor;
import com.formacion.Bloque17SpringBatch.Job.TiempoItemProcessor;
import com.formacion.Bloque17SpringBatch.Job.TiempoRiesgoItemProcessor;
import com.formacion.Bloque17SpringBatch.Listener.JobListener;
import com.formacion.Bloque17SpringBatch.Mappers.ResultadoRowMapper;
import com.formacion.Bloque17SpringBatch.Mappers.TiempoRowMapper;
import lombok.Data;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@EnableBatchProcessing
@Configuration
@Data
public class BatchConfiguration {



    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private int ultimaFila = 0;

    @Bean
    public FlatFileItemReader<Tiempo> tiempoReader() {
        FlatFileItemReader<Tiempo> reader = new FlatFileItemReader<>();
        reader.setName("tiempoReader");
        reader.setResource(new ClassPathResource("sample-data.csv"));
        reader.setLineMapper(new DefaultLineMapper<Tiempo>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"localidad", "fecha", "temperatura"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Tiempo>() {{
                setTargetType(Tiempo.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ComprobacionesItemProcessor comprobacionesItemProcessor() {
        return new ComprobacionesItemProcessor();
    }

    @Bean
    public TiempoItemProcessor tiempoProcessor() {
        return new TiempoItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Tiempo> tiempoWriter(DataSource dataSource) {
        JdbcBatchItemWriter<Tiempo> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO tiempo (fecha, localidad, temperatura) VALUES (:fecha, :localidad, :temperatura)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Step comprobacionesStep(JdbcBatchItemWriter<Tiempo> writer) {
        return stepBuilderFactory.get("comprobacionesStep")
                .<Tiempo, Tiempo> chunk(10)
                .reader(tiempoReader())
                .processor(comprobacionesItemProcessor())
                .writer(writer)
                .build();
    }

    @Bean
    public Step tiempoStep1(JdbcBatchItemWriter<Tiempo> writer) {
        return stepBuilderFactory.get("tiempoStep1")
                .<Tiempo, Tiempo> chunk(10)
                .reader(tiempoReader())
                .processor(tiempoProcessor())
                .writer(writer)
                .build();
    }

    @Bean
    public JdbcCursorItemReader<Tiempo> tiempoReaderDataBase(){
        JdbcCursorItemReader<Tiempo> reader = new JdbcCursorItemReader<>();
        reader.setSql("SELECT t.* FROM tiempo t WHERE t.id_tiempo > " + ultimaFila);
        reader.setDataSource(dataSource);
        reader.setFetchSize(100);
        reader.setRowMapper(new TiempoRowMapper());
        return reader;
    }

    @Bean
    public TiempoRiesgoItemProcessor tiempoRiesgoProcessor() {
        return new TiempoRiesgoItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<TiempoRiesgo> tiempoRiesgoWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<TiempoRiesgo>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO tiempo_riesgo (fecha_prediccion, riesgo, id_tiempo) VALUES (:fechaPrediccion, :riesgo, :tiempo.idTiempo)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step tiempoRiesgoStep1(JdbcBatchItemWriter<TiempoRiesgo> writer) {
        return stepBuilderFactory.get("tiempoRiesgoStep1")
                .<Tiempo, TiempoRiesgo> chunk(10)
                .reader(tiempoReaderDataBase())
                .processor(tiempoRiesgoProcessor())
                .writer(writer)
                .build();
    }

    @Bean
    public JdbcCursorItemReader<Resultado> agrupandoDatos(){
        jdbcTemplate.update("DELETE FROM Resultado");
        jdbcTemplate.update("ALTER TABLE Resultado ALTER COLUMN id_resultado RESTART WITH 1");
        JdbcCursorItemReader<Resultado> reader = new JdbcCursorItemReader<>();
        String sql = "SELECT YEAR(t.fecha) as anio,localidad, MONTH(t.fecha) as mes,count(*) as numero_mediciones,riesgo,AVG(t.temperatura) as temperatura_media FROM tiempo t " +
                "JOIN tiempo_riesgo tr ON t.id_tiempo = tr.id_tiempo " +
                "GROUP BY t.localidad, MONTH(t.fecha)";
        reader.setSql(sql);
        reader.setDataSource(dataSource);
        reader.setFetchSize(100);
        reader.setRowMapper(new ResultadoRowMapper());
        return reader;
    }

    @Bean
    public ResultadoItemProcessor resultadoProcessor() {
        return new ResultadoItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Resultado> resultadoWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Resultado>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO resultado (anio,localidad,mes,numero_mediciones,riesgo,temperatura_media) VALUES (:anio, :localidad, :mes, :numeroMediciones, :riesgo, :temperaturaMedia)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step resultadoStep1(JdbcBatchItemWriter<Resultado> writer) {
        return stepBuilderFactory.get("resultadoStep1")
                .<Resultado, Resultado> chunk(10)
                .reader(agrupandoDatos())
                .processor(resultadoProcessor())
                .writer(writer)
                .build();
    }

    @Bean
    public Job importTiempoJob(JobListener listener, Step tiempoStep1, Step comprobacionesStep) {
        return jobBuilderFactory.get("importTiempoJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(comprobacionesStep)
                .next(tiempoStep1)
                .end()
                .build();
    }

    @Bean
    public Job importTiempoRiesgoJob(JobListener listener, Step tiempoRiesgoStep1) {
        return jobBuilderFactory.get("importTiempoRiesgoJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(tiempoRiesgoStep1)
                .end()
                .build();
    }

    @Bean
    public Job importResultadoJob(JobListener listener, Step resultadoStep1) {
        return jobBuilderFactory.get("importResultadoJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(resultadoStep1)
                .end()
                .build();
    }
}
