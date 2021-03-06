package com.liyuan.bmpower.form.power;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(description = "细粒度权限")
public class PowerCreateForm implements Serializable {

	@ApiModelProperty(value = "权限名称，用于显示")
    @NotEmpty(message = "权限名不能为空")
	private String name;

	@ApiModelProperty(value = "权限别名，英文名")
    @NotEmpty(message = "权限别名不能为空")
	private String aliasName;

	@ApiModelProperty(value = "所属父级权限编号")
	private Integer parentId;

	@ApiModelProperty(value = "接口请求路径")
	private String actionUrl;

	@ApiModelProperty(value = "图标存储路径")
	private String iconUrl;

	@ApiModelProperty(value = "图标标志，文件名")
	private String iconKey;

	@ApiModelProperty(value = "启用状态，1启用，0未启用")
	private Integer state;

	@ApiModelProperty(value = "类型，1菜单目录，2接口功能")
	private Integer type;

	@ApiModelProperty(value = "排序")
	private Integer sortNum;

	@ApiModelProperty(value = "所属项目编号")
    @NotNull(message = "项目编号不能为空")
	private Integer projectId;

}
