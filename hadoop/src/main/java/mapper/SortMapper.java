package mapper;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/19 21:22
 */
public class SortMapper extends Mapper<LongWritable,Text, DoubleWritable,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        final String[] split = value.toString().split("-");
        context.write(new DoubleWritable(Double.parseDouble(split[3])),value);
    }
}
