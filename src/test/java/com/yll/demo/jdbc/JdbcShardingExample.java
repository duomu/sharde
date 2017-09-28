package com.yll.demo.jdbc;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.BindingTableRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.jdbc.core.datasource.ShardingDataSource;
import com.yll.sharde.algorithm.DefaultDatabaseShardingAlgorithm;
import com.yll.sharde.algorithm.DefaultTableShardingAlgorithm;
import com.yll.sharde.algorithm.DynamicUserJobTableShardingAlgorithm;
import com.yll.sharde.algorithm.DynamicUserTableShardingAlgorithm;
import com.yll.sharde.util.ReleaseUtil;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * @author：linlin.yang
 * @date：2017/9/14 10:27
 */
public class JdbcShardingExample {
    private final String shardingColumn = "user_id";
    private final String userGenerateKeyColumn = "user_id";
    private final String userJobGenerateKeyColumn = "id";
    private final String userlogicTable = "user_0";
    private final String userJoblogicTable = "user_job_0";
    private List<String> userActualTables = new LinkedList<String>();
    private List<String> userJobActualTables = new LinkedList<String>();

    {
        for (int i=0;i<=9;i++) {
            userActualTables.add("user_" + i);
        }

        for (int i=0;i<=9;i++) {
            userJobActualTables.add("user_job_" + i);
        }

    }

    public static void main(String[] args) throws SQLException {
        JdbcShardingExample app = new JdbcShardingExample();
        DataSource dataSource = app.getShardingDataSource();

        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
//            String sql = "select * from user_0";//ok
//            String sql = "select user_id from user_0";//ok
            String sql = "select user_id,name,age,address from user_0";//bug
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
//                System.out.println(rs.getLong(1));
                System.out.println(rs.getLong(1) + ", " + rs.getString(2) + ", " + rs.getInt(3) + ", " + rs.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ReleaseUtil.close(conn);
            ReleaseUtil.close(rs);
        }

    }

    private ShardingDataSource getShardingDataSource0() throws SQLException {
        List<String> userActualTables = new ArrayList<String>();
        List<String> userJobActualTables = new ArrayList<String>();
        for (int i=0;i<=9;i++) {
            userActualTables.add("user_" + i);
        }

        for (int i=0;i<=9;i++) {
            userJobActualTables.add("user_job_" + i);
        }
        DataSourceRule dataSourceRule = new DataSourceRule(createDataSourceMap());
        TableRule userTableRule = TableRule.builder("user_0").actualTables(userActualTables).dataSourceRule(dataSourceRule).build();
        TableRule userJobTableRule = TableRule.builder("user_job_0").actualTables(userJobActualTables).dataSourceRule(dataSourceRule).build();
        ShardingRule shardingRule = ShardingRule.builder().dataSourceRule(dataSourceRule).tableRules(Arrays.asList(userTableRule, userJobTableRule))
                .bindingTableRules(Collections.singletonList(new BindingTableRule(Arrays.asList(userTableRule, userJobTableRule))))
                .databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new DefaultDatabaseShardingAlgorithm()))
                .tableShardingStrategy(new TableShardingStrategy("user_id", new DefaultTableShardingAlgorithm())).build();
        return new ShardingDataSource(shardingRule);
    }

    public DataSource getShardingDataSource() throws SQLException {
        //dataSourceRule描述数据源分布规则
        DataSourceRule dataSourceRule = new DataSourceRule(createDataSourceMap());
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

        //sharding-rule
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(userTableRule, userJobTableRule))
                .bindingTableRules(Collections.singletonList(new BindingTableRule(Arrays.asList(userTableRule, userJobTableRule))))
                .databaseShardingStrategy(createDatabaseShardingStrategy())
                .build();

        //props
        Properties props = new Properties();
        props.setProperty("sql.show", "true");

        //shardingDataSource
        DataSource shardingDataSource = ShardingDataSourceFactory.createDataSource(shardingRule, props);
        return shardingDataSource;
    }

    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<String, DataSource>(1);
        result.put("ds_0", createDataSource("sharde"));
        return result;
    }

    private DataSource createDataSource(String dataSourceName) {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        result.setUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName));
        result.setUsername("root");
        result.setPassword("123456");
        return result;
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
}
