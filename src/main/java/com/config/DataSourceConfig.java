package com.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan("com.dao")
public class DataSourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    private JdbcConfig jdbcConfig;

    @Bean
    @Primary //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(jdbcConfig.getUrl());
        druidDataSource.setUsername(jdbcConfig.getUserName());
        druidDataSource.setPassword(jdbcConfig.getPassword());
        druidDataSource.setInitialSize(jdbcConfig.getInitialSize());
        druidDataSource.setMinIdle(jdbcConfig.getMinIdle());
        druidDataSource.setMaxActive(jdbcConfig.getMaxActive());
        druidDataSource.setTimeBetweenEvictionRunsMillis(jdbcConfig.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(jdbcConfig.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(jdbcConfig.getValidationQuery());
        druidDataSource.setTestWhileIdle(jdbcConfig.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(jdbcConfig.isTestOnBorrow());
        druidDataSource.setTestOnReturn(jdbcConfig.isTestOnReturn());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(jdbcConfig.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            druidDataSource.setFilters(jdbcConfig.getFilters());
        } catch (SQLException e) {
            if (logger.isInfoEnabled()) {
                logger.info(e.getMessage(), e);
            }
        }
        return druidDataSource;
    }


    /**
     * Jdbc配置类
     */
    @PropertySource(value = "classpath:jdbc.properties")
    @Component
    @Data
    public static class JdbcConfig {
        /**
         * 数据库用户名
         */
        @Value("${spring.datasource.username}")
        private String userName;
        /**
         * 驱动名称
         */
        @Value("${spring.datasource.driver-class-name}")
        private String driverClass;
        /**
         * 数据库连接url
         */
        @Value("${spring.datasource.url}")
        private String url;
        /**
         * 数据库密码
         */
        @Value("${spring.datasource.password}")
        private String password;

        /**
         * 数据库连接池初始化大小
         */
        @Value("${spring.datasource.initialSize}")
        private int initialSize;

        /**
         * 数据库连接池最小最小连接数
         */
        @Value("${spring.datasource.minIdle}")
        private int minIdle;

        /**
         * 数据库连接池最大连接数
         */
        @Value("${spring.datasource.maxActive}")
        private int maxActive;

        /**
         * 获取连接等待超时的时间
         */
        @Value("${spring.datasource.maxWait}")
        private long maxWait;

        /**
         * 多久检测一次
         */
        @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
        private long timeBetweenEvictionRunsMillis;

        /**
         * 连接在池中最小生存的时间
         */
        @Value("${spring.datasource.minEvictableIdleTimeMillis}")
        private long minEvictableIdleTimeMillis;

        /**
         * 测试连接是否有效的sql
         */
        @Value("${spring.datasource.validationQuery}")
        private String validationQuery;

        /**
         * 申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，检测连接是否有效
         */
        @Value("${spring.datasource.testWhileIdle}")
        private boolean testWhileIdle;

        /**
         * 申请连接时,检测连接是否有效
         */
        @Value("${spring.datasource.testOnBorrow}")
        private boolean testOnBorrow;

        /**
         * 归还连接时,检测连接是否有效
         */
        @Value("${spring.datasource.testOnReturn}")
        private boolean testOnReturn;

        /**
         * PSCache大小
         */
        @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
        private int maxPoolPreparedStatementPerConnectionSize;

        /**
         * 通过别名的方式配置扩展插件
         */
        @Value("${spring.datasource.filters}")
        private String filters;
    }
}
