package com.liyuan.bmpower.vo.rolepowerref;

import java.io.Serializable;
import com.liyuan.bmpower.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "权限与组的映射关系")
public class RolePowerRefVo implements Serializable {

	@ApiModelProperty(value = "权限id", required = true)
	private Integer powerId;

	@ApiModelProperty(value = "组Id")
	private Integer roleId;

	@ApiModelProperty(value = "")
	@JsonFormat(pattern= DateUtil.FORMAT,timezone="GMT+8")
	private Date addTime;

	@ApiModelProperty(value = "")
	private String addUserCode;

}