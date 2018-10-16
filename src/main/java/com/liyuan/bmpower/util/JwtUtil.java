package com.liyuan.bmpower.util;

import com.liyuan.bmpower.constants.User;
import com.liyuan.bmpower.domain.exception.bmpowerException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * @ClassName JwtUtil
 * @Description 用户的token生成和解析
 * @Author liyuan
 * @Date 2018/8/25 1:27
 **/
public class JwtUtil {

    public static String stringKey = "LIYUAN";
    public static byte[] encodedKey = Base64.decodeBase64(stringKey);
    public static SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

    /**
     * 生成token
     *
     * @return
     */
    public static String generateToken(JwtUser jwtUser) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date startTime = new Date(System.currentTimeMillis());
        Date expireTime = new Date(startTime.getTime() + 60 * 1000 * 60 * 24);//设置token失效时间:24小时
        Date outDate = new Date(startTime.getTime() + 60 * 1000 * 60 * 12);//自定义会话超时时间：12小时
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")    //设置header
                .setHeaderParam("alg", "HS256")
                .setIssuedAt(startTime)
                //设置失效时间
                .setExpiration(expireTime)
                //设置会话超时时间
                .claim("expiration", outDate.getTime())
                .claim("loginName", jwtUser.getLoginName())   //设置payload的键值对
                .claim("userCode", jwtUser.getUserCode())
                .claim("userType",jwtUser.getUserType())
                .claim("projectId",jwtUser.getProjectId())
                .signWith(signatureAlgorithm, key);    //签名，需要算法和key
        String token = builder.compact();
        return token;
    }

    /**
     * 获取验证Claims
     */
    public static Claims getClaims(String token) {
        //获取claims
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)     //此处的key要与之前创建的key一致
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {

        }
        return claims;
    }

    /**
     * 验证登录
     *
     * @param authorization
     * @return
     */
    public static JwtUser checkLogin(String authorization) throws bmpowerException {
        JwtUser jwtUser;

        Claims claims = getClaims(authorization);
        if (claims == null) {
            throw new bmpowerException("无效token");
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
            throw new bmpowerException("会话超时，请重新登陆");
        }

        jwtUser = new JwtUser();
        jwtUser.setUserCode(userCode);
        jwtUser.setLoginName((String) claims.get("loginName"));
        jwtUser.setUserType((Integer) claims.get("userType"));
        jwtUser.setProjectId((Integer) claims.get("projectId"));

        return jwtUser;
    }
}
