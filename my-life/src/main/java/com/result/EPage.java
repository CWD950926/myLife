package com.result;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;


public class EPage<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer total; //总记录数

    private Integer pages; //总页数

    private int current;//当前页
    private int size;//一页包含多少条记录
    private int currentSize;//当前返回条数

    private List<T> records;

    public EPage() {
    }

    public EPage(Integer total, Integer pages, int current, int size, int currentSize, List<T> records) {
        this.total = total;
        this.pages = pages;
        this.current = current;
        this.size = size;
        this.currentSize = currentSize;
        this.records = records;
    }

    public EPage(Integer total, Integer pages, int current, int size, List<T> records) {
        this.total = total;
        this.pages = pages;
        this.current = current;
        this.size = size;
        this.records = records;
    }

    public EPage(IPage wherePage, List<T> records) {
        if (wherePage != null){
            this.total = (int) wherePage.getTotal();
            this.pages = (int) wherePage.getPages();
            this.current = (int) wherePage.getCurrent();
            this.size = (int) wherePage.getSize();
        }


        this.records = records;
    }


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}