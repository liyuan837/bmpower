package com.liyuan.bmpower.domain.po.rolepowerref;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class RolePowerRefPo implements Serializable {

	/**
	 * 权限id
	*/
	private Integer powerId;
	/**
	 * 组Id
	*/
	private Integer roleId;
	/**
	 * 
	*/
	private Date addTime;
	/**
	 * 
	*/
	private String addUserCode;
}