package com.aston.landmarks.integration;

import jakarta.persistence.Table;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;

public class DbHelper {
    public static <T> T get(Class<T> entity, DataSource dataSource) {
        NamedParameterJdbcTemplate namedJdbc = new NamedParameterJdbcTemplate(dataSource);
        final String tableName = entity.getAnnotation(Table.class).name();
        final String query = String.format("select * from %s order by id desc limit 1", tableName);
        return namedJdbc.queryForObject(query, new HashMap<>(),
                new BeanPropertyRowMapper<>(entity, false));
    }
}
