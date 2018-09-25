package com.liyuan.bmpower.controller;

import com.liyuan.bmpower.annotation.NotToken;
import com.liyuan.bmpower.constants.Power;
import com.liyuan.bmpower.domain.condition.rolepowerref.RolePowerRefCondition;
import com.liyuan.bmpower.domain.po.power.PowerPo;
import com.liyuan.bmpower.domain.po.role.RolePo;
import com.liyuan.bmpower.domain.condition.role.RoleCondition;
import com.liyuan.bmpower.domain.po.rolepowerref.RolePowerRefPo;
import com.liyuan.bmpower.form.role.*;
import com.liyuan.bmpower.service.PowerService;
import com.liyuan.bmpower.service.RolePowerRefService;
import com.liyuan.bmpower.util.*;
import com.liyuan.bmpower.vo.power.PowerVo;
import com.liyuan.bmpower.vo.role.RoleVo;
import com.liyuan.bmpower.service.RoleService;
import com.liyuan.bmpower.domain.exception.bmpowerException;
import com.liyuan.bmpower.domain.response.ResponseEntity;
import com.liyuan.bmpower.domain.response.PageListResponse;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
/**
 * @Author: LiYuan
 * @Description:角色管理
 * @Date: 15:43 2018/9/19
 */
@RestController
@RequestMapping("/role")
@Api(value = "/role", description = "权限祖")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private RolePowerRefService rolePowerRefService;

    @Autowired
    private PowerService powerService;

    @NotToken
	@ApiOperation(value = "查询权限祖",notes = "根据ID查询权限祖",httpMethod = "GET")
	@GetMapping(value = "/query")
	public ResponseEntity<RoleVo> query( @ApiParam(value = "主键", required = true)@RequestParam Integer id) throws bmpowerException {
//        JwtUser jwtUser = JwtUtil.checkLogin(authorization);

        //[1]查询权限组
        RolePo po = roleService.query(id);
        if(po == null){
            return getFailResult("权限读取失败");
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
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "查询权限祖数量",notes = "查询权限祖数量",httpMethod = "POST")
	@PostMapping(value = "/queryCount")
	public ResponseEntity<Integer> queryCount(@RequestHeader("Authorization") String authorization, @RequestBody@Valid RoleQueryForm form) throws bmpowerException {
        JwtUser jwtUser =JwtUtil.checkLogin(authorization);

        RoleCondition condition = CopyUtil.transfer(form, RoleCondition.class);
		int count = roleService.queryCount(condition);
		return getSuccessResult(count);
	}

	@ApiOperation(value = "查询权限祖列表",notes = "查询权限祖列表",httpMethod = "POST")
	@PostMapping(value = "/queryList")
	public ResponseEntity<PageListResponse<RoleVo>> queryList(@RequestHeader("Authorization") String authorization, @RequestBody@Valid RoleQueryForm form) throws bmpowerException {
        JwtUser jwtUser =JwtUtil.checkLogin(authorization);


        RoleCondition condition = CopyUtil.transfer(form, RoleCondition.class);
		condition.setPageNum(0);
		condition.setPageSize(Integer.MAX_VALUE);
		List<RolePo> poList = roleService.queryList(condition);
		List<RoleVo> voList = CopyUtil.transfer(poList, RoleVo.class);
		return getSuccessResult(voList);
	}

	@ApiOperation(value = "查询权限祖列表(带分页)",notes = "查询权限祖列表(带分页)",httpMethod = "POST")
	@PostMapping(value = "/queryPageList")
	public ResponseEntity<PageListResponse<RoleVo>> queryPageList(@RequestHeader("Authorization") String authorization, @RequestBody@Valid RoleQueryForm form) throws bmpowerException {
        JwtUser jwtUser =JwtUtil.checkLogin(authorization);

        RoleCondition condition = CopyUtil.transfer(form, RoleCondition.class);
		List<RoleVo> voList = new ArrayList<>();
		int count = roleService.queryCount(condition);
		if (count > 0) {
			List<RolePo> poList = roleService.queryList(condition);
			voList = CopyUtil.transfer(poList, RoleVo.class);
		}
		return getSuccessResult(getPageListResponse(condition.getPageNum(),condition.getPageSize(),count,voList));
	}

	@ApiOperation(value = "新增权限祖",notes = "新增权限祖",httpMethod = "POST")
	@PostMapping(value = "/add")
	public ResponseEntity<RoleVo> add(@RequestHeader("Authorization") String authorization, @RequestBody@Valid RoleCreateForm form) throws bmpowerException {
        JwtUser jwtUser =JwtUtil.checkLogin(authorization);

        checkCreateForm(form);
        Date optTime = new Date();

        //[1]新增记录
        RolePo po = CopyUtil.transfer(form, RolePo.class);
		po.setAddTime(optTime);
		po.setAddUserCode(jwtUser.getUserCode());
		po.setOptTime(optTime);
		po.setOptUserCode(jwtUser.getUserCode());
		po.setUserCode(jwtUser.getUserCode());
		roleService.insert(po);

		RoleVo vo = CopyUtil.transfer(po, RoleVo.class);
		return getSuccessResult(vo);
	}


    @ApiOperation(value = "修改权限祖",notes = "修改权限祖",httpMethod = "POST")
	@PostMapping(value = "/update")
	public ResponseEntity update(@RequestHeader("Authorization") String authorization, @RequestBody@Valid RoleUpdateForm form) throws bmpowerException {
        JwtUser jwtUser =JwtUtil.checkLogin(authorization);
        checkUpdateForm(form);

        RolePo po = CopyUtil.transfer(form, RolePo.class);
		po.setOptTime(new Date());
		po.setOptUserCode(jwtUser.getUserCode());
		roleService.update(po);
		return getSuccessResult();
	}

    /**
     * 为权限组分配权限
     * @param authorization
     * @param form
     * @return
     * @throws bmpowerException
     */
    @ApiOperation(value = "权限分配",notes = "权限分配",httpMethod = "POST")
    @PostMapping(value = "/allocate")
    public ResponseEntity allocate(@RequestHeader("Authorization") String authorization, @RequestBody@Valid RoleUpdateForm form) throws bmpowerException {
        JwtUser jwtUser =JwtUtil.checkLogin(authorization);
        //[0]判断权限组是否存在
        RolePo rolePo = roleService.query(form.getId());
        if(rolePo == null){
            return getFailResult("该角色不存在");
        }

        List<PowerPo> powerList = CopyUtil.transfer(form.getPowerList(),PowerPo.class);

        //[2]更新关联关系
        if(powerList != null && powerList.size() > 0){
            for(PowerPo powerPo : powerList){
                if(powerPo.getState() != null && powerPo.getState() == 0){
                    //去除关联关系
                    RolePowerRefCondition condition = new RolePowerRefCondition();
                    condition.setRoleId(form.getId());
                    condition.setPowerId(powerPo.getId());
                    rolePowerRefService.deleteByCondition(condition);
                }else{
                    //添加关联关系
                    RolePowerRefCondition condition = new RolePowerRefCondition();
                    condition.setRoleId(form.getId());
                    condition.setPowerId(powerPo.getId());
                    if(rolePowerRefService.queryCount(condition) > 0){
                        continue;
                    }
                    RolePowerRefPo ref = new RolePowerRefPo();
                    ref.setRoleId(form.getId());
                    ref.setPowerId(powerPo.getId());
                    ref.setAddTime(new Date());
                    ref.setAddUserCode(jwtUser.getUserCode());
                    rolePowerRefService.insert(ref);
                }

            }
        }

        return getSuccessResult();
    }


    @ApiOperation(value = "（单个）删除权限祖",notes = "删除权限祖",httpMethod = "POST")
	@PostMapping(value = "/delete")
	public ResponseEntity delete(@RequestHeader("Authorization") String authorization, @RequestBody@Valid RoleDeleteForm form) throws bmpowerException {
        JwtUser jwtUser =JwtUtil.checkLogin(authorization);
        //[1]删除权限-角色关联列表
        RolePowerRefCondition condition = new RolePowerRefCondition();
        condition.setRoleId(form.getId());
        rolePowerRefService.deleteByCondition(condition);

        //[2]删除权限
        roleService.delete(form.getId());
		return getSuccessResult();
	}

    /**
     * 权限组名称重复交验
     * @param form
     */
    private void checkCreateForm(RoleCreateForm form) {
        RoleCondition condition = new RoleCondition();
        condition.setProjectId(form.getProjectId());
        condition.setName(form.getName());
        condition.setPageSize(Integer.MAX_VALUE);
        List<RolePo> rolePoList = roleService.queryList(condition);
        if(rolePoList != null && rolePoList.size() > 0){
            throw new bmpowerException("该角色名已存在");
        }
    }

    /**
     * 权限组名称重复交验
     * @param form
     */
    private void checkUpdateForm(RoleUpdateForm form) {
        RoleCondition condition = new RoleCondition();
        condition.setProjectId(form.getProjectId());
        condition.setName(form.getName());
        condition.setPageSize(Integer.MAX_VALUE);
        List<RolePo> rolePoList = roleService.queryList(condition);
        if(rolePoList != null && rolePoList.size() > 0){
            for(RolePo rolePo: rolePoList){
                if(!rolePo.getId().equals(form.getProjectId())){
                    throw new bmpowerException("该角色名已存在");
                }
            }
        }
    }
}