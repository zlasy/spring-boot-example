package com.example.springbootai.avatar;

import com.aliyun.avatar20220130.models.QueryRunningInstanceResponse;
import com.aliyun.avatar20220130.models.SendCommandResponse;

public interface AvatarService {

    public com.aliyun.avatar20220130.models.StartInstanceResponse start();
    public com.aliyun.avatar20220130.models.StartInstanceResponse startRTMP();

    public com.aliyun.avatar20220130.models.StopInstanceResponse  stop();

    public com.aliyun.avatar20220130.models.SendTextResponse sendText(String text);

    public SendCommandResponse sendCommand(String code);

    public QueryRunningInstanceResponse query();
}
