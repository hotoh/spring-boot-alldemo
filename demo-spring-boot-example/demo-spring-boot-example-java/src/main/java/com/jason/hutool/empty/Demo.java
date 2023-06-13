package com.jason.hutool.empty;

import cn.hutool.core.util.ObjectUtil;

public class Demo {
    public static void main(String[] args) {
        String arg1 = "arg1";
        String arg2 = "null";
//        String arg2 = null;
        String arg3 = "arg3";
        boolean allEmpty = ObjectUtil.isAllNotEmpty(arg1, arg2, arg3);
        // 这样判断简写代码，只要其中一个为空则返回false
        System.out.println(" 是否不存在空：" + allEmpty);

    }
}
