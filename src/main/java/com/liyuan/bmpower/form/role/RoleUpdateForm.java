package com.liyuan.bmpower.form.role;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;

import com.liyuan.bmpower.form.power.PowerCreateForm;
import com.liyuan.bmpower.form.power.PowerUpdateForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "权限祖")
public class RoleUpdateForm implements Serializable {

	@ApiModelProperty(value = "主键", required = true)
	@NotNull(message = "主键不能为空")
	private Integer id;

	@ApiModelProperty(value = "权限组名")
	private String name;

	@ApiModelProperty(value = "描述")
	private String remark;

	@ApiModelProperty(value = "")
	private String addUserCode;

	@ApiModelProperty(value = "")
	private String optUserCode;

	@ApiModelProperty(value = "所属项目")
	private Integer projectId;

	@ApiModelProperty(value = "")
	private String userCode;

	@ApiModelProperty(value="对应权限列表")
    List<PowerUpdateForm> powerList;
}