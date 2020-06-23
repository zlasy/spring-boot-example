package com.example.springbootcommon.java8.keyword;

import java.io.*;

public class TransientTest implements Serializable {
    private static final long serialVersionUID = 233858934995755239L;
    private String name1;
    private transient String name2;

    public TransientTest(String name1,String name2){
        this.name1 = name1;
        this.name2 = name2;
    }

    @Override
    public String toString(){
        return String.format("TransientTest.toString(): name1=%s,name2=%s", name1,name2);
    }

    public static void main(String[] args) {
        String name1="常规属性",name2="transient修饰的属性";
        TransientTest test = new TransientTest(name1, name2);
        System.out.println("序列化前："+test.toString());
        ObjectOutputStream outStream;
        ObjectInputStream inStream;
        String filePath = "H:/TransientTest.obj";
        try {
            outStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outStream.writeObject(test);

            inStream = new ObjectInputStream(new FileInputStream(filePath));
            TransientTest readObject = (TransientTest)inStream.readObject();
            System.out.println("序列化后："+readObject.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
