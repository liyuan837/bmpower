package com.liyuan.bmpower.service.impl;

import com.liyuan.bmpower.domain.po.rolepowerref.RolePowerRefPo;
import com.liyuan.bmpower.domain.condition.rolepowerref.RolePowerRefCondition;
import com.liyuan.bmpower.mapper.RolePowerRefMapper;
import com.liyuan.bmpower.domain.exception.bmpowerException;
import com.liyuan.bmpower.service.RolePowerRefService;
import org.springframework.stereotype.Service;

@Service
public class RolePowerRefServiceImpl extends BaseServiceImpl<RolePowerRefPo, RolePowerRefCondition, RolePowerRefMapper> implements RolePowerRefService {

    @Override
    public RolePowerRefPo queryWithValid(Object id) throws bmpowerException {
        return super.queryWithValid(id);
    }
}