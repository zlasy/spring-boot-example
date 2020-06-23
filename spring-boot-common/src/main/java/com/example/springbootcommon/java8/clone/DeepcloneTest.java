package com.example.springbootcommon.java8.clone;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.*;

@Setter
@Getter
public class DeepcloneTest implements Cloneable, Serializable {

    Integer i = 1111;
    String s = "test";
    InnerObject c = new InnerObject();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public static void main(String[] args) throws CloneNotSupportedException {
        // shallowCopy
        DeepcloneTest d = new DeepcloneTest();
        DeepcloneTest e = (DeepcloneTest)d.clone();
        printField(d, e);

        // deepCopy
        DeepcloneTest f = new DeepcloneTest();
        DeepcloneTest g = (DeepcloneTest)f.deepCopy();
        printField(f, g);
    }

    private Object deepCopy(){
        Object obj = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(this);
            out.flush();
            out.close();

            // 从流中读出 byte array，调用readObject函数反序列化出对象
            ObjectInputStream in = new ObjectInputStream(
                    new ByteArrayInputStream(bos.toByteArray()));
            obj = in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private static void printField(DeepcloneTest d, DeepcloneTest e) {
        System.out.println("==========");
        System.out.println(d.getI());
        d.setI(1234);
        System.out.println(e.getI());
        System.out.println(d.getS());
        d.setS("test2");
        System.out.println(e.getS());
        System.out.println(d.c);
        d.c.setA("a2");
        System.out.println(e.c);
    }

    @Getter
    @Setter
    @ToString
    public class InnerObject implements Serializable{
        String a = "a1";

        Integer b = 123456;
    }
}
