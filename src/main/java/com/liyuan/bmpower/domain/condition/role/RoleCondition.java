package com.liyuan.bmpower.domain.condition.role;

import java.io.Serializable;
import java.util.List;
import com.liyuan.bmpower.domain.condition.BaseCondition;
import java.util.Date;
import lombok.Data;

@Data
public class RoleCondition extends BaseCondition implements Serializable {

	/**
	 * 主键
	*/
	private Integer id;
	/**
	 * 主键列表
	*/
	private List<Integer> idList;
	/**
	 * 权限组名
	*/
	private String name;
	/**
	 * 描述
	*/
	private String remark;
	/**
	 * 最小
	*/
	private Date minAddTime;
	/**
	 * 最大
	*/
	private Date maxAddTime;
	/**
	 * 
	*/
	private String addUserCode;
	/**
	 * 
	*/
	private String optUserCode;
	/**
	 * 所属项目
	*/
	private Integer projectId;
	/**
	 * 
	*/
	private String userCode;
}