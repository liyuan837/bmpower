package com.liyuan.bmpower.form.rolepowerref;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "权限与组的映射关系")
public class RolePowerRefDeleteForm implements Serializable {

	@ApiModelProperty(value = "权限id", required = true)
	@NotNull(message = "权限id不能为空")
	private Integer powerId;

}