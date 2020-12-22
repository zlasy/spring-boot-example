package com.example.springbootelasticjob.controller;

import javax.annotation.Resource;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.OneOffJobBootstrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class OneOffJobController {

    private static final String RES_TEXT = "{\"msg\":\"OK\"}";

    @Resource(name = "manualScriptJobBean")
    private OneOffJobBootstrap manualScriptJob;

    @Autowired
    @Qualifier(value = "occurErrorNoticeDingtalkBean")
    private OneOffJobBootstrap occurErrorNoticeDingtalkJob;

    @Autowired
    @Qualifier(value = "occurErrorNoticeWechatBean")
    private OneOffJobBootstrap occurErrorNoticeWechatJob;

    @Autowired
    @Qualifier(value = "occurErrorNoticeEmailBean")
    private OneOffJobBootstrap occurErrorNoticeEmailJob;

    @GetMapping("/execute/manualScriptJob")
    public String executeManualScriptJob() {
        manualScriptJob.execute();
        return RES_TEXT;
    }

    @GetMapping("/execute/occurErrorNoticeDingtalkJob")
    public String executeOneOffJob() {
        occurErrorNoticeDingtalkJob.execute();
        return RES_TEXT;
    }

    @GetMapping("/execute/occurErrorNoticeWechatJob")
    public String executeOccurErrorNoticeWechatJob() {
        occurErrorNoticeWechatJob.execute();
        return RES_TEXT;
    }

    @GetMapping("/execute/occurErrorNoticeEmailJob")
    public String executeOccurErrorNoticeEmailJob() {
        occurErrorNoticeEmailJob.execute();
        return RES_TEXT;
    }
}