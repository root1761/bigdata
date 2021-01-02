package com.example.spark.main.rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/08/07 14:52
 */
public class SparkWordCount {
    private static final Pattern SPACE = Pattern.compile("-");

    public static void main(String[] args) {
        SparkWordCount sparkWordCount=new SparkWordCount();
        sparkWordCount.count(args);
    }
    public void count(String[] args) {

        System.out.println(args[0]);

            if (args.length < 1) {
                System.err.println("Usage: JavaWordCount <file>");
                System.exit(1);
            }
        SparkConf sparkConf=new SparkConf().setJars( new String[]{"D:/Git/project/spark/target/spark-1.0.1.jar"});
            SparkSession spark = SparkSession
                    .builder().config(sparkConf)
                    .appName("JavaWordCount")
                    .getOrCreate();

            JavaRDD<String> lines = spark.read().textFile(args[0]).javaRDD();
        System.out.println("textFile"+lines.collect());
            JavaRDD<String> words = lines.flatMap(s ->{
                return Arrays.asList(SPACE.split(s)).iterator();});
        System.out.println("words"+words.collect());
            JavaPairRDD
                    <String, Integer> ones = words.mapToPair(s -> new Tuple2<>(s, 1));

            JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> i1 + i2);
        System.out.println("reduceByKey"+counts.collect());
            List<Tuple2<String, Integer>> output = counts.collect();
            for (Tuple2<?,?> tuple : output) {
                System.out.println(tuple._1() + ": " + tuple._2());
            }
            spark.stop();

    }
}
