package com.entity;

import com.alibaba.excel.annotation.ExcelProperty;

public class ExcelShow {
    @ExcelProperty("日期")
    private String riqi;
    @ExcelProperty("车次")
    private String checi;
    @ExcelProperty("出发")
    private String chufa;
    @ExcelProperty("到站")
    private String daozhan;
    //销售量
    @ExcelProperty("销售量")
    private Integer xiaoshouliang;
    //销售额度
    @ExcelProperty("销售额度")
    private Float xiaoshoue;
    //退票量
    @ExcelProperty("退票量")
    private double tuipiaoliang;
    //退票率
    @ExcelProperty("退票率")
    private Double tuipiaolv;
    //座位使用率
    @ExcelProperty("座位使用率")
    private Double zuoweishiyonglv;

    public String getRiqi() {
        return riqi;
    }

    public void setRiqi(String riqi) {
        this.riqi = riqi;
    }

    public String getCheci() {
        return checi;
    }

    public void setCheci(String checi) {
        this.checi = checi;
    }

    public String getChufa() {
        return chufa;
    }

    public void setChufa(String chufa) {
        this.chufa = chufa;
    }

    public String getDaozhan() {
        return daozhan;
    }

    public void setDaozhan(String daozhan) {
        this.daozhan = daozhan;
    }

    public Integer getXiaoshouliang() {
        return xiaoshouliang;
    }

    public void setXiaoshouliang(Integer xiaoshouliang) {
        this.xiaoshouliang = xiaoshouliang;
    }

    public Float getXiaoshoue() {
        return xiaoshoue;
    }

    public void setXiaoshoue(Float xiaoshoue) {
        this.xiaoshoue = xiaoshoue;
    }

    public double getTuipiaoliang() {
        return tuipiaoliang;
    }

    public void setTuipiaoliang(double tuipiaoliang) {
        this.tuipiaoliang = tuipiaoliang;
    }

    public Double getTuipiaolv() {
        return tuipiaolv;
    }

    public void setTuipiaolv(Double tuipiaolv) {
        this.tuipiaolv = tuipiaolv;
    }

    public Double getZuoweishiyonglv() {
        return zuoweishiyonglv;
    }

    public void setZuoweishiyonglv(Double zuoweishiyonglv) {
        this.zuoweishiyonglv = zuoweishiyonglv;
    }
}
