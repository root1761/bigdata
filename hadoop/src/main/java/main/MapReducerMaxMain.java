package main;

import mapper.MaxMapper;
import model.Clazz;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import partitioner.MaxPartitioner;
import reducer.MaxReducer;

import java.io.IOException;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/23 21:06
 */
public class MapReducerMaxMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration=new Configuration();
        configuration.set("fs.defaultFS","hdfs://192.168.42.143:9000");
        final FileSystem fileSystem = FileSystem.get(configuration);
        final Job job = Job.getInstance(configuration);
        job.setJarByClass(MapReducerMaxMain.class);
        job.setInputFormatClass(CombineTextInputFormat.class);
        CombineTextInputFormat.addInputPath(job,new Path("/dir1/d.txt"));
        job.setOutputFormatClass(TextOutputFormat.class);
        final Path path = new Path("/result");
        if(fileSystem.exists(path)){
            fileSystem.delete(path,true);
        }
        TextOutputFormat.setOutputPath(job,path);
        job.setMapperClass(MaxMapper.class);
        job.setReducerClass(MaxReducer.class);
        job.setPartitionerClass(MaxPartitioner.class);
        job.setNumReduceTasks(3);
        job.setOutputValueClass(DoubleWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DoubleWritable.class);
        job.setMapOutputKeyClass(Clazz.class);
job.waitForCompletion(true);
    }
}
//class102	235.0
//class105	133.0
//class101	123.0


//class102	235.0
//class101	123.0
//class105	133.0