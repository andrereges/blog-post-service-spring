package br.com.andre.blog.post.service.shared.utils;

import br.com.andre.blog.post.utils.Pagination;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationImpl implements Pagination {
    private int page;
    private int size;
    private String sort;

    public PaginationImpl(int page, int size) {
        this.page = page;
        this.size = size;
    }

    @Override
    public int page() {
        return page;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String sort() {
        return sort;
    }

}
