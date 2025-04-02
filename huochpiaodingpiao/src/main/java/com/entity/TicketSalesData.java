package com.entity;

import java.util.List;

public class TicketSalesData {
    private List<String> datas;
    private List<String> names;
    private List<Integer> nums;
    private List<Float> moneys;

    public List<String> getDatas() {
        return datas;
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<Integer> getNums() {
        return nums;
    }

    public void setNums(List<Integer> nums) {
        this.nums = nums;
    }

    public List<Float> getMoneys() {
        return moneys;
    }

    public void setMoneys(List<Float> moneys) {
        this.moneys = moneys;
    }
}
