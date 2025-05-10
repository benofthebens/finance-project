package org.financeproject.utils.mapper;

public interface Mapper<A, E> {
    A entityToApi(E entity);
    E apiToEntity(A api);
}
