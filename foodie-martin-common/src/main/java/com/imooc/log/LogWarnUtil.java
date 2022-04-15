package com.imooc.log;

import com.imooc.exception.BizException;
import com.imooc.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

/**
 * @Describe: error 和 warn 级别的 报警 和分析日志
 * error 级别日志经过 ding-log 发送到钉钉报警群
 * warn  级别日志经过ding-log 会进行收集分析
 *
 * @Created by tangfeng 2021-03-15 17:23
 */
@Slf4j
public class LogWarnUtil {

    /**
     * warn 级别的分析日志
     */
    public static void warn(Logger logger, ResultCode resultCode, String message, Object... msgParam) {
        String msg = processMsg(resultCode, message);
        logger.warn(msg, msgParam);
    }

    public static void warnEx(Logger logger,Exception e,  String message, Object... msgParam) {
        if (e instanceof BizException) {
            warn(logger, ((BizException) e).getResultCode(), message, msgParam);
        } else {
            logger.error(message, e.getMessage());
        }
    }

    /**
     * error 级别的报警日志
     */

    public static void error(Logger logger, String message, Object... msgParam) {
        String msg = processMsg(null, message);
        logger.error(msg, msgParam);
    }

    public static void error(Logger logger, ResultCode resultCode, String message, Object... msgParam) {
        String msg = processMsg(resultCode, message);
        logger.error(msg, msgParam);
    }

    private static String processMsg(ResultCode resultCode, String message){
        StringBuffer pre = new StringBuffer();
        if (resultCode != null) {
            pre.append("[ResultCode-").append(resultCode.getCode()).append("-").append(resultCode.getMsg()).append("-ResultCode]-");
        }
        String msg = pre.append(message).toString();
        return msg;
    }
}
