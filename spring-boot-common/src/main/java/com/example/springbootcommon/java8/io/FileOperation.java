package com.example.springbootcommon.java8.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileOperation {

    public static void main(String[] args) {
        readFileNames();
    }

    private static void readFileNames() {
        try {
            String path = "D:\\code\\order";		//要遍历的路径
            traverseFolderByContent(path, "xml", "product_id");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<File> traverseFolder(String path) {
        List<File> fileList = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
                return null;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        fileList.add(file2);
                        System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return fileList;
    }

    private static List<File> traverseFolderByContent(String path, String suffix, String content) throws IOException {
        List<File> fileList = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
//                System.out.println("文件夹是空的!");
                return null;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
//                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolderByContent(file2.getAbsolutePath(),suffix,content);
                    } else {
                        if (file2.getAbsolutePath().endsWith(suffix) && containStr(file2, content)){
                            fileList.add(file2);
                            System.out.println("文件:" + file2.getCanonicalPath());
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return fileList;
    }

    private static boolean containStr(File file, String content) {
        try {
            byte[] filecontent = new byte[(int) file.length()];
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();

            String tempContent = new String(filecontent, StandardCharsets.UTF_8);
            if (tempContent.indexOf(content) > -1){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
