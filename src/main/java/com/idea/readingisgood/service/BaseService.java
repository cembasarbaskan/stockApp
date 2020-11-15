package com.idea.readingisgood.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import com.idea.readingisgood.entity.BaseEntity;
import com.idea.readingisgood.entity.response.BaseResponse;

/**
 * @param <E> Entity
 * @param <D> DTO
 */
public abstract class BaseService<E extends BaseEntity, D> {

    /**
     * @return list of related DTO(s)
     */
    public abstract ResponseEntity<BaseResponse> fetchAll(int start, int size);

    /**
     * @param id of entity
     * @return DTO
     */
    public abstract ResponseEntity<BaseResponse> fetchOneById(String id);

    /**
     * @param id of entity
     */
    public abstract ResponseEntity<BaseResponse> deleteOneById(String id);

    /**
     * @param dto has been saved dto
     * @return saved Entity
     */
    public abstract ResponseEntity<BaseResponse> save(D dto);

    public abstract ResponseEntity<BaseResponse> update(D dto);

    /**
     * @param start the page start value
     * @param size the size of every page
     * @param sortBy the sort value
     * @return the Pageable
     */
    public Pageable createPageRequest(int start, int size, String sortBy) {
        return PageRequest.of(start, size, Sort.by(sortBy).ascending());
    }

}
