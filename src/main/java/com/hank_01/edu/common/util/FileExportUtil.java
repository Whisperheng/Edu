package com.hank_01.edu.common.util;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: juqi
 * @Date: 2018/1/15
 **/
public class FileExportUtil {
    public static void addFileName(HttpServletResponse response, String fileName){
        if (response==null||StringUtil.isEmpty(fileName)){
            return ;
        }
        // only support excel
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename="+fileName);
    }
}
