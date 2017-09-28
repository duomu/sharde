package com.yll.sharde.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.config.ShardingProperties;
import com.dangdang.ddframe.rdb.sharding.config.ShardingPropertiesConstant;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.yll.sharde.algorithm.DefaultDatabaseShardingAlgorithm;
import com.yll.sharde.algorithm.DynamicUserJobTableShardingAlgorithm;
import com.yll.sharde.algorithm.DynamicUserTableShardingAlgorithm;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.*;

/**
 * @author：linlin.yang
 * @date：2017/8/28 16:44
 */
@Configuration
@PropertySource(MybatisShardingConfig.DB_LOCATION)//声明属性源
@MapperScan(basePackages = MybatisShardingConfig.BASE_PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisShardingConfig {
    static final String BASE_PACKAGE = "com.yll.sharde.repository";
    static final String DB_LOCATION = "classpath:db.properties";
    static final String MAPPER_LOCATION = "classpath:mybatis/mapper/*.xml";
    static final String shardingColumn = "user_id";
    static final String userGenerateKeyColumn = "user_id";
    static final String userJobGenerateKeyColumn = "id";
    static final String userlogicTable = "user_0";
    static final String userJoblogicTable = "user_job_0";
    static List<String> userActualTables = new LinkedList<String>();
    static final List<String> userJobActualTables = new LinkedList<String>();

    static {
        for (int i = 0; i <= 9; i++) {
            userActualTables.add("user_" + i);
        }

        for (int i = 0; i <= 9; i++) {
            userJobActualTables.add("user_job_" + i);
        }
    }


    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String user;

    @Value("${db.password}")
    private String password;

    @Value("${db.driver}")
    private String driverClass;

    @Bean(name = "dataSource", destroyMethod = "close")
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "shardingDataSource")
    public DataSource shardingDataSource(@Qualifier("dataSource") DataSource dataSource) throws SQLException {
        //dataSourceRule描述数据源分布规则
        DataSourceRule dataSourceRule = createDataSourceRule(dataSource);

        //tableRule描述数据表分布规则
        TableRule userTableRule = TableRule.builder(userlogicTable)
                .actualTables(userActualTables)
                .dataSourceRule(dataSourceRule)
                .tableShardingStrategy(createUserTableShardingStrategy())
                .generateKeyColumn(userGenerateKeyColumn)
                .build();

        TableRule userJobTableRule = TableRule.builder(userJoblogicTable)
                .actualTables(userJobActualTables)
                .dataSourceRule(dataSourceRule)
                .tableShardingStrategy(createUserJobTableShardingStrategy())
                .generateKeyColumn(userJobGenerateKeyColumn)
                .build();

//        List<TableRule> tableRules = Collections.EMPTY_LIST;//UnsupportedOperationException

        //sharding-rule
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
//                .tableRules(Arrays.asList(userTableRule, userJobTableRule))
                .tableRules(Arrays.asList(userTableRule))
//                .databaseShardingStrategy(createDatabaseShardingStrategy())
                .build();

        //props
        Properties props = new Properties();

        props.setProperty("sql.show", "true");

        //shardingDataSource
        DataSource shardingDataSource = ShardingDataSourceFactory.createDataSource(shardingRule, props);
        return shardingDataSource;
    }

    @Bean(name = "shardingDataSource")
    public ShardingProperties shardingProperties() {
        Properties props = new Properties();
        props.put(ShardingPropertiesConstant.SQL_SHOW.getKey(), "true");
        return new ShardingProperties(props);
    }



    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("shardingDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(resolver.getResources(MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("shardingDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    private DataSourceRule createDataSourceRule(DataSource dataSource) {
        Map<String, DataSource> result = new HashMap<String, DataSource>();
        result.put("dataSource", dataSource);
        return new DataSourceRule(result);
    }

    private TableShardingStrategy createUserTableShardingStrategy() {
        return new TableShardingStrategy(shardingColumn, new DynamicUserTableShardingAlgorithm());
    }

    private TableShardingStrategy createUserJobTableShardingStrategy() {
        return new TableShardingStrategy(shardingColumn, new DynamicUserJobTableShardingAlgorithm());
    }

    private DatabaseShardingStrategy createDatabaseShardingStrategy() {
        return new DatabaseShardingStrategy(shardingColumn, new DefaultDatabaseShardingAlgorithm());
    }

    private DatabaseShardingStrategy createDefaultDatabaseShardingStrategy() {
        return null;
    }
}
