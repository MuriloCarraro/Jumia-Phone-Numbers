package com.murilo.jumia.model.DTO;

import java.util.List;

public class PaginatedRepresentation<TReturn> {

    private final Long total;
    private final Integer totalPages;
    private final Integer page;
    private final List<TReturn> data;

    public static <TReturn> PaginatedRepresentation<TReturn> of(Long total, Integer totalPages, Integer page, List<TReturn> data) {
        return new PaginatedRepresentation<>(total, totalPages, page, data);
    }

    private PaginatedRepresentation(Long total, Integer totalPages, Integer page, List<TReturn> data) {
        this.total = total;
        this.totalPages = totalPages;
        this.page = page;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }
    public Integer getTotalPages() {
        return totalPages;
    }
    public Integer getPage() {
        return page;
    }
    public List<TReturn> getData() {
        return data;
    }
}
