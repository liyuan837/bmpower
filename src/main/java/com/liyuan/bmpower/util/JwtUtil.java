package com.liyuan.bmpower.util;

import com.liyuan.bmpower.constants.User;
import com.liyuan.bmpower.domain.exception.bmpowerException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @ClassName JwtUtil
 * @Description 用户的token生成和解析
 * @Author liyuan
 * @Date 2018/8/25 1:27
 **/
public class JwtUtil {
    /**
     * 基线项目的jwtSecret
     */
    public static String BASE_JWT_SECRET = "08154AA120144103ADC9D9A2D540946C";

    /**
     * 获取验证Claims
     */
    private static Claims getClaims(String token, String jwtSecret) {
        byte[] encodedKey = Base64.decodeBase64(jwtSecret);

        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        //获取claims
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {

        }
        return claims;
    }

    /**
     * 验证登录
     * @param authorization
     * @return
     */
    public static JwtUser checkLogin(String authorization) throws bmpowerException{
        JwtUser jwtUser;

        Claims claims = getClaims(authorization,BASE_JWT_SECRET);
        if (claims == null) {
            throw new bmpowerException(202,"无效token");
        }

        String userCode = (String) claims.get("userCode");
        if (userCode == null || userCode.equals("")) {
            throw new bmpowerException("会话中的用户编号为空");
        }

        Integer userType = claims.get("userType")==null? null : (Integer) claims.get("userType");
        if (userType == null || (userType != User.UserType.SYS_SUPERUSER  && userType != User.UserType.SYS_COMMONUSER &&
                userType !=User.UserType.PROJ_SUPERUSER && userType != User.UserType.PROJ_COMMONUSER)) {
            throw new bmpowerException("用户类型错误");
        }

        Long expiration = (Long) claims.get("expiration");
        if (expiration < (System.currentTimeMillis())) {
            throw new bmpowerException(202,"会话超时，请重新登陆");
        }

        jwtUser = new JwtUser();
        jwtUser.setUserCode(userCode);
        jwtUser.setLoginName((String) claims.get("loginName"));
        jwtUser.setUserType((Integer) claims.get("userType"));
        jwtUser.setProjectId((Integer) claims.get("projectId"));
        jwtUser.setHuserCode((String) claims.get("huserCode"));
        return jwtUser;
    }

    /**
     * 验证微服务登录
     * @param authorization
     * @return
     */
    public static JwtUser checkMicroLogin(String authorization, String jwtSecret) throws bmpowerException {
        JwtUser jwtUser;

        Claims claims = getClaims(authorization, jwtSecret);
        if (claims == null) {
            throw new bmpowerException(202,"无效token");
        }

        String userCode = (String) claims.get("userCode");
        if (userCode == null || userCode.equals("")) {
            throw new bmpowerException("会话中的用户编号为空");
        }

        Integer userType = claims.get("userType")==null? null : (Integer) claims.get("userType");
        if (userType == null || (userType != User.UserType.SYS_SUPERUSER  && userType != User.UserType.SYS_COMMONUSER &&
                userType !=User.UserType.PROJ_SUPERUSER && userType != User.UserType.PROJ_COMMONUSER)) {
            throw new bmpowerException("用户类型错误");
        }

        Long expiration = (Long) claims.get("expiration");
        if (expiration < (System.currentTimeMillis())) {
            throw new bmpowerException(202,"会话超时，请重新登陆");
        }

        jwtUser = new JwtUser();
        jwtUser.setUserCode(userCode);
        jwtUser.setLoginName((String) claims.get("loginName"));
        jwtUser.setUserType((Integer) claims.get("userType"));
        jwtUser.setProjectId((Integer) claims.get("projectId"));
        jwtUser.setHuserCode((String) claims.get("huserCode"));
        return jwtUser;
    }
}
