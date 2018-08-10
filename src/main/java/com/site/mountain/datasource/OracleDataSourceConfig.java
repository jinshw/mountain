package com.site.mountain.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = OracleDataSourceConfig.PACKAGE, sqlSessionTemplateRef = "oracleSqlSessionTemplate")
public class OracleDataSourceConfig {

    // 精确到 oracle 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.site.mountain.dao.oracle";
    static final String MAPPER_LOCATION = "classpath:mybatis/mapper/oracle/*.xml";

    @Value("${oracle.datasource.url}")
    private String url;

    @Value("${oracle.datasource.username}")
    private String user;

    @Value("${oracle.datasource.password}")
    private String password;

    @Value("${oracle.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "oracleDataSource")
    public DataSource oracleDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "oracleTransactionManager")
    public DataSourceTransactionManager oracleTransactionManager() {
        return new DataSourceTransactionManager(oracleDataSource());
    }

    @Bean(name = "oracleSqlSessionFactory")
    public SqlSessionFactory oracleSqlSessionFactory(@Qualifier("oracleDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(OracleDataSourceConfig.MAPPER_LOCATION));
        return bean.getObject();
    }

    @Bean(name = "oracleSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("oracleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
