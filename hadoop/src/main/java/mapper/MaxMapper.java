package mapper;

import model.Clazz;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/23 21:08
 */
public class MaxMapper extends Mapper<LongWritable, Text, Clazz, DoubleWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
         String[] s = value.toString().split(" ");
         context.write(new Clazz(s[0]),new DoubleWritable(Double.parseDouble(s[1])));

    }
}
