package com.example.lenovo.textviewspannerdalogexercise.fastjson;

/**
 * Created by xue on 2018/4/28.
 */

public class FastjsonAttributeBean {
    private int start, limit, total;
    private String rows;

    @Override
    public String toString() {
        return " start=" + start + ", limit=" + limit + ", total=" + total
                + ", rows=" + rows;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }


    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
