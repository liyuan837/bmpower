package com.liyuan.bmpower.constants;

/**
 * @ClassName User
 * @Description TODO
 * @Author liyuan
 * @Date 2018/8/25 1:33
 **/
public interface User {
    /**
     * 状态，0注销，1正常，-1删除
     */
    interface UserState {
        int NORMAL = 1;
        int LOGOFF = 0;
        int DELETED = -1;
    }

    /**
     * 用户类型，1系统超级管理员，2系统普通管理员
     * 3项目超级管理员，4项目普通管理员
     */
    interface UserType{
        int SYS_SUPERUSER = 1;
        int SYS_COMMONUSER = 2;
        int PROJ_SUPERUSER = 3;
        int PROJ_COMMONUSER = 4;
    }
}
