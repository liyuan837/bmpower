package com.liyuan.bmpower.service;

import com.liyuan.bmpower.domain.exception.bmpowerException;
import java.util.List;

public interface BaseService<T, C> {
    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    T query(Object id);

    /**
     * 查询验证的详情,查不到则抛异常
     *
     * @param id
     * @return
     * @throws bmpowerException
     */
    T queryWithValid(Object id) throws bmpowerException;

    /**
     * 查询列表
     *
     * @param condition
     * @return
     */
    List<T> queryList(C condition);

    /**
     * 查询数量
     *
     * @param condition
     * @return
     */
    int queryCount(C condition);

    /**
     * 修改
     *
     * @param po
     * @return
     */
    int update(T po);

    /**
     * 修改,失败则抛异常
     *
     * @param po
     * @throws bmpowerException
     */
    void updateWithValid(T po) throws bmpowerException;

    /**
     * 新增
     *
     * @param po
     */
    void insert(T po);

    /**
     * 删除
     *
     * @param id
     * @throws bmpowerException
     */
    void delete(Object id);

    /**
     * 按条件批量删除
     *
     * @param condition
     * @throws bmpowerException
     */
    void deleteByCondition(C condition);
}
