package com.jason.testSysUser.controller;

import com.jason.common.api.CommonResult;
import com.jason.testSysUser.entity.SysUser;
import com.jason.testSysUser.mapper.SysUserMapper;
import com.jason.testSysUser.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jason
 * @since 2022-09-05
 * @version v1.0
 */
@RestController
@Api(value = "SysUser控制类", tags = {"SysUser控制类"})
@RequestMapping("/testSysUser/api/v1/sys-user")
public class SysUserController {

}
