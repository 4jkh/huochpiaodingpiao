package com.entity;

import com.alibaba.dashscope.common.Message;

public class Choices {
    private Message message;

    private String finish_reason;

    private int index;

    private String logprobs;

    public void setMessage(Message message){
        this.message = message;
    }
    public Message getMessage(){
        return this.message;
    }
    public void setFinish_reason(String finish_reason){
        this.finish_reason = finish_reason;
    }
    public String getFinish_reason(){
        return this.finish_reason;
    }
    public void setIndex(int index){
        this.index = index;
    }
    public int getIndex(){
        return this.index;
    }
    public void setLogprobs(String logprobs){
        this.logprobs = logprobs;
    }
    public String getLogprobs(){
        return this.logprobs;
    }

}
