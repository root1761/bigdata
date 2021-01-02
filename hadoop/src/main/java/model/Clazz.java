package model;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/23 21:54
 */
public class Clazz implements WritableComparable {
    private String className;

    public Clazz() {
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "className='" + className + '\'' +
                '}';
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Clazz(String className) {
        this.className = className;
    }

    public int compareTo(Object o) {
        if(this.className!=((Clazz) o).className){
            return this.className.compareTo(((Clazz) o).className);
        }
        return 0;
    }

    public void write(DataOutput dataOutput) throws IOException {
    dataOutput.writeUTF(className);
    }

    public void readFields(DataInput dataInput) throws IOException {
this.className=dataInput.readUTF();
    }
}
