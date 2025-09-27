package com.CRUD.IHernandezRepositoryPrueba.Configuration;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig
{
    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        
        driver.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
        driver.setUsername("IhernandezRepository");
        driver.setPassword("password1");
        
        return driver;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }
}
