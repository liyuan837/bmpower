package com.liyuan.bmpower.constants;

/**
 * @Author:LiYuan
 * @description:
 * @Date:Create in 13:02 2018/9/25
 * @Modified By:
 */
public interface Power {

    /**
     * 权限状态，1启用中，0禁用中
     */
    interface PowerState{
        int USED = 1;
        int UNUSED = 0;
    }

    /**
     * 权限类型，0项目类型，1菜单类型，2功能类型
     */
    interface PowerType{
        int PROJECT = 0;
        int MENU = 1;
        int FUNCTION = 2;
    }
}
