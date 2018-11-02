package edu.fzu.lbs.service;

import edu.fzu.lbs.entity.param.PageParam;
import org.springframework.data.domain.Page;

public interface CrudService<T, V> {

    Page<T> getList(V param, PageParam pageParam);

    void update(T obj);

    void deleteById(Long id);

}
