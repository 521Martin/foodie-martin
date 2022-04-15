package com.imooc.web.examples;

import com.google.common.collect.Maps;
import com.imooc.pojo.Items;
import com.imooc.service.AllKindsOfExamplesService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "各种例子", tags = {"各种例子"})
@RequestMapping("/allkindsof")
@RestController
public class AllKindsOfExamplesController extends BaseController {

    @Autowired
    private AllKindsOfExamplesService allKindsOfExamplesService;


    /**
     * @Description: 下载或者导出文件
     * @Param: 
     * @return: 
     * @Author: Martin~
     * @Date: 2022/4/11 11:11
     */ 
    @ApiOperation(value = "列表下载导出", notes = "列表下载导出", httpMethod = "POST")
    @GetMapping("/list/export-excel")
    public IMOOCJSONResult<Map<String, String>> ItemsListExportExcel() {

        Items items = new Items();
        items.setItemsId("bingan-1001,bingan-1002,bingan-1003");
        String downloadUrl = allKindsOfExamplesService.itemsListExportExcel(items);
        if (StringUtils.isNotEmpty(downloadUrl)) {
            Map<String, String> map = Maps.newHashMap();
            map.put("downloadUrl", downloadUrl);

            return IMOOCJSONResult.ok(map);
        }

        return IMOOCJSONResult.errorMsg("下载异常");
    }
}
