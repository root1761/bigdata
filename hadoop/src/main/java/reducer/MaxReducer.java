package reducer;

import model.Clazz;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/23 21:34
 */
public class MaxReducer extends Reducer<Clazz, DoubleWritable,Text,DoubleWritable> {
    @Override
    protected void reduce(Clazz key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        final Iterator<DoubleWritable> iterator = values.iterator();
        double max=0;
      while (iterator.hasNext()){
          final DoubleWritable next = iterator.next();
          final double v = next.get();
          if(max<v){
              max=v;
          }
      }
      context.write(new Text(key.getClassName()),new DoubleWritable(max));
    }
}
