/*
package com.hank_01.edu.common.util;

import com.github.pagehelper.Page;
import com.juqitech.zb.common.constants.ZBConstants;

import java.util.ArrayList;
import java.util.List;

public class PageListHelper {

    public static <T, E> List<E> createListAndCopyPageInfos(List<T> page, Integer pageNum, Integer pageSize) {
        if (page==null || !(page instanceof Page)){
            return createEmptyPage(pageNum, pageSize);
        }
        Page<E> newInstance = new Page<>();
        newInstance.setTotal(((Page) page).getTotal());
        newInstance.setPageNum(((Page) page).getPageNum());
        newInstance.setPageSize(((Page) page).getPageSize());
        newInstance.setPages(((Page) page).getPages());
        newInstance.setStartRow(((Page) page).getStartRow());
        newInstance.setEndRow(((Page) page).getEndRow());
        return newInstance;
    }

    public static <T, E> List<E> createListAndCopyPageInfos(List<T> page) {
        return createListAndCopyPageInfos(page, ZBConstants.DEFAULT_PAGE_NUM, ZBConstants.DEFAULT_PAGE_SIZE);
    }

    public static <E> List<E> createEmptyPage(Integer pageNum, Integer pageSize){
        if (pageNum==null||pageSize==null){
            return new ArrayList<>();
        }
        Page<E> instance = new Page<>();
        instance.setTotal(0);
        instance.setPageNum(pageNum);
        instance.setPageSize(pageSize);
        instance.setPages(0);
        instance.setStartRow(0);
        instance.setEndRow(0);
        return instance;
    }
}
*/
