package com.freestyle.service.commons;

import java.util.List;

public class Page<E> {

    private int total;
    
    private List<E> rows;
    
    private int pageNumber;
    
    private int totalPageNum;
    
    private int pageSize;

    public Page() {
        super();
    }

    public Page(int total, List<E> rows, int pageNumber, int pageSize) {
        super();
        this.total = total;
        this.rows = rows;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPageNum = (int) Math.ceil(total * 1.0 / pageSize);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + pageNumber;
        result = prime * result + pageSize;
        result = prime * result + ((rows == null) ? 0 : rows.hashCode());
        result = prime * result + total;
        result = prime * result + totalPageNum;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Page<?> other = (Page<?>) obj;
        if (pageNumber != other.pageNumber)
            return false;
        if (pageSize != other.pageSize)
            return false;
        if (rows == null) {
            if (other.rows != null)
                return false;
        } else if (!rows.equals(other.rows))
            return false;
        if (total != other.total)
            return false;
        if (totalPageNum != other.totalPageNum)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Page [total=" + total + ", rows=" + rows + ", pageNumber="
                + pageNumber + ", totalPageNum=" + totalPageNum + ", pageSize="
                + pageSize + "]";
    }
   
}
