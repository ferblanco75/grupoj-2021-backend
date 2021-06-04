package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import org.springframework.data.domain.Sort;

public class ReviewPage {
    private int pageNumber = 0;
    private int pageSize  = 10;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "date";

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public String getSortBy() {
        return sortBy;
    }
}
