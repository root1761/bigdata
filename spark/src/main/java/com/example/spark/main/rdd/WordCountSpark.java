package com.example.spark.main.rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/08/06 22:26
 */
public class WordCountSpark {


    public static void main(String[] args) {

        final SparkConf wordCount = new SparkConf().setAppName("WordCount").set("spark.executor.memory", "1g").setMaster("spark://192.168.42.143:7077").setJars(new String[]{"D:/Git/project/spark/target/spark-1.0.1.jar"});
        JavaSparkContext javaSparkContext=new JavaSparkContext(wordCount);
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        JavaRDD<Integer> parallelize = javaSparkContext.parallelize(list);
        final JavaRDD<Integer> map = parallelize.map(s->s+1);
        System.out.println(map.collect());
 /*       final Integer reduce = parallelize.reduce(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                System.out.println(integer+" "+integer2);
                return integer + integer2;
            }
        });
*//*
        System.out.println(reduce);
*//*
        System.out.println(parallelize.collect());*/
        final JavaRDD<Integer> filter = map.filter(new Function<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) throws Exception {
                return integer==2||integer==3;
            }
        });/*iter -> {

            System.out.println(iter.intValue());
            return iter.equals(2)||iter.equals(3);
        }*/
        System.out.println(filter.collect());
        System.out.println(filter.count());
        javaSparkContext.close();
    }
}
