package com.company.concurrency;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by Justin
 * 2019/5/3  12:09
 */
public class RateLimterUtil {
    RateLimiter limiter = RateLimiter.create(1);
}
