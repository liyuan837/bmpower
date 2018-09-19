package com.liyuan.bmpower.service.impl;

import com.liyuan.bmpower.domain.po.power.PowerPo;
import com.liyuan.bmpower.domain.condition.power.PowerCondition;
import com.liyuan.bmpower.mapper.PowerMapper;
import com.liyuan.bmpower.domain.exception.bmpowerException;
import com.liyuan.bmpower.service.PowerService;
import org.springframework.stereotype.Service;

@Service
public class PowerServiceImpl extends BaseServiceImpl<PowerPo, PowerCondition, PowerMapper> implements PowerService {

    @Override
    public PowerPo queryWithValid(Object id) throws bmpowerException {
        return super.queryWithValid(id);
    }
}