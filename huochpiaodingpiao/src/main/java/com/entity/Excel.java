package com.entity;

public class Excel {
    private String riqi;
    private String checi;
    private String chufa;
    private String daozhan;
    //票种数字
    private String piaozhong;
    //票种
    private String piaozhongValue;
    //销售量
    private String xiaoshouliang;
    //销售额度
    private Float xiaoshoue;
    //退票量
    private String tuipiaoliang;
    //退票率
    private Double tuipiaolv;
    //座位使用率
    private Double zuoweishiyonglv;


    //是否退票
    private Integer shifoutuipiao;
    //总共座位
    private Integer zongzuowei;

    public String getPiaozhongValue() {
        return piaozhongValue;
    }

    public void setPiaozhongValue(String piaozhongValue) {
        this.piaozhongValue = piaozhongValue;
    }

    public Integer getZongzuowei() {
        return zongzuowei;
    }

    public void setZongzuowei(Integer zongzuowei) {
        this.zongzuowei = zongzuowei;
    }

    public Integer getShifoutuipiao() {
        return shifoutuipiao;
    }

    public void setShifoutuipiao(Integer shifoutuipiao) {
        this.shifoutuipiao = shifoutuipiao;
    }

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

    public String getPiaozhong() {
        return piaozhong;
    }

    public void setPiaozhong(String piaozhong) {
        this.piaozhong = piaozhong;
    }

    public String getXiaoshouliang() {
        return xiaoshouliang;
    }

    public void setXiaoshouliang(String xiaoshouliang) {
        this.xiaoshouliang = xiaoshouliang;
    }

    public Float getXiaoshoue() {
        return xiaoshoue;
    }

    public void setXiaoshoue(Float xiaoshoue) {
        this.xiaoshoue = xiaoshoue;
    }

    public String getTuipiaoliang() {
        return tuipiaoliang;
    }

    public void setTuipiaoliang(String tuipiaoliang) {
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
