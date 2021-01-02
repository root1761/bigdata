package com.example.hadoop.main;

import com.example.hadoop.mapper.CountMapper;
import com.example.hadoop.reducer.CountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/17 16:10
 */
public class MapReduceCountMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration=new Configuration();
        FileSystem fileSystem=FileSystem.get(configuration);
        final Job job = Job.getInstance(configuration);
        job.setJarByClass(MapReduceCountMain.class);
        job.setInputFormatClass(TextInputFormat.class);
        Path path=new Path("/dir1/c.txt");
        TextInputFormat.addInputPath(job,path);
        job.setOutputFormatClass(TextOutputFormat.class);
        Path path1=new Path("/result");
        if(fileSystem.exists(path1)){
            fileSystem.delete(path1,true);
        }
        job.setMapperClass(CountMapper.class);
        job.setReducerClass(CountReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        System.exit(job.waitForCompletion(true)?0:1);


    }
}
