package partitioner;

import model.User;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/23 20:12
 */
public class CountPartitioner extends Partitioner<User, NullWritable> {


    public int getPartition(User user, NullWritable nullWritable, int i) {
        if(user.getSalary()<10000) {
            return 0;
        }else if(10000 <= user.getSalary() &&user.getSalary()<20000){
            return 1;
        }else {
        return 2;}
    }
}
