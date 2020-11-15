package com.idea.readingisgood.mapper;

import com.idea.readingisgood.entity.BaseEntity;

/**
 * @param <D> DTO
 * @param <E> Entity
 */
public interface BaseMapper<D, E extends BaseEntity> {
    /**
     * @param entity which should be converted
     * @return dto
     */
    D entityToDTO(E entity);

    /**
     * @param dto which should be converted
     * @return entity
     */
    E dtoToEntity(D dto);
}
