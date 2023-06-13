package com.jason.myException.file;


import com.jason.myException.BaseException;

/**
 * 文件信息异常类
 *
 * @author jason
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
