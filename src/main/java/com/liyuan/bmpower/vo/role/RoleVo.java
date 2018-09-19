package com.liyuan.bmpower.vo.role;

import java.io.Serializable;
import com.liyuan.bmpower.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.liyuan.bmpower.util.TreeContainer;
import com.liyuan.bmpower.vo.power.PowerVo;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@ApiModel(description = "权限祖")
public class RoleVo implements Serializable {

	@ApiModelProperty(value = "主键", required = true)
	private Integer id;

	@ApiModelProperty(value = "权限组名")
	private String name;

	@ApiModelProperty(value = "描述")
	private String remark;

	@ApiModelProperty(value = "")
	@JsonFormat(pattern= DateUtil.FORMAT,timezone="GMT+8")
	private Date addTime;

	@ApiModelProperty(value = "")
	private String addUserCode;

	@ApiModelProperty(value = "")
	@JsonFormat(pattern= DateUtil.FORMAT,timezone="GMT+8")
	private Date optTime;

	@ApiModelProperty(value = "")
	private String optUserCode;

	@ApiModelProperty(value = "所属项目")
	private Integer projectId;

	@ApiModelProperty(value = "")
	private String userCode;

    @ApiModelProperty(value = "对应权限列表")
	private List<TreeContainer<PowerVo>> powerList = new ArrayList<>();
}