package com.entity;

import java.util.List;

public class Root {
    private List<Choices> choices ;

    private String object;

    private Usage usage;

    private int created;

    private String system_fingerprint;

    private String model;

    private String id;

    public void setChoices(List<Choices> choices){
        this.choices = choices;
    }
    public List<Choices> getChoices(){
        return this.choices;
    }
    public void setObject(String object){
        this.object = object;
    }
    public String getObject(){
        return this.object;
    }
    public void setUsage(Usage usage){
        this.usage = usage;
    }
    public Usage getUsage(){
        return this.usage;
    }
    public void setCreated(int created){
        this.created = created;
    }
    public int getCreated(){
        return this.created;
    }
    public void setSystem_fingerprint(String system_fingerprint){
        this.system_fingerprint = system_fingerprint;
    }
    public String getSystem_fingerprint(){
        return this.system_fingerprint;
    }
    public void setModel(String model){
        this.model = model;
    }
    public String getModel(){
        return this.model;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }

}
