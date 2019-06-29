package com.security.demo.utils;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.util.Date;

/**
 * <p>
 * P6spy SQL 打印策略
 * </p>
 *
 * @author hubin
 * @since 2019-02-20
 */
public class P6SpyLogger implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category,
                                String prepared, String sql, String url) {
        return StringUtils.isNotEmpty(sql) ? " Consume Time：" + elapsed + " ms " +
                DateUtil.format(new Date(Long.valueOf(now)), "yyyy-MM-dd HH:mm:ss")
                + "\n Execute SQL：" + sql.replaceAll("[\\s]+", " ") + "\n" : null;
    }
}
