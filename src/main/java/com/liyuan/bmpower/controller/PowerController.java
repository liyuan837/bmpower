package com.liyuan.bmpower.controller;

import com.liyuan.bmpower.domain.condition.rolepowerref.RolePowerRefCondition;
import com.liyuan.bmpower.domain.po.power.PowerPo;
import com.liyuan.bmpower.domain.condition.power.PowerCondition;
import com.liyuan.bmpower.form.power.*;
import com.liyuan.bmpower.service.RolePowerRefService;
import com.liyuan.bmpower.util.JwtUser;
import com.liyuan.bmpower.util.JwtUtil;
import com.liyuan.bmpower.vo.power.PowerVo;
import com.liyuan.bmpower.service.PowerService;
import com.liyuan.bmpower.domain.exception.bmpowerException;
import com.liyuan.bmpower.util.CopyUtil;
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
 * @Description:权限管理
 * @Date: 13:41 2018/9/19
 */
@RestController
@RequestMapping("/power")
@Api(value = "/power", description = "细粒度权限")
public class PowerController extends BaseController {

	@Autowired
	private PowerService powerService;

	@Autowired
    private RolePowerRefService rolePowerRefService;

	@ApiOperation(value = "查询细粒度权限",notes = "根据ID查询细粒度权限",httpMethod = "GET")
	@GetMapping(value = "/query")
	public ResponseEntity<PowerVo> query(@RequestHeader("Authorization") String authorization, @ApiParam(value = "主键", required = true)@RequestParam Integer id) throws bmpowerException {
        JwtUtil.checkLogin(authorization);

	    PowerPo po = powerService.queryWithValid(id);
		PowerVo vo = CopyUtil.transfer(po, PowerVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "查询细粒度权限数量",notes = "查询细粒度权限数量",httpMethod = "POST")
	@PostMapping(value = "/queryCount")
	public ResponseEntity<Integer> queryCount(@RequestHeader("Authorization") String authorization, @RequestBody@Valid PowerQueryForm form) throws bmpowerException {
        JwtUser jwtUser =JwtUtil.checkLogin(authorization);

	    PowerCondition condition = CopyUtil.transfer(form, PowerCondition.class);
		int count = powerService.queryCount(condition);
		return getSuccessResult(count);
	}

	@ApiOperation(value = "查询细粒度权限列表",notes = "查询细粒度权限列表",httpMethod = "POST")
	@PostMapping(value = "/queryList")
	public ResponseEntity<PageListResponse<PowerVo>> queryList(@RequestHeader("Authorization") String authorization, @RequestBody@Valid PowerQueryForm form) throws bmpowerException {
        JwtUser jwtUser =JwtUtil.checkLogin(authorization);

        PowerCondition condition = CopyUtil.transfer(form, PowerCondition.class);
		condition.setPageNum(0);
		condition.setPageSize(Integer.MAX_VALUE);
		List<PowerPo> poList = powerService.queryList(condition);
		List<PowerVo> voList = CopyUtil.transfer(poList, PowerVo.class);
		return getSuccessResult(voList);
	}

	@ApiOperation(value = "查询细粒度权限列表(带分页)",notes = "查询细粒度权限列表(带分页)",httpMethod = "POST")
	@PostMapping(value = "/queryPageList")
	public ResponseEntity<PageListResponse<PowerVo>> queryPageList(@RequestHeader("Authorization") String authorization, @RequestBody@Valid PowerQueryForm form) throws bmpowerException {
        JwtUser jwtUser =JwtUtil.checkLogin(authorization);

        PowerCondition condition = CopyUtil.transfer(form, PowerCondition.class);
		List<PowerVo> voList = new ArrayList<>();
		int count = powerService.queryCount(condition);
		if (count > 0) {
			List<PowerPo> poList = powerService.queryList(condition);
			voList = CopyUtil.transfer(poList, PowerVo.class);
		}
		return getSuccessResult(getPageListResponse(condition.getPageNum(),condition.getPageSize(),count,voList));
	}

	@ApiOperation(value = "新增细粒度权限",notes = "新增细粒度权限",httpMethod = "POST")
	@PostMapping(value = "/add")
	public ResponseEntity<PowerVo> add(@RequestHeader("Authorization") String authorization, @RequestBody@Valid PowerCreateForm form) throws bmpowerException {
        JwtUser jwtUser =JwtUtil.checkLogin(authorization);

        Date optTime = new Date();

        PowerPo po = CopyUtil.transfer(form, PowerPo.class);

		po.setAddTime(optTime);
		po.setAddUserCode(jwtUser.getUserCode());
		po.setOptTime(optTime);
		po.setOptUserCode(jwtUser.getUserCode());
		po.setUserCode(jwtUser.getUserCode());

		powerService.insert(po);
		PowerVo vo = CopyUtil.transfer(po, PowerVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "修改细粒度权限",notes = "修改细粒度权限",httpMethod = "POST")
	@PostMapping(value = "/update")
	public ResponseEntity update(@RequestHeader("Authorization") String authorization, @RequestBody@Valid PowerUpdateForm form) throws bmpowerException {
        JwtUser jwtUser =JwtUtil.checkLogin(authorization);

        PowerPo po = CopyUtil.transfer(form, PowerPo.class);
		po.setOptTime(new Date());
		po.setOptUserCode(jwtUser.getUserCode());

		powerService.update(po);
		return getSuccessResult();
	}

	@ApiOperation(value = "（单个）删除细粒度权限",notes = "删除细粒度权限",httpMethod = "POST")
	@PostMapping(value = "/delete")
	public ResponseEntity delete(@RequestHeader("Authorization") String authorization, @RequestBody@Valid PowerDeleteForm form) throws bmpowerException {
        JwtUtil.checkLogin(authorization);
        //[1]删除 角色——权限 关联关系
        RolePowerRefCondition condition = new RolePowerRefCondition();
        condition.setPowerId(form.getId());
        rolePowerRefService.deleteByCondition(condition);

        //[2]删除权限
        powerService.delete(form.getId());
		return getSuccessResult();
	}

}