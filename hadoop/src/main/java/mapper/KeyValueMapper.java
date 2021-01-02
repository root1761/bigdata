package mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 *
 * @author Louyp
 * @version 1.0
 * @data 2020/07/17 17:26
 */
public class KeyValueMapper extends Mapper<Text, Text,Text,Text> {
    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
      context.write(key,value);
    }
}
