package com.example.hbase.main;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/08/12 21:49
 */
public class HbaseGetMain {
    private static Connection connection;
    private static Configuration config;
    private static Table table;
    public static void main(String[] args) throws Exception {
        config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum","192.168.42.143,192.168.42.144,192.168.42.145");
        config.set("hbase.zookeeper.property.clientPort", "2181");
      connection = ConnectionFactory.createConnection(config);
       table = connection.getTable(TableName.valueOf("dept"));
       insertData();
    }
    public static void createTable() throws IOException {
       	// 创建表管理类
         		Admin admin =connection.getAdmin(); // hbase表管理

        // 创建表描述类
         		TableName tableName = TableName.valueOf("dept"); // 表名称
         		HTableDescriptor desc = new HTableDescriptor(tableName);
         		// 创建列族的描述类
         		HColumnDescriptor family = new HColumnDescriptor("info"); // 列族
         		// 将列族添加到表中
         		desc.addFamily(family);
         		HColumnDescriptor family2 = new HColumnDescriptor("subdept"); // 列族
         		// 将列族添加到表中
         		desc.addFamily(family2);
         		// 创建表
         		admin.createTable(desc); // 创建表4		System.out.println("创建表成功！");
    }
    public static void insertData() throws Exception {

        		ArrayList<Put> arrayList = new ArrayList<Put>();

        		Put put = new Put(Bytes.toBytes("0_1"));
         		put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("网络部"));
         		put.addColumn(Bytes.toBytes("subdept"), Bytes.toBytes("subdept1"), Bytes.toBytes("1_1"));
       		put.addColumn(Bytes.toBytes("subdept"), Bytes.toBytes("subdept2"), Bytes.toBytes("1_2"));
        		arrayList.add(put);

        		Put put1 = new Put(Bytes.toBytes("1_1"));
       		put1.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("开发部"));
         		put1.addColumn(Bytes.toBytes("info"), Bytes.toBytes("f_pid"), Bytes.toBytes("0_1"));

        		Put put2 = new Put(Bytes.toBytes("1_2"));
       		put2.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("测试部"));
       		put2.addColumn(Bytes.toBytes("info"), Bytes.toBytes("f_pid"), Bytes.toBytes("0_1"));

        		for (int i = 1; i <= 100; i++) {

            			put1.addColumn(Bytes.toBytes("subdept"), Bytes.toBytes("subdept"+i), Bytes.toBytes("2_"+i));
            			put2.addColumn(Bytes.toBytes("subdept"), Bytes.toBytes("subdept"+i), Bytes.toBytes("3_"+i));
             		}
       		arrayList.add(put1);
         		arrayList.add(put2);
        		//插入数据
         		table.put(arrayList);
        		//提交

        		System.out.println("数据插入成功！");
        	}
}
