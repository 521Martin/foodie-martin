package com.imooc.utils;

import cn.hutool.core.io.IoUtil;
import com.imooc.exception.BizException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    public static class Upload{


        public  static List<List<Cell>> uploadExcel(HttpServletRequest request)  {

            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile("filename");
            if (file.isEmpty()) {
                throw new BizException("文件不能为空");
            }
            InputStream inputStream  = null;
            try{
                inputStream = file.getInputStream();
                return getBankListByExcel(inputStream, file.getOriginalFilename());
            }catch (Exception e) {
                throw new BizException(e);
            }finally {
                IoUtil.close(inputStream);
            }
        }


        /**
         * 处理上传的文件
         *
         * @param in
         * @param fileName
         * @return
         * @throws Exception
         */
       static List<List<Cell>> getBankListByExcel(InputStream in, String fileName) throws Exception {
            List<List<Cell>> list = new ArrayList<>();
            //创建Excel工作薄
            Workbook work = getWorkbook(in, fileName);
            if (null == work) {
                throw new Exception("创建Excel工作薄为空！");
            }
            Sheet sheet = null;
            Row row = null;
            Cell cell = null;

            for (int i = 0; i < work.getNumberOfSheets(); i++) {
                sheet = work.getSheetAt(i);
                if (sheet == null) {
                    continue;
                }

                for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                    row = sheet.getRow(j);
                    if (row == null || row.getFirstCellNum() == j) {
                        continue;
                    }
                    List<Cell> li = new ArrayList<>();
                    for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                        cell = row.getCell(y);
                        li.add(cell);
                    }
                    list.add(li);
                }
            }
            return list;
        }
        /**
         * 判断文件格式
         *
         * @param inStr
         * @param fileName
         * @return
         * @throws Exception
         */
         static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
            Workbook workbook = null;
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            if (".xls".equals(fileType)) {
                workbook = new HSSFWorkbook(inStr);
            } else if (".xlsx".equals(fileType)) {
                workbook = new XSSFWorkbook(inStr);
            } else {
                throw new Exception("请上传excel文件！");
            }
            return workbook;
        }
    }
}
