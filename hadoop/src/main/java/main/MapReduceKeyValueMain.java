package main;

import mapper.CountMapper;
import mapper.KeyValueMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.log4j.BasicConfigurator;
import reducer.CountReducer;
import reducer.KeyValueReducer;

import java.io.IOException;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/17 16:10
 */
public class MapReduceKeyValueMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        BasicConfigurator.configure(); //自动快速地使用缺省Log4j环境

        Configuration configuration=new Configuration();
        configuration.set("fs.defaultFS", "hdfs://192.168.42.143:9000");
        configuration.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator",":");
        FileSystem fileSystem=FileSystem.get(configuration);
        final Job job = Job.getInstance(configuration);
        job.setJarByClass(MapReduceKeyValueMain.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        Path path=new Path("/dir1/b.txt");
        TextInputFormat.addInputPath(job,path);
        job.setOutputFormatClass(TextOutputFormat.class);
        Path path1=new Path("/result");
        if(fileSystem.exists(path1)){
            fileSystem.delete(path1,true);
        }
        TextOutputFormat.setOutputPath(job,path1);
        job.setMapperClass(KeyValueMapper.class);
        job.setReducerClass(KeyValueReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        System.exit(job.waitForCompletion(true)?0:1);


    }
}
