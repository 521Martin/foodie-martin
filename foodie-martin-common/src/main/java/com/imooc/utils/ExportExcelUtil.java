package com.imooc.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class ExportExcelUtil {
    public ExportExcelUtil() {
    }


    /**
     * XSSFWorkbook:是操作Excel2007的版本，扩展名是.xlsx
     * 这是一个通用的方法，将map数据保存到excel并输出到指定IO设备上
     * <p>
     * title   表格标题名
     * headers 表格属性列名数组
     * columnList 注意此处的columnList 和 headers可以相同，其实是为了从 mapList 取值，作为key参数，
     * mapList 需要传入map集合。
     * 可以参考后面的main方法实际生成一次就理解了
     * out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     */

    public Workbook exportExcelXlsx2(Workbook workbook, String title, String[] headerTextNames, String[] columnRef4MapList, List<Map> mapList, OutputStream out) {
        if (workbook == null) {
            workbook = new HSSFWorkbook();
        }

        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        style.setWrapText(true);//自动换行

        //创建Excel工作表对象
        Sheet sheet = workbook.createSheet(title);
        Row row0 = sheet.createRow(0);
        for (int i = 0; i < headerTextNames.length; i++) {
            //设置行宽
            //sheet.setColumnWidth(i, 30 * 256);

            //设置第一行的 表头 和样式
            Cell cell = row0.createCell(i);
            cell.setCellValue(headerTextNames[i]);
            //cell.setCellStyle(style);
        }

        //遍历设置下一行数据
        int i = 0;
        for (Map map : mapList) {
            Row rowNext = sheet.createRow(i + 1);

            //遍历 填充 每行的 每一个 值
            for (int i1 = 0; i1 < columnRef4MapList.length; i1++) {
                String mapKey = columnRef4MapList[i1];
                Object value = map.get(mapKey);
                String valueStr = value == null ? "" : value.toString();

                Cell cell001 = rowNext.createCell(i1);
                cell001.setCellValue(valueStr);
                //cell001.setCellStyle(style);
            }
            i++;
        }

        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (Exception e) {

            }
        }
        return workbook;
    }

    public void exportExcelXlsx(String title, String[] headers, String[] columnList, List<Map> mapList, OutputStream out) throws Exception {
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();

        /*CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        style.setWrapText(true);//自动换行*/
        //创建Excel工作表对象
        Sheet sheet = workbook.createSheet("Sheet1");
        // 生成一个表格
        //XSSFSheet sheet = workbook.createSheet(URLEncoder.encode(title,"utf-8"));
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();
        // 设置这些样式
//        style.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        // 生成一个字体
        Font font = workbook.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        CellStyle style2 = workbook.createCellStyle();
//        style2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);
        style2.setAlignment(HorizontalAlignment.LEFT);
        style2.setVerticalAlignment(VerticalAlignment.CENTER);
        // 生成另一个字体
        Font font2 = workbook.createFont();
        font2.setBold(true);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        //产生表格标题行
        Row row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //遍历集合数据，产生数据行
        int index = 0;
        for (int i = 0; i < mapList.size(); i++) {
            Map map = mapList.get(i);
            index++;
            row = sheet.createRow(index);
            for (int j = 0; j < columnList.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(style2);
                cell.setCellValue(String.valueOf(map.get(columnList[j])));
            }
        }

        try {
            workbook.write(out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (Exception e) {

            }
        }

    }

    /**
     * HSSFWorkbook:是操作Excel2003以前（包括2003）的版本，扩展名是.xls
     * 这是一个通用的方法，将map数据保存到excel并输出到指定IO设备上
     * <p>
     * title   表格标题名
     * headers 表格属性列名数组
     * columnList 注意此处的columnList 和 headers可以相同，其实是为了从 mapList 取值，作为key参数，
     * mapList 需要传入map集合。
     * 可以参考后面的main方法实际生成一次就理解了
     * out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     */
    public void exportExcel(String title, String[] headers, String[] columnList, List<Map> mapList, OutputStream out) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
//        style.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
//        style2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);
        style2.setAlignment(HorizontalAlignment.LEFT);
        style2.setVerticalAlignment(VerticalAlignment.CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBold(true);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        //产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //遍历集合数据，产生数据行
        int index = 0;
        for (int i = 0; i < mapList.size(); i++) {
            Map map = mapList.get(i);
            index++;
            row = sheet.createRow(index);
            for (int j = 0; j < columnList.length; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(style2);
                cell.setCellValue(String.valueOf(map.get(columnList[j])));
            }
        }

        try {
            workbook.write(out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (Exception e) {

            }

        }
    }


    /*public static void main(String[] args) throws Exception {
        ExportExcelUtil util = new ExportExcelUtil();
        String title = "商品图片"; //excel第一个sheet的名称
        String[] headers = {"a_header", "b_header"};  //具体列的名字叫啥
        String[] columnList = {"a_column", "b_column"};  //对月接下来要循环的数据，他的key是啥
        List<Map> mapList = new ArrayList();    //要实际填充的数据是啥
        Map map = new HashMap();
        map.put("a_column", "a_1");
        map.put("b_column", "b_1");
        mapList.add(map);

        Map map2 = new HashMap();
        map2.put("a_column", "a_2");
        map2.put("b_column", "b_2");
        mapList.add(map2);

        File file = new File("F:\\a.xls");
        FileOutputStream fos = new FileOutputStream(file);
        util.exportExcel(title, headers, columnList, mapList, fos);

    }*/
}
