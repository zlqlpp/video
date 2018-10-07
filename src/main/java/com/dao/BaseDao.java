package com.dao;


import java.util.List;
import java.util.Map;

public interface BaseDao {

    public <T> List<T> find(String sql, Object[] params, Class<T> tClass);

    public <T> int addOrUpdateOrDelete(String sql, Object[] params, Class<T> tClass);

    public <T> Page<T> queryPagination(String sql, Object[] parameters,int pageNo, int pageSize,  Class<T> entity);

    public <T> T findForObject(String sql, Object[] args, Class<T> classT);

    public Map<String,Object> find(String sql,Object[] params);

    public List<Map<String,Object>> queryList(String sql,Object[] params);

    public int[] batchAdd(String sql,List<Object[]> objs);
}
