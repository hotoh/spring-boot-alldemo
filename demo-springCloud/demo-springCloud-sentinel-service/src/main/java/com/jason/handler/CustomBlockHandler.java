package com.jason.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jason.domain.CommonResult;

/**
 * Created by jason on 2023/11/7.
 */
public class CustomBlockHandler {

    public static CommonResult handleException(BlockException exception){
        return new CommonResult("自定义限流信息111111111111",200);
    }
}
