package com.liyuan.bmpower.Api.Controller;

import com.liyuan.bmpower.Api.form.PowerApiQueryForm;
import com.liyuan.bmpower.annotation.NotToken;
import com.liyuan.bmpower.constants.Power;
import com.liyuan.bmpower.controller.BaseController;
import com.liyuan.bmpower.domain.condition.power.PowerCondition;
import com.liyuan.bmpower.domain.condition.role.RoleCondition;
import com.liyuan.bmpower.domain.condition.rolepowerref.RolePowerRefCondition;
import com.liyuan.bmpower.domain.exception.bmpowerException;
import com.liyuan.bmpower.domain.po.power.PowerPo;
import com.liyuan.bmpower.domain.po.role.RolePo;
import com.liyuan.bmpower.domain.po.rolepowerref.RolePowerRefPo;
import com.liyuan.bmpower.domain.response.PageListResponse;
import com.liyuan.bmpower.domain.response.ResponseEntity;
import com.liyuan.bmpower.form.role.RoleQueryForm;
import com.liyuan.bmpower.service.PowerService;
import com.liyuan.bmpower.service.RolePowerRefService;
import com.liyuan.bmpower.service.RoleService;
import com.liyuan.bmpower.util.CopyUtil;
import com.liyuan.bmpower.util.JwtUser;
import com.liyuan.bmpower.util.JwtUtil;
import com.liyuan.bmpower.vo.power.PowerVo;
import com.liyuan.bmpower.vo.role.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LiYuan
 * @Description:权限管理
 * @Date: 15:43 2018/9/19
 */
@RestController
@RequestMapping("/api/power")
@Api(value = "/api/power", description = "权限")
public class PowerApiController extends BaseController {

    @Autowired
    private PowerService powerService;

    @NotToken
	@ApiOperation(value = "查询项目权限列表(带分页)",notes = "查询项目权限列表(带分页)",httpMethod = "POST")
	@PostMapping(value = "/queryList")
	public List<PowerVo> queryList(@RequestBody@Valid PowerApiQueryForm form) throws bmpowerException {

        PowerCondition condition = CopyUtil.transfer(form, PowerCondition.class);
        condition.setPageSize(Integer.MAX_VALUE);
		List<PowerVo> voList = new ArrayList<>();
		int count = powerService.queryCount(condition);
		if (count > 0) {
			List<PowerPo> poList = powerService.queryList(condition);
			voList = CopyUtil.transfer(poList, PowerVo.class);
		}
		return voList;
	}
}