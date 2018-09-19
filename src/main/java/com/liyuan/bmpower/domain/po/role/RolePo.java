package com.liyuan.bmpower.domain.po.role;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class RolePo implements Serializable {

	/**
	 * 主键
	*/
	private Integer id;
	/**
	 * 权限组名
	*/
	private String name;
	/**
	 * 描述
	*/
	private String remark;
	/**
	 * 
	*/
	private Date addTime;
	/**
	 * 
	*/
	private String addUserCode;
	/**
	 * 
	*/
	private Date optTime;
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