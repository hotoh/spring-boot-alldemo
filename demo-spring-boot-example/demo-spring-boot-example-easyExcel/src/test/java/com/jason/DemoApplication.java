package com.jason;

import com.jason.testSysUser.entity.SysUser;
import com.jason.testSysUser.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author :jason
 * @date : 2022/9/8
 */
@SpringBootApplication
@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
public class DemoApplication {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void testInsert(){

    }
}
