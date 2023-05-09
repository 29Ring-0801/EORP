package pers.lintao.eorp.dto;

import java.io.Serializable;

/**
 * @description:
 * @author: lilintao@163.com
 * @time: 2023/4/9 09时40分 周一
 */
public class PageDTO implements Serializable {

    private static final long serialVersionUID = -8565876284310006537L;

    private Long total;

    private Long pages;

    private Long current = 1L;

    private Long size = 10L;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageDTO{" +
                "total=" + total +
                ", pages=" + pages +
                ", current=" + current +
                ", size=" + size +
                '}';
    }
}
