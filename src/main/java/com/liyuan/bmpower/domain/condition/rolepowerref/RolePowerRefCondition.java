package com.liyuan.bmpower.domain.condition.rolepowerref;

import java.io.Serializable;
import java.util.List;
import com.liyuan.bmpower.domain.condition.BaseCondition;
import java.util.Date;
import lombok.Data;

@Data
public class RolePowerRefCondition extends BaseCondition implements Serializable {

	/**
	 * 权限id
	*/
	private Integer powerId;
	/**
	 * 权限id列表
	*/
	private List<Integer> powerIdList;
	/**
	 * 组Id
	*/
	private Integer roleId;
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
}