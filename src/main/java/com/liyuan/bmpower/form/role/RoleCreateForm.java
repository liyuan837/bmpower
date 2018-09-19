package com.liyuan.bmpower.form.role;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "权限祖")
public class RoleCreateForm implements Serializable {

	@ApiModelProperty(value = "权限组名")
    @NotEmpty(message = "角色名不能为空")
	private String name;

	@ApiModelProperty(value = "描述")
	private String remark;

	@ApiModelProperty(value = "")
	private String addUserCode;

	@ApiModelProperty(value = "")
	private String optUserCode;

	@ApiModelProperty(value = "所属项目")
    @NotNull(message = "所属项目编号不能为空")
	private Integer projectId;

	@ApiModelProperty(value = "")
	private String userCode;

}