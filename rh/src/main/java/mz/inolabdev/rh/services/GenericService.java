package mz.inolabdev.rh.services;

import java.util.List;

public interface GenericService<T> {

    public T create(T t);  
    
    public List<T> getAll();

    public T find(Long id);
    
    public T update(T t); 
 
    public long count();
    
    public void delete(Object id);
}
