package repository;

public interface Repository<ID, T> {
    int size();
    boolean save(T entity);
    boolean delete(ID id);
    boolean update(T entity);
    T findOne(ID id);
    Iterable<T> findAll();
}
