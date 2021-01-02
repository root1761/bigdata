package partitioner;

import model.Clazz;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/23 22:08
 */
public class MaxPartitioner extends Partitioner<Clazz, DoubleWritable> {

    public int getPartition(Clazz clazz, DoubleWritable doubleWritable, int i) {
        return (clazz.getClassName().hashCode() & 2147483647) % i;

    }
}
