package com.entity;

import java.util.List;

public class BarChartData {
    private List<String> categories;
    private List<Integer> values1;
    private List<Float> values2;

    public BarChartData() {}

    public BarChartData(List<String> categories, List<Integer> values1, List<Float> values2) {
        this.categories = categories;
        this.values1 = values1;
        this.values2 = values2;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Integer> getValues1() {
        return values1;
    }

    public void setValues1(List<Integer> values1) {
        this.values1 = values1;
    }

    public List<Float> getValues2() {
        return values2;
    }

    public void setValues2(List<Float> values2) {
        this.values2 = values2;
    }
}
