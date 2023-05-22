package by.fin.processing.mapper;

import org.springframework.stereotype.Component;

@Component
public interface AbstractMapper<T, D> {
    D mapToDto(T t);

    T mapToEntity(D d);
}