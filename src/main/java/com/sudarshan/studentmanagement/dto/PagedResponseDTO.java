package com.sudarshan.studentmanagement.dto;

import java.util.List;

public class PagedResponseDTO<T> {

    private List<T> content;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int pageSize;
    private boolean last;

    public PagedResponseDTO(List<T> content,
                             int currentPage,
                             int totalPages,
                             long totalElements,
                             int pageSize,
                             boolean last) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
        this.last = last;
    }

    public List<T> getContent() {
        return content;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getPageSize() {
        return pageSize;
    }

    public boolean isLast() {
        return last;
    }
}
