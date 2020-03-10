package com.example.springbootcommon.tools;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OnlineTimeTool {

    public static void main(String[] args){
        printTime();
    }

    private static void printTime() {
        String filename = "D:\\1.txt";
        String outFile = "D:\\out.txt";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<Long, StatisticResult> resultMap = new HashMap<>();
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8);
            BufferedReader br=new BufferedReader(in);
            BufferedWriter out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile,false), StandardCharsets.UTF_8));
            String temp=br.readLine();
            while(temp!=null){
                String[] strings = temp.split("\t");
                JournalLog log = new JournalLog();
                log.setUserId(Long.parseLong(strings[3]));
                log.setStatus(Integer.parseInt(strings[4]));
                log.setLogTime(sdf.parse(strings[6]));
                log.setFromPlatform(Integer.parseInt(strings[7]));
                if (log.getFromPlatform() == 0){
                    addLog(resultMap, log);
                }
//                System.out.println(strings[3] + "  " + strings[6]);
//                out.write(strings[3] + "  " + strings[6] + "\r\n");
                temp=br.readLine();
            }
            calculate(resultMap);
            printResult(resultMap, out);
            out.close();
            br.close();
            in.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void addLog(Map<Long, StatisticResult> resultMap, JournalLog log) {
        if (!resultMap.containsKey(log.getUserId())){
            resultMap.put(log.getUserId(), new StatisticResult(log.getUserId()));
        }
        resultMap.get(log.getUserId()).getList().add(log);
    }

    private static void calculate(Map<Long, StatisticResult> resultMap) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar start = Calendar.getInstance();
        start.setTime(sdf.parse("2020-03-01 09:00:00"));
        Calendar end = Calendar.getInstance();
        end.setTime(sdf.parse("2020-03-01 21:30:00"));
        for (StatisticResult result : resultMap.values()) {
            Map<String, TimeReport> map = result.getMap();
            for (JournalLog log : result.getList()) {
                Calendar logTime = Calendar.getInstance();
                logTime.setTime(log.getLogTime());
                String key = logTime.get(Calendar.MONTH) + "-" + logTime.get(Calendar.DAY_OF_MONTH);
                if (!map.containsKey(key)){ // 新的一天，重新开始计算，同时要考虑前一天最后一次
                    // 判断前一天最后一次之后是否还有时长
                    String lastKey = logTime.get(Calendar.MONTH) + "-" + (logTime.get(Calendar.DAY_OF_MONTH) - 1);
                    if (map.containsKey(lastKey)){
                        if (result.getLastTime().after(start) && result.getLastTime().before(end) && result.getLastStatus() < 4){
                            map.get(lastKey).add(result.getLastStatus(), calcTimeDuring(result.getLastTime(), end));
                        }
                    }
                    // 初始化新的一天的参数
                    map.put(key, new TimeReport());
                    result.setLastStatus(log.status);
                    result.setLastTime(logTime);
                    start.set(Calendar.DAY_OF_MONTH, logTime.get(Calendar.DAY_OF_MONTH));
                    end.set(Calendar.DAY_OF_MONTH, logTime.get(Calendar.DAY_OF_MONTH));
                    continue;
                }
                // 从第二次开始算
                if (logTime.before(start)){
                    System.out.println("未上班");
                    // 上班前
                } else if (!logTime.before(start) && !logTime.after(end)){
                    // 上班时
                    Calendar begin = result.lastTime.before(start) ? start : result.getLastTime();
                    map.get(key).add(result.getLastStatus(), calcTimeDuring(begin, logTime));
                } else if (result.getLastTime().before(end)){
                    // 下班后
//                    Calendar over = logTime.after(end) ? end : logTime;
                    map.get(key).add(result.getLastStatus(), calcTimeDuring(result.lastTime, end));
                }
                result.setLastStatus(log.getStatus());
                result.setLastTime(logTime);
                System.out.println(log.getUserId() + "  " + logTime.get(Calendar.DAY_OF_MONTH)
                        + "  " + logTime.get(Calendar.HOUR_OF_DAY)
                        + "  " + logTime.get(Calendar.MINUTE)
                        + "  " + logTime.get(Calendar.SECOND));
            }
        }
    }

    private static void printResult(Map<Long, StatisticResult> resultMap, BufferedWriter out) {
        for (StatisticResult result : resultMap.values()) {
            result.getMap().forEach((key, v) -> {
                try {
                    out.write(String.format("%s\tkey:%s\tonline:%d\tbusy:%d\tleave:%d\r\n", result.getUserId(), key, v.getOnlineTime(), v.getBusyTime(), v.getLeaveTime()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static int calcTimeDuring(Calendar begin, Calendar end){
        System.out.println(begin.getTime() + " | " + end.getTime());
        return (int) (end.getTimeInMillis() - begin.getTimeInMillis() ) / 1000;
    }

    @Data
    static class JournalLog {
        private Long userId;
        private Integer status;
        private Date logTime;
        private Integer fromPlatform;
    }

    @Data
    static class StatisticResult {
        private long userId;
        private LinkedList<JournalLog> list = new LinkedList<>();
        private int lastStatus;
        private Calendar lastTime;
        private Map<String, TimeReport> map = new HashMap<>();

        public StatisticResult(Long userId){
            this.userId = userId;
        }
    }

    @Data
    @NoArgsConstructor
    static class TimeReport {
        private int onlineTime;
        private int busyTime;
        private int leaveTime;

        public void add(int status, int time){
            switch (status){
                case 1:
                    onlineTime = getOnlineTime() + time;
                    System.out.println("1:" + time);
                    break;
                case 2:
                    busyTime = getBusyTime() + time;
                    System.out.println("2:" + time);
                    break;
                case 3:
                    leaveTime = getLeaveTime() + time;
                    System.out.println("3:" + time);
                    break;
                default:
            }
        }
    }
}
