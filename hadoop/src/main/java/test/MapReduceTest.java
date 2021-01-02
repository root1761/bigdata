package test;

import model.User;
import org.apache.commons.configuration.interpol.ConfigurationInterpolator;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.junit.jupiter.api.Test;
import sun.security.krb5.Config;

import javax.ws.rs.core.Context;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.nio.ByteBuffer;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/19 10:40
 */
public class MapReduceTest {
@Test
    public  void test2() throws IOException {
        Configuration configuration=new Configuration();
        configuration.set("fs.defaultFS", "hdfs://192.168.42.144:8020");
        final FileSystem open = FileSystem.get(configuration);
        Path path=new Path("/result/part-r-00002");
        final FSDataInputStream open1 = open.open(path);
        final ByteBuffer allocate = ByteBuffer.allocate(1024);
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        while(open1.read(allocate)!=-1){
            allocate.flip();
            while(allocate.hasRemaining()){
                byteArrayOutputStream.write(allocate.get());
            }
            allocate.clear();
        }
        System.out.println(byteArrayOutputStream.toString());
        open.close();
    }
    @Test
    public void test1() throws IOException {
Configuration configuration=new Configuration();
        configuration.set("fs.defaultFS", "hdfs://192.168.42.143:9000");
        final FileSystem fileSystem = FileSystem.get(configuration);
        Path path=new Path("/dir1/a.seq");
        final SequenceFile.Writer writer = SequenceFile.createWriter(fileSystem, configuration, path, Text.class
                , User.class);
        writer.append(new Text("key"),new User("zhangsan",12));
        writer.close();;
    }
    @Test
    public void test3() throws IOException {
        Configuration configuration=new Configuration();
        configuration.set("fs.defaultFS", "hdfs://192.168.42.143:9000");
        final FileSystem fileSystem = FileSystem.get(configuration);
        Path path=new Path("/dir1/a.seq");

        final SequenceFile.Reader reader = new SequenceFile.Reader(fileSystem, path, configuration);
        Text text = new Text();
        final User user = new User();
        while(reader.next(text,user)){
            System.out.println(text.toString()+" "+user.toString());
        };

    }
    @Test
    public void test4(){
    
    }
}
