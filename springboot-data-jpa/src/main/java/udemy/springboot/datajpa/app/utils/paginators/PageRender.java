package udemy.springboot.datajpa.app.utils.paginators;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender<T> {

    @Getter
    private final List<PageItem> pages = new ArrayList<>();

    @Getter
    private final String url;

    private final Page<T> page;

    @Getter
    private int pageTotal;

    private int pageElements;

    @Getter
    private int pageIndex;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.pageTotal = page.getTotalPages();
        this.pageElements = page.getSize();
        this.pageIndex = page.getNumber();

        int from, to;
        if(pageTotal <= pageElements) {
            from = 1;
            to = pageTotal;
        } else {
            if (pageIndex <= pageElements / 2) {
                from = 1;
                to = pageElements;
            } else if(pageIndex >= pageTotal - pageElements / 2) {
                from = pageTotal - pageElements + 1;
                to = pageElements;
            } else {
                from = pageIndex - pageElements / 2;
                to = pageElements;
            }
        }
        for (int i = 0; i < to; i++) {
            pages.add(new PageItem(from + i, pageIndex == from + i));
        }
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public boolean hasNext() {
        return page.hasNext();
    }

    public boolean hasPrevious() {
        return page.hasPrevious();
    }
}
