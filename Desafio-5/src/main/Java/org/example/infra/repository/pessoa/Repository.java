package org.example.infra.repository.pessoa;

import java.util.List;

public interface Repository<T> {

    public List<T> getList();

    public T findById(int id);

    void merge(T t);

    void insert(T t);

    void remove(T t);
}
