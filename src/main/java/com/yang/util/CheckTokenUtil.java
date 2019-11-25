package com.yang.util;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 第一次请求的时候在session中放入UUID，并把此UUID带到下一个页面，然后把此UUID带回来与session中进行比较
 * 注意多台服务器下session共享问题
 * 
 * @date 2018年4月28日 下午2:27:19
 * @author tonasun
 */
public class CheckTokenUtil {
    private static Logger logger = LoggerFactory.getLogger(CheckTokenUtil.class);

    /**
     * 在session中生成key为sessionAttributeKey，value为UUID的Token(防止重复提交重复访问)
     */
    public static void createToken(HttpServletRequest request, String sessionAttributeKey) {
        logger.info("createToken is invoke");
        HttpSession session = request.getSession(true);
        String uuid = UUID.randomUUID().toString();
        logger.info("createToken session:" + session.getId() + ",uuid:" + uuid);
        session.setAttribute(sessionAttributeKey, uuid);
    }

    /**
     * 取出session中的sessionAttributeKey值与request中的requestParameterName值比较，其中有空值或者不同返回false表示未通过check
     */
    public static boolean checkToken(HttpServletRequest request, String sessionAttributeKey, String requestParameterName) {
        try {
            logger.info("checkToken is invoke");
            String requestToken = request.getParameter(requestParameterName);
            HttpSession session = request.getSession();
            String sessionToken = (String) session.getAttribute(sessionAttributeKey);
            logger.info("1-checkToken sessionId:" + session.getId());
            logger.info("requestToken=" + requestToken + ",sessionToken=" + sessionToken);
            if (StringUtils.isEmpty(requestToken)) {
                return false;
            }
            if (StringUtils.isEmpty(sessionToken)) {
                return false;
            }
            if (!requestToken.equals(sessionToken)) {
                return false;
            }
            session.removeAttribute(sessionAttributeKey);
            logger.info("2-checkToken sessionId:" + session.getId() + ",session token:" + session.getAttribute(sessionAttributeKey));
            return true;
        } catch (Exception e) {
            logger.info("checkToken exception:" + e);
        }
        return false;
    }
}
