package com.liyuan.bmpower.vo.power;

import java.io.Serializable;
import com.liyuan.bmpower.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

import com.liyuan.bmpower.util.Tree;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "细粒度权限")
public class PowerVo implements Serializable ,Tree{

	@ApiModelProperty(value = "主键", required = true)
	private Integer id;

	@ApiModelProperty(value = "权限名称，用于显示")
	private String name;

	@ApiModelProperty(value = "权限别名，英文名")
	private String aliasName;

	@ApiModelProperty(value = "所属父级权限编号")
	private Integer parentId;

	@ApiModelProperty(value = "接口请求路径")
	private String actionUrl;

	@ApiModelProperty(value = "启用状态，1启用，0未启用")
	private Integer state;

	@ApiModelProperty(value = "类型，1菜单目录，2接口功能")
	private Integer type;

	@ApiModelProperty(value = "排序")
	private Integer sortNum;

	@ApiModelProperty(value = "所属项目编号")
	private Integer projectId;

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

	@ApiModelProperty(value = "")
	private String userCode;

}