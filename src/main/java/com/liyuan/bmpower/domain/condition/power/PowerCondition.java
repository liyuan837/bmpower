package com.liyuan.bmpower.domain.condition.power;

import java.io.Serializable;
import java.util.List;
import com.liyuan.bmpower.domain.condition.BaseCondition;
import java.util.Date;
import lombok.Data;

@Data
public class PowerCondition extends BaseCondition implements Serializable {

	/**
	 * 主键
	*/
	private Integer id;
	/**
	 * 主键列表
	*/
	private List<Integer> idList;
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
	 * 图标存储路径
	 */
	private String iconUrl;
	/**
	 * 图标标志，文件名
	 */
	private String iconKey;
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
	 * 
	*/
	private String userCode;
}