package main;


import mapper.SortMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import reducer.SortReducer;

import java.io.IOException;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/19 21:33
 */
public class MapReducerSortMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration=new Configuration();
      configuration.set("fs.defaultFS","hdfs://192.168.42.143:9000");
        final FileSystem fileSystem = FileSystem.get(configuration);
        final Job job = Job.getInstance(configuration);
        job.setJarByClass(MapReducerSortMain.class);
        job.setInputFormatClass(TextInputFormat.class);
        Path path=new Path("/dir1/a.txt");
        TextInputFormat.addInputPath(job,path);
        job.setOutputFormatClass(TextOutputFormat.class);
        Path path1=new Path("/result");
        if (fileSystem.exists(path1)){
            fileSystem.delete(path1,true);
        }
        TextOutputFormat.setOutputPath(job,path1);
        job.setReducerClass(SortReducer.class);

        job.setMapperClass(SortMapper.class);
        job.setMapOutputKeyClass(DoubleWritable.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setOutputKeyClass(NullWritable.class);
        job.waitForCompletion(true);



    }
}
