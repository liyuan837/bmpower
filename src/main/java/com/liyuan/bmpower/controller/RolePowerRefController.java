package com.liyuan.bmpower.controller;

import com.liyuan.bmpower.domain.po.rolepowerref.RolePowerRefPo;
import com.liyuan.bmpower.domain.condition.rolepowerref.RolePowerRefCondition;
import com.liyuan.bmpower.form.rolepowerref.*;
import com.liyuan.bmpower.vo.rolepowerref.RolePowerRefVo;
import com.liyuan.bmpower.service.RolePowerRefService;
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

@RestController
@RequestMapping("/rolepowerref")
@Api(value = "/rolepowerref", description = "权限与组的映射关系")
public class RolePowerRefController extends BaseController {

	@Autowired
	private RolePowerRefService rolePowerRefService;

	@ApiOperation(value = "查询权限与组的映射关系",notes = "根据ID查询权限与组的映射关系",httpMethod = "GET")
	@GetMapping(value = "/query")
	public ResponseEntity<RolePowerRefVo> query(@ApiParam(value = "权限id", required = true)@RequestParam Integer powerId) throws bmpowerException {
		RolePowerRefPo po = rolePowerRefService.queryWithValid(powerId);
		RolePowerRefVo vo = CopyUtil.transfer(po, RolePowerRefVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "查询权限与组的映射关系数量",notes = "查询权限与组的映射关系数量",httpMethod = "POST")
	@PostMapping(value = "/queryCount")
	public ResponseEntity<Integer> queryCount(@RequestBody@Valid RolePowerRefQueryForm form) throws bmpowerException {
		RolePowerRefCondition condition = CopyUtil.transfer(form, RolePowerRefCondition.class);
		int count = rolePowerRefService.queryCount(condition);
		return getSuccessResult(count);
	}

	@ApiOperation(value = "查询权限与组的映射关系列表",notes = "查询权限与组的映射关系列表",httpMethod = "POST")
	@PostMapping(value = "/queryList")
	public ResponseEntity<PageListResponse<RolePowerRefVo>> queryList(@RequestBody@Valid RolePowerRefQueryForm form) throws bmpowerException {
		RolePowerRefCondition condition = CopyUtil.transfer(form, RolePowerRefCondition.class);
		condition.setPageNum(0);
		condition.setPageSize(Integer.MAX_VALUE);
		List<RolePowerRefPo> poList = rolePowerRefService.queryList(condition);
		List<RolePowerRefVo> voList = CopyUtil.transfer(poList, RolePowerRefVo.class);
		return getSuccessResult(voList);
	}

	@ApiOperation(value = "查询权限与组的映射关系列表(带分页)",notes = "查询权限与组的映射关系列表(带分页)",httpMethod = "POST")
	@PostMapping(value = "/queryPageList")
	public ResponseEntity<PageListResponse<RolePowerRefVo>> queryPageList(@RequestBody@Valid RolePowerRefQueryForm form) throws bmpowerException {
		RolePowerRefCondition condition = CopyUtil.transfer(form, RolePowerRefCondition.class);
		List<RolePowerRefVo> voList = new ArrayList<>();
		int count = rolePowerRefService.queryCount(condition);
		if (count > 0) {
			List<RolePowerRefPo> poList = rolePowerRefService.queryList(condition);
			voList = CopyUtil.transfer(poList, RolePowerRefVo.class);
		}
		return getSuccessResult(getPageListResponse(condition.getPageNum(),condition.getPageSize(),count,voList));
	}

	@ApiOperation(value = "新增权限与组的映射关系",notes = "新增权限与组的映射关系",httpMethod = "POST")
	@PostMapping(value = "/add")
	public ResponseEntity<RolePowerRefVo> add(@RequestBody@Valid RolePowerRefCreateForm form) throws bmpowerException {
		RolePowerRefPo po = CopyUtil.transfer(form, RolePowerRefPo.class);
		po.setAddTime(new Date());
		rolePowerRefService.insert(po);
		RolePowerRefVo vo = CopyUtil.transfer(po, RolePowerRefVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "修改权限与组的映射关系",notes = "修改权限与组的映射关系",httpMethod = "POST")
	@PostMapping(value = "/update")
	public ResponseEntity update(@RequestBody@Valid RolePowerRefUpdateForm form) throws bmpowerException {
		RolePowerRefPo po = CopyUtil.transfer(form, RolePowerRefPo.class);
		rolePowerRefService.update(po);
		return getSuccessResult();
	}

	@ApiOperation(value = "（单个）删除权限与组的映射关系",notes = "删除权限与组的映射关系",httpMethod = "POST")
	@PostMapping(value = "/delete")
	public ResponseEntity delete(@RequestBody@Valid RolePowerRefDeleteForm form) throws bmpowerException {
		rolePowerRefService.delete(form.getPowerId());
		return getSuccessResult();
	}

}