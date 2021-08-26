package br.com.andre.blog.post.service.shared.utils;

import br.com.andre.blog.post.utils.PageInfo;
import br.com.andre.blog.post.utils.Pagination;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class PageInfoImpl<T> implements PageInfo<T> {
    private Collection<T> content;
    private Pagination pagination;
    private boolean first;
    private boolean last;
    private boolean empty;
    private int totalPages;
    private long totalElements;

    public PageInfoImpl(Collection<T> content, Pagination pagination) {
        this.content = content;
        this.pagination = pagination;
    }

    @Override
    public Collection<T> content() {
        return content;
    }

    @Override
    public Pagination pagination() {
        return pagination;
    }

    @Override
    public boolean first() {
        return first;
    }

    @Override
    public boolean last() {
        return last;
    }

    @Override
    public boolean empty() {
        return empty;
    }

    @Override
    public int totalPages() {
        return totalPages;
    }

    @Override
    public long totalElements() {
        return totalElements;
    }

}
