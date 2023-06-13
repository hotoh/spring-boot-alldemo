package com.jason.javaDemo.json;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jason.javaDemo.collection.domain.User;

/***
 * @author jason
 * json 测试demo
 */
public class JsonTestDemo {
    public static void main(String[] args) {
        User user = new User();

        user.setAge(1);
        user.setId(1);
        user.setUserName("222");

        JSONObject json = JSONUtil.parseObj(user, false, true);

        System.out.println("json:"+json);
        String s = json.toStringPretty();
        System.out.println("输出s:"+s);
        String id = json.getStr("id");
        System.out.println("id:"+id);


    }
}
