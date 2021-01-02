package com.example.hive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;


/**
 * @author Louyp
 * @version 1.0
 * @data 2020/09/01 9:55
 */
@SpringBootTest
public class HiveApplicationTest {
    @Test
    public void test() throws Exception {
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        final Connection connection = DriverManager.getConnection("jdbc:hive2://hadoop1:10000/test", "root", "root");
        final PreparedStatement preparedStatement = connection.prepareStatement("select * from t_user");
        final ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            final int anInt = resultSet.getInt(1);
            final String string = resultSet.getString(2);
            System.out.println(anInt+" "+string);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
