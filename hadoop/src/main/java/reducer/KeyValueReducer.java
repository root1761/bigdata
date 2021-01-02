package reducer;

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
public class KeyValueReducer extends Reducer<Text, Text, Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        final Iterator<Text> iterator = values.iterator();
        int count = 0;
        while(iterator.hasNext()){
            final Text next = iterator.next();
          context.write(key,next

          );
        }
    }
}
