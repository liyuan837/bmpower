package com.liyuan.bmpower.form.rolepowerref;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "权限与组的映射关系")
public class RolePowerRefCreateForm implements Serializable {

	@ApiModelProperty(value = "组Id")
	private Integer roleId;

	@ApiModelProperty(value = "")
	private String addUserCode;

}