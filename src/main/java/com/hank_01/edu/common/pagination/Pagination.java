package com.hank_01.edu.common.pagination;

import com.github.pagehelper.Page;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class Pagination {

    private int nextRow = 0;

    private int prePageNum = 0;

    private int pageNum;

    private int nextPageNum = 0;

    private int pageSize = 0;

    private long totalCount = 0;

    private List<?> list;

    public Pagination() {
    }

    public Pagination(List<?> list) {
        if (!CollectionUtils.isEmpty(list)) {
            this.list = list;
            this.nextRow = ((Page<?>) list).getEndRow();
            this.pageNum = ((Page<?>) list).getPageNum();
            this.prePageNum = ((Page<?>) list).toPageInfo().getPrePage();
            this.nextPageNum = ((Page<?>) list).toPageInfo().getNextPage();
            this.pageSize = ((Page<?>) list).getPageSize();
            this.totalCount = ((Page<?>) list).getTotal();
        }
    }

    public void setNextRow(int nextRow) {
        this.nextRow = nextRow;
    }

    public int getNextRow() {
        return nextRow;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPrePageNum(int prePageNum) {
        this.prePageNum = prePageNum;
    }

    public int getPrePageNum() {
        return prePageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    public int getNextPageNum() {
        return nextPageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public List<?> getList() {
        return list;
    }

}
