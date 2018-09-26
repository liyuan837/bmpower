package com.liyuan.bmpower.controller;

import com.liyuan.bmpower.annotation.NotToken;
import com.liyuan.bmpower.constants.Power;
import com.liyuan.bmpower.domain.condition.role.RoleCondition;
import com.liyuan.bmpower.domain.condition.rolepowerref.RolePowerRefCondition;
import com.liyuan.bmpower.domain.exception.bmpowerException;
import com.liyuan.bmpower.domain.po.power.PowerPo;
import com.liyuan.bmpower.domain.po.role.RolePo;
import com.liyuan.bmpower.domain.po.rolepowerref.RolePowerRefPo;
import com.liyuan.bmpower.domain.response.PageListResponse;
import com.liyuan.bmpower.domain.response.ResponseEntity;
import com.liyuan.bmpower.form.role.RoleCreateForm;
import com.liyuan.bmpower.form.role.RoleDeleteForm;
import com.liyuan.bmpower.form.role.RoleQueryForm;
import com.liyuan.bmpower.form.role.RoleUpdateForm;
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
import java.util.Date;
import java.util.List;

/**
 * @Author: LiYuan
 * @Description:角色管理
 * @Date: 15:43 2018/9/19
 */
@RestController
@RequestMapping("/role2")
@Api(value = "/role2", description = "权限祖")
public class RoleController2 extends BaseController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private RolePowerRefService rolePowerRefService;

    @Autowired
    private PowerService powerService;

    @ApiOperation(value = "查询权限祖",notes = "根据ID查询权限祖",httpMethod = "GET")
    @GetMapping(value = "/query")
    public RoleVo query( @ApiParam(value = "主键", required = true)@RequestParam Integer id) throws bmpowerException {
        //[1]查询权限组
        RolePo po = roleService.query(id);
        if(po == null){
            return null;
        }

        RoleVo vo = CopyUtil.transfer(po, RoleVo.class);

        //[2]查询对应的权限列表
        RolePowerRefCondition condition = new RolePowerRefCondition();
        condition.setPageSize(Integer.MAX_VALUE);
        condition.setRoleId(id);
        List<RolePowerRefPo> refPoList = rolePowerRefService.queryList(condition);

        List<PowerVo> powerList = new ArrayList<>();
        if(refPoList!=null && refPoList.size() > 0){
            for(RolePowerRefPo ref: refPoList){
                PowerPo powerPo = powerService.query(ref.getPowerId());
                if(powerPo.getState() != null && powerPo.getState() == Power.PowerState.USED){
                    powerList.add(CopyUtil.transfer(powerPo,PowerVo.class));
                }
            }
            vo.setPowerList(powerList);
        }
        return vo;
    }

	@ApiOperation(value = "查询权限祖列表(带分页)",notes = "查询权限祖列表(带分页)",httpMethod = "POST")
	@PostMapping(value = "/queryPageList")
	public PageListResponse<RoleVo> queryPageList(@RequestHeader("Authorization") String authorization, @RequestBody@Valid RoleQueryForm form) throws bmpowerException {
        JwtUser jwtUser =JwtUtil.checkLogin(authorization);

        RoleCondition condition = CopyUtil.transfer(form, RoleCondition.class);
		List<RoleVo> voList = new ArrayList<>();
		int count = roleService.queryCount(condition);
		if (count > 0) {
			List<RolePo> poList = roleService.queryList(condition);
			voList = CopyUtil.transfer(poList, RoleVo.class);
		}
		return getPageListResponse(condition.getPageNum(),condition.getPageSize(),count,voList);
	}
}