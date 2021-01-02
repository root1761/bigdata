package com.example.spark.main.sql;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Properties;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/09/01 14:10
 */
public class SparkSqlDataSource {
    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL data sources example")
                .getOrCreate();
runJdbcDatasetExample(spark);

        spark.stop();
    }
    private static void runJdbcDatasetExample(SparkSession spark) {
        // $example on:jdbc_dataset$
        // Note: JDBC loading and saving can be achieved via either the load/save or jdbc methods
        // Loading data from a JDBC source
        Dataset<Row> jdbcDF = spark.read()
                .format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/test?serverTimezone=UTC")
                .option("dbtable", "t_user")
                .option("user", "root")
                .option("password", "root")
                .load();
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "username");
        connectionProperties.put("password", "password");
        Dataset<Row> jdbcDF2 = spark.read()
                .jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties);

        // Saving data to a JDBC source
        jdbcDF.write()
                .format("jdbc")
                .option("url", "jdbc:postgresql:dbserver")
                .option("dbtable", "schema.tablename")
                .option("user", "username")
                .option("password", "password")
                .save();

        jdbcDF2.write()
                .jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties);

        // Specifying create table column data types on write
        jdbcDF.write()
                .option("createTableColumnTypes", "name CHAR(64), comments VARCHAR(1024)")
                .jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties);
        // $example off:jdbc_dataset$
    }
}
