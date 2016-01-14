package com.lang71.server.converter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConverter<E, D> {

    public List<E> convertToEntity(List<D> dtos) {
        List<E> res = new ArrayList<>(dtos.size());
        for (D dto : dtos) {
            res.add(convertToEntity(dto));
        }
        return res;
    }

    public List<D> convertToDTO(List<E> entities) {
        List<D> res = new ArrayList<>(entities.size());
        for (E entity : entities) {
            res.add(convertToDTO(entity));
        }
        return res;
    }

    public abstract E convertToEntity(D dto);

    public abstract D convertToDTO(E entity);

}
