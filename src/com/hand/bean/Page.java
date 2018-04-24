package com.hand.bean;

import java.util.List;

public class Page<T> {
    private int totalPage; //总页数
    private int currentPage; //当前页数
    private int currentCount; //当前页显示数目
    private int totalCount; //总共的数目
    private List<T> list; //返回给前端的数据 ,不管是什么界面需要分页的话 只用传递一个实体类就可以

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
