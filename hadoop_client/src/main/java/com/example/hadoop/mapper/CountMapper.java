package com.example.hadoop.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 *
 * @author Louyp
 * @version 1.0
 * @data 2020/07/17 17:26
 */
public class CountMapper extends Mapper<IntWritable, Text,Text,IntWritable> {
    @Override
    protected void map(IntWritable key, Text value, Context context) throws IOException, InterruptedException {
        final String s = value.toString();
        final String[] s1 = s.split(" ");
        for (String s2 : s1) {
            context.write(new Text(s2),new IntWritable(1));
        }
    }
}
