package combiner;

import model.User;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/23 20:44
 */
public class CountCombiner extends Reducer<User, NullWritable, User,NullWritable> {
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
