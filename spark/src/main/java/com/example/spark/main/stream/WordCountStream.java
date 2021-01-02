package com.example.spark.main.stream;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.Optional;
import org.apache.spark.api.java.function.Function3;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.deploy.StandaloneResourceUtils$;
import org.apache.spark.resource.ResourceUtils;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.State;
import org.apache.spark.streaming.StateSpec;
import org.apache.spark.streaming.api.java.*;
import scala.Tuple2;

import java.util.Arrays;
import static org.apache.spark.resource.ResourceUtils.*;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/08/08 18:51
 */
public class WordCountStream {
    public static void main(String[] args) throws InterruptedException {
        SparkConf sparkConf=new SparkConf().setAppName("wordCountStream");
       //  SparkConf sparkConf=new SparkConf().setAppName("wordCountStream").setMaster("spark://192.168.42.143:7077").setJars(new String[]{"D:/Git/project/spark/target/spark-1.0.1.jar"});
        final JavaStreamingContext javaStreamingContext = new JavaStreamingContext(sparkConf, Duration.apply(10000));
        javaStreamingContext.checkpoint(".");
        final JavaReceiverInputDStream<String> tJavaReceiverInputDStream1 = javaStreamingContext.socketTextStream("192.168.42.144", 9999);

        System.out.println(tJavaReceiverInputDStream1);
        final JavaDStream<String> stringJavaDStream = tJavaReceiverInputDStream1.flatMap(iter -> {
            return Arrays.asList(iter.split(" ")).iterator();
        });
        final JavaPairDStream<String, Integer> stringIntegerJavaPairDStream = stringJavaDStream.mapToPair(iter -> (new Tuple2<>(iter, 1)));
        Function3<String, Optional<Integer>, State<Integer>, Tuple2<String, Integer>> mappingFunc =
                (word, one, state) -> {
                    int sum = one.orElse(0) + (state.exists() ? state.get() : 0);
                    Tuple2<String, Integer> output = new Tuple2<>(word, sum);
                    state.update(sum);
                    return output;
                };

        // DStream made of get cumulative counts that get updated in every batch
        JavaMapWithStateDStream<String, Integer, Integer, Tuple2<String, Integer>> stateDstream =
                stringIntegerJavaPairDStream.mapWithState(StateSpec.function(mappingFunc));
        stateDstream.foreachRDD((iter)->{
            iter.foreach(val->{
                System.out.println(val._1);
            });
        });
        stateDstream.print();
        javaStreamingContext.start();
javaStreamingContext.awaitTermination();

    }
}
