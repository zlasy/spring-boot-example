package com.example.springbootai.hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.seg.Config;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.Viterbi.ViterbiSegment;
import com.hankcs.hanlp.seg.common.Term;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MySegment {

    public static void main(String[] args) {
        test();
//        try {
////            segment();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private static void test() {
        HanLP.Config.enableDebug(false);
        String path = System.getProperty("user.dir") + "/" + "data/dictionary/custom/CustomDictionary.txt;" +
            System.getProperty("user.dir") + "/" + "data/dictionary/custom/MyDictionary.txt";
        path = path.replace("\\", "/");
        String text = "当当银铃铛的发放规则是什么样的";
        Segment segment = HanLP.newSegment().enableCustomDictionary(false);
        Segment seg = new ViterbiSegment(path);
        System.out.println("不启用字典的分词结果：" + segment.seg(text));
        System.out.println("默认分词结果：" + HanLP.segment(text));
        seg.enableCustomDictionaryForcing(true).enableCustomDictionary(true);
        List<Term> termList = seg.seg(text);
        System.out.println("自定义字典的分词结果：" + termList);
    }

    private static void segment() throws IOException {
        File file = new File("D:\\2.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fileWriter);

        String path = System.getProperty("user.dir") + "/" + "data/dictionary/custom/CustomDictionary.txt;" +
            System.getProperty("user.dir") + "/" + "data/dictionary/custom/MyDictionary.txt";
        path = path.replace("\\", "/");
        HanLP.Config.ShowTermNature = false;
        Segment segment = new ViterbiSegment(path);
        segment.enableCustomDictionary(true);
        IOUtil.LineIterator iterator = IOUtil.readLine("d:\\1.txt");
        while (iterator.hasNext()) {
            String line = iterator.next();
            List<String> list = segment.seg(line).stream().map(x -> x.word).collect(Collectors.toList());
            bw.write(String.join(" ", list));
            bw.newLine();
        }

        bw.close();
    }
}


