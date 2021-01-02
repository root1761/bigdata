package mapper;

import model.User;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 *
 * @author Louyp
 * @version 1.0
 * @data 2020/07/17 17:26
 */
public class CountMapper extends Mapper<LongWritable, Text, User, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        final String s = value.toString();
        final String[] s1 = s.split("-");
            context.write(new User(s1[0],Integer.parseInt(s1[1]),Double.parseDouble(s1[3])),NullWritable.get());
    }
}
