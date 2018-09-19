package com.liyuan.bmpower.service.impl;

import com.liyuan.bmpower.util.PageHelperUtil;
import com.liyuan.bmpower.domain.condition.BaseCondition;
import com.liyuan.bmpower.mapper.BaseMapper;
import com.liyuan.bmpower.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.liyuan.bmpower.domain.exception.bmpowerException;
import java.util.List;

public abstract class BaseServiceImpl<T, C extends BaseCondition, M extends BaseMapper<T, C>> implements BaseService<T, C> {
    @Autowired
    private M mapper;

    @Override
    public T query(Object id) {
        T o = mapper.select(id);
        return o;
    }

    @Override
    public T queryWithValid(Object id) throws bmpowerException {
        T o = mapper.select(id);
        if (o == null) {
            throw new bmpowerException(id + "对应的记录为空");
        }
        return o;
    }

    @Override
    public List<T> queryList(C condition) {
        PageHelperUtil.startPage(condition.getPageNum(), condition.getPageSize(),condition.getOrderBy());
        List<T> list = mapper.selectList(condition);
        return list;
    }

    @Override
    public int queryCount(C condition) {
        PageHelperUtil.startPage(0, 0,null);
        return mapper.count(condition);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int update(T po) {
        int row = mapper.update(po);
        return row;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateWithValid(T po) throws bmpowerException {
        int row = mapper.update(po);
        if (row == 0) {
            throw new bmpowerException("修改失败");
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void insert(T po) {
        mapper.insert(po);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void delete(Object id) {
        mapper.delete(id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteByCondition(C condition) {
        mapper.deleteByCondition(condition);
    }


    /**
     * 返回对应的Mapper
     *
     * @return
     */
    protected M getMapper() {
        return mapper;
    }
}
