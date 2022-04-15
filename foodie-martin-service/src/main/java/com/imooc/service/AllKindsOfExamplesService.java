package com.imooc.service;

import com.imooc.pojo.Items;

/**
 * @Description: 各种小例子
 * @Param: 
 * @return: 
 * @Author: Martin~
 * @Date: 2022/4/11 11:12
 */ 
public interface AllKindsOfExamplesService {
    /**
     * @Description: 下载文件或者导出到excel
     * @Param: 
     * @return: 
     * @Author: Martin~
     * @Date: 2022/4/11 11:19
     */ 
    String itemsListExportExcel(Items items);
}
