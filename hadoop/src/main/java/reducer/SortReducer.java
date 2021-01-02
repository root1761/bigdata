package reducer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/19 21:28
 */
public class SortReducer extends Reducer<DoubleWritable, Text,NullWritable,Text> {
    @Override
    protected void reduce(DoubleWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
         Iterator<Text> iterator = values.iterator();
        while(iterator.hasNext()) {
            Text next = iterator.next();
            context.write(NullWritable.get(), next);
        }
    }
}
