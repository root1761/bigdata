package main;

import com.sun.tools.javac.util.BasicDiagnosticFormatter;
import combiner.CountCombiner;
import mapper.CountMapper;
import model.User;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.lib.input.CombineFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.log4j.BasicConfigurator;
import partitioner.CountPartitioner;
import reducer.CountReducer;
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
        BasicConfigurator.configure(); //自动快速地使用缺省Log4j环境

        Configuration configuration=new Configuration();
        configuration.set("fs.defaultFS", "hdfs://192.168.42.144:8020");

        FileSystem fileSystem=FileSystem.get(configuration);
        final Job job = Job.getInstance(configuration);
        job.setJarByClass(MapReduceCountMain.class);
        Path path=new Path("/dir1/a.txt");
        MultipleInputs.addInputPath(job,path,TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        Path path1=new Path("/result");
        if(fileSystem.exists(path1)){
            fileSystem.delete(path1,true);
        }
        TextOutputFormat.setOutputPath(job,path1);
        job.setPartitionerClass(CountPartitioner.class);
        job.setMapperClass(CountMapper.class);
        job.setReducerClass(CountReducer.class);
        job.setNumReduceTasks(3);
       // job.setCombinerClass(CountCombiner.class);
        job.setMapOutputKeyClass(User.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(User.class);
        job.setOutputValueClass(NullWritable.class);
        System.exit(job.waitForCompletion(true)?0:1);


    }
}
