package base.service.impl;

import base.model.BaseEntity;
import base.repository.BaseRepository;
import base.service.BaseService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class BaseServiceImpl<ID extends Serializable,TYPE extends BaseEntity<ID>, R extends BaseRepository<ID,TYPE>> implements BaseService<ID,TYPE> {
    protected final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public void save(TYPE entity) throws SQLException {
        repository.save(entity);
    }

    @Override
    public int delete(ID id) throws SQLException {
        return repository.delete(id);
    }

    @Override
    public List<TYPE> findAll() throws SQLException {
        return repository.findAll();
    }

    @Override
    public TYPE findById(ID id) throws SQLException {
        return repository.findById(id);
    }

    @Override
    public int update(TYPE entity) throws SQLException {
        return repository.update(entity);
    }
}
