package com.liyuan.bmpower.domain.po.power;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class PowerPo implements Serializable {

	/**
	 * 主键
	*/
	private Integer id;
	/**
	 * 权限名称，用于显示
	*/
	private String name;
	/**
	 * 权限别名，英文名
	*/
	private String aliasName;
	/**
	 * 所属父级权限编号
	*/
	private Integer parentId;
	/**
	 * 接口请求路径
	*/
	private String actionUrl;
	/**
	 * 启用状态，1启用，0未启用
	*/
	private Integer state;
	/**
	 * 类型，1菜单目录，2接口功能
	*/
	private Integer type;
	/**
	 * 排序
	*/
	private Integer sortNum;
	/**
	 * 所属项目编号
	*/
	private Integer projectId;
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
	 * 
	*/
	private String userCode;
}