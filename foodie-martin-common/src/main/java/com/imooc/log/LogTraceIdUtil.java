package com.imooc.log;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 * @Describe:
 * @Created
 */
public class LogTraceIdUtil {

    public static String getTraceId(){
        String traceId = MDC.get("X-B3-TraceId");
        String spanId = MDC.get("X-B3-SpanId");
        if (StringUtils.isNotEmpty(traceId) && StringUtils.isNotEmpty(spanId)) {
            StringBuffer sb = new StringBuffer()
                    .append("[").append(traceId).append("-").append(spanId).append("]");
            return sb.toString();
        }
        return "";
    }
}
