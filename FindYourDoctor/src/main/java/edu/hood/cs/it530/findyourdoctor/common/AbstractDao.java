package edu.hood.cs.it530.findyourdoctor.common;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class AbstractDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public AbstractDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        DataSource dataSource = jdbcTemplate.getDataSource();
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
