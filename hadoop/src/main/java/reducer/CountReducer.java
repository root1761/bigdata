package reducer;

import model.User;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.metrics.spi.NullContextWithUpdateThread;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/13 16:03
 */
public class CountReducer extends Reducer<User, NullWritable, User,NullWritable> {
    @Override
    protected void reduce(User key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
       /* while(iterator.hasNext()){
            final IntWritable next = iterator.next();
            final int i = next.get();
            count+=i;
        }
        context.write(key,new IntWritable(count));*/
       context.write(key,NullWritable.get());
    }
}
