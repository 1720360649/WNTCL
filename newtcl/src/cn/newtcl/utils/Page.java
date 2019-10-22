package cn.newtcl.utils;

import org.springframework.stereotype.Component;

/*
  User: 晨梦意志
  Date: 2019/6/2
  Time: 10:39
  Notes:分页类
*/
@Component
public class Page {
    private Integer page=1;

    private Integer limit;

    private Integer offset;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        this.offset=(this.page-1)*this.limit;
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", limit=" + limit +
                ", offset=" + offset +
                '}';
    }
}
