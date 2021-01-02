package model;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/22 11:21
 */
public class User implements WritableComparable {
    private String name;
    private Integer id;
    private Integer age;
    private Double salary;

    public User(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User() {
    }

    public User(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public int compareTo(Object o) {
        User that = (User) o;
     /*   if (this.name != that.name) {
            return this.name.hashCode() - that.name.hashCode();
        } else if (this.age != that.age) {
            return this.age - that.age;
        } else if (this.salary != that.salary) {
            return (int) (this.salary - that.salary);
        }*/
     if(that.salary!=this.salary){
         return (int)(that.salary-this.salary);
     }
        return 0;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.name);
        dataOutput.writeInt(this.age);
        dataOutput.writeDouble(this.salary);

    }

    public void readFields(DataInput dataInput) throws IOException {
        this.name = dataInput.readUTF();
        this.age = dataInput.readInt();
        this.salary=dataInput.readDouble();
    }
}
