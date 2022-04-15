package com.imooc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.imooc.exception.BizException;
import com.imooc.mapper.ItemsMapper;
import com.imooc.pojo.Items;
import com.imooc.service.AllKindsOfExamplesService;
import com.imooc.utils.DateUtils;
import com.imooc.utils.ExportExcelUtil;
import com.imooc.utils.QiNiuYunUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AllKindsOfExamplesServiceImp extends ServiceImpl<ItemsMapper, Items> implements AllKindsOfExamplesService {

    public static String fileMiniType_xlsx = ".xlsx";
    public static String fileMiniType_xls = ".xls";
    public static String uploadPre = "kdsp/coupon/upload/";
    public static String downloadPre = "kdsp/coupon/download/";
    public static String downloadPrivateTrafficPre = "kdsp/privatetraffic/download/";

    @Autowired
    private QiNiuYunUtil qiNiuYunUtil;
    /**
     * @param items
     * @Description: 下载文件或者导出到excel
     * @Param:
     * @return:
     * @Author: Martin~
     * @Date: 2022/4/11 11:12
     */
    @Override
    public String itemsListExportExcel(Items items) {
        LambdaQueryWrapper<Items> queryWrapper = Wrappers.lambdaQuery(Items.class)
//                .eq(Items::getEnable, EnableType.VALID.getCode())
                .orderByDesc(Items::getCreatedTime);


        if (items.getItemName() != null && items.getItemName().trim().length() > 0) {
            queryWrapper.like(Items::getItemName, items.getItemName());
        }


        List<String> channelIdList;
        if (StringUtils.isNotBlank(items.getItemsId())) {
            channelIdList = Arrays.asList(items.getItemsId().split(",")).stream().map(e -> e).collect(Collectors.toList());
        } else {
            channelIdList = new ArrayList<>();
        }
        if (CollectionUtils.isNotEmpty(channelIdList)) {
            queryWrapper.in(Items::getItemsId, channelIdList);
        }


        List<Items> itemsList = this.getBaseMapper().selectList(queryWrapper);

        if (CollectionUtils.isEmpty(itemsList)) {
            throw new BizException("无对应的记录，不需要下载");
        }
//        List<Items> ItemsList = page.getRecords();

        //todo 数据量较大，可能会内存溢出，后续优化
        List<Map> mapList = Lists.newArrayList();

        itemsList.forEach(vo -> {
//            vo.setEnableTypeMsg(vo.getEnable().getMsg());
//            vo.setItemTypeName(ItemsType.valueOf(vo.getChannelType()).getDesc());
            vo.setCreatedAtStr(DateUtils.format(vo.getCreatedTime()));
            Map<String, Object> innerMap = JSONObject.parseObject(JSONObject.toJSONString(vo)).getInnerMap();
            mapList.add(innerMap);

        });


//        String title = DateUtils.getNowYmdhms() + "私域渠道管理明细"; //excel第一个sheet的名称
        String title = DateUtils.getNowYmdhms() + "商品信息明细"; //excel第一个sheet的名称
        String[] headers = {"商品ID","商品名称","销售数量", "创建时间"};  //具体列的名字叫啥


        String[] columnList = {"itemsId","itemName","sellCounts","createdAtStr"};

        ByteArrayOutputStream output = null;
        String downloadUrl = null;
        try {
            output = new ByteArrayOutputStream();

            ExportExcelUtil util = new ExportExcelUtil();
            Workbook workbook = new HSSFWorkbook();
            util.exportExcelXlsx2(workbook,title, headers, columnList, mapList, output);

            String fname = downloadPrivateTrafficPre.concat("channel/").concat(title).concat(fileMiniType_xls);
            downloadUrl = qiNiuYunUtil.overrideUploadFileName(output.toByteArray(), fileMiniType_xls, fname);

            return downloadUrl;
        } catch (Exception e) {
            log.error("下载私域流量渠道明细数据异常：请求参数：{},异常：{}", JSONObject.toJSONString(items), e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    log.error("下载私域流量渠道明细数据异常IO：{}", e);
                }
            }
        }
        return downloadUrl;
    }
}
