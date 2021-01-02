package com.example.hadoop.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/13 16:03
 */
public class CountReducer extends Reducer<Text, IntWritable, Text,IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        final Iterator<IntWritable> iterator = values.iterator();
        int count = 0;
        while(iterator.hasNext()){
            final IntWritable next = iterator.next();
            final int i = next.get();
            count+=i;
        }
        context.write(key,new IntWritable(count));
    }
}
