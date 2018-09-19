package com.liyuan.bmpower.form.role;

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
@ApiModel(description = "权限祖")
public class RoleQueryForm extends BaseQueryForm implements Serializable {

	@ApiModelProperty(value = "主键")
	private Integer id;

	@ApiModelProperty(value = "主键列表")
	private List<Integer> idList;

	@ApiModelProperty(value = "权限组名")
	private String name;

	@ApiModelProperty(value = "描述")
	private String remark;

	@ApiModelProperty(value = "最小,格式为:" + DateUtil.FORMAT)
	@DateTimeFormat(pattern = DateUtil.FORMAT)
	private Date minAddTime;

	@ApiModelProperty(value = "最大,格式为:" + DateUtil.FORMAT)
	@DateTimeFormat(pattern = DateUtil.FORMAT)
	private Date maxAddTime;

	@ApiModelProperty(value = "")
	private String addUserCode;

	@ApiModelProperty(value = "")
	private String optUserCode;

	@ApiModelProperty(value = "所属项目")
	private Integer projectId;

	@ApiModelProperty(value = "")
	private String userCode;

}