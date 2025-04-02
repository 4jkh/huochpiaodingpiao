package com.entity;

public class Usage {
    private int prompt_tokens;

    private int completion_tokens;

    private int total_tokens;

    private Prompt_tokens_details prompt_tokens_details;

    public void setPrompt_tokens(int prompt_tokens){
        this.prompt_tokens = prompt_tokens;
    }
    public int getPrompt_tokens(){
        return this.prompt_tokens;
    }
    public void setCompletion_tokens(int completion_tokens){
        this.completion_tokens = completion_tokens;
    }
    public int getCompletion_tokens(){
        return this.completion_tokens;
    }
    public void setTotal_tokens(int total_tokens){
        this.total_tokens = total_tokens;
    }
    public int getTotal_tokens(){
        return this.total_tokens;
    }
    public void setPrompt_tokens_details(Prompt_tokens_details prompt_tokens_details){
        this.prompt_tokens_details = prompt_tokens_details;
    }
    public Prompt_tokens_details getPrompt_tokens_details(){
        return this.prompt_tokens_details;
    }

}
