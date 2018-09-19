package com.liyuan.bmpower.form.rolepowerref;

import java.io.Serializable;
import java.util.List;
import com.liyuan.bmpower.form.BaseQueryForm;
import com.liyuan.bmpower.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "权限与组的映射关系")
public class RolePowerRefQueryForm extends BaseQueryForm implements Serializable {

	@ApiModelProperty(value = "权限id")
	private Integer powerId;

	@ApiModelProperty(value = "权限id列表")
	private List<Integer> powerIdList;

	@ApiModelProperty(value = "组Id")
	private Integer roleId;

	@ApiModelProperty(value = "最小,格式为:" + DateUtil.FORMAT)
	@DateTimeFormat(pattern = DateUtil.FORMAT)
	private Date minAddTime;

	@ApiModelProperty(value = "最大,格式为:" + DateUtil.FORMAT)
	@DateTimeFormat(pattern = DateUtil.FORMAT)
	private Date maxAddTime;

	@ApiModelProperty(value = "")
	private String addUserCode;

}