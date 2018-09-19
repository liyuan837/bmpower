package com.liyuan.bmpower.service.impl;

import com.liyuan.bmpower.domain.po.role.RolePo;
import com.liyuan.bmpower.domain.condition.role.RoleCondition;
import com.liyuan.bmpower.mapper.RoleMapper;
import com.liyuan.bmpower.domain.exception.bmpowerException;
import com.liyuan.bmpower.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RolePo, RoleCondition, RoleMapper> implements RoleService {

    @Override
    public RolePo queryWithValid(Object id) throws bmpowerException {
        return super.queryWithValid(id);
    }
}