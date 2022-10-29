//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * JWT工具
 * @author Ming Qiu
 * @date created in 2019/11/11
 *      modifiedBy Ming Qiu
 *         增加departId 到createToken
 */
public class JwtHelper {

    private Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    // 秘钥
    static final String SECRET = "Role-Privilege-Token";
    // 签名是有谁生成
    static final String ISSUSER = "OOAD";
    // 签名的主题
    static final String SUBJECT = "this is a token";
    // 签名的观众
    static final String AUDIENCE = "MINIAPP";
    // Request中的变量名
    public static final String LOGIN_TOKEN_KEY = "authorization";

    @Getter
    @AllArgsConstructor
    @ToString
    public class Token{
        private Long userId;
        private String userName;
        private Long departId;
        private Date expireTime;
        private Integer userLevel;

        public void setDepartId(Long departId){
            this.departId = departId;
        }
    }

    /**
     * 创建用户Token
     * @param userId 用户id
     * @param departId 部门id
     * @return token
     */
    public String createToken(Long userId, String userName,Long departId,Integer userLevel, int expireTime) {
        logger.debug("createToken:");
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            Map<String, Object> map = new HashMap<String, Object>();
            Date nowDate = new Date();
            Date expireDate = getAfterDate(nowDate, 0, 0, 0, 0, 0, expireTime);
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            String tokenId = Common.genSeqNum(1);
            StringBuilder message = new StringBuilder().append("createToken: ").append("userId = ")
                    .append(userId).append(" userName=").append(userName).append(" departId=").append(departId).append(" tokenId:").append(tokenId);
            logger.debug(message.toString());
            String token = JWT.create()
                    // 设置头部信息 Header
                    .withHeader(map)
                    // 设置 载荷 Payload
                    .withClaim("userId", userId)
                    .withClaim("departId", departId)
                    .withClaim("tokenId",tokenId)
                    .withClaim("userName",userName)
                    .withClaim("userLevel",userLevel)
                    .withIssuer(ISSUSER)
                    .withSubject(SUBJECT)
                    .withAudience(AUDIENCE)
                    // 生成签名的时间
                    .withIssuedAt(nowDate)
                    // 签名过期的时间
                    .withExpiresAt(expireDate)
                    // 签名 Signature
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 获得UserId和DepartId
     * @param token
     * @return UserAndDepart
     *    modifiedBy Ming Qiu 2020/11/3 23:09
     */
    public Token verifyTokenAndGetClaims(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUSER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            Claim claimUserId = claims.get("userId");
            Claim claimDepartId = claims.get("departId");
            Claim claimUserName = claims.get("userName");
            Claim claimUserLevel = claims.get("userLevel");
            Claim expireTime=claims.get("exp");
            return new Token(claimUserId.asLong(),claimUserName.asString() ,claimDepartId.asLong(),expireTime.asDate(),claimUserLevel.asInt());
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = new GregorianCalendar();

        cal.setTime(date);
        if (year != 0) {
            cal.add(Calendar.YEAR, year);
        }
        if (month != 0) {
            cal.add(Calendar.MONTH, month);
        }
        if (day != 0) {
            cal.add(Calendar.DATE, day);
        }
        if (hour != 0) {
            cal.add(Calendar.HOUR_OF_DAY, hour);
        }
        if (minute != 0) {
            cal.add(Calendar.MINUTE, minute);
        }
        if (second != 0) {
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }
}
