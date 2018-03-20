/*
package com.hank_01.edu.common.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.math.BigDecimal;

*/
/**
 * @Author: juqi
 * @Date: 2018/1/11
 **//*

public class POIExcelUtil {

    private static final Logger LOG = LoggerFactory.getLogger(POIExcelUtil.class);

    public static void exportWorkbookToOutputStream(HSSFWorkbook workbook, OutputStream outputStream){
        try {
            workbook.write(outputStream);
        }catch (Exception e){
            LOG.error("Error to export workbook into output stream, error: "+e.getMessage(), e);
        }finally {
            try {
                if (outputStream!=null) {outputStream.close();}
                if (workbook!=null){workbook.close();}
            }catch (Exception e){}
        }
    }

    public static void merge(HSSFSheet sheet, int startRow, int endRow, int startColumn, int endColumn){
        sheet.addMergedRegion(new CellRangeAddress( startRow, endRow,startColumn, endColumn));
    }

    public static void addCell(HSSFRow row, int columnIndex, HSSFCellStyle style, Object value){
        HSSFCell cell = row.createCell(columnIndex);
        cell.setCellStyle(style);
        cell.setCellValue(value==null?"":value.toString());
    }
    public static void addCell(HSSFRow row, int columnIndex, HSSFCellStyle style, Long value){
        HSSFCell cell = row.createCell(columnIndex);
        cell.setCellStyle(style);
        cell.setCellValue(value);
    }
    public static void addCell(HSSFRow row, int columnIndex, HSSFCellStyle style, BigDecimal value){
        HSSFCell cell = row.createCell(columnIndex);
        cell.setCellStyle(style);
        cell.setCellValue(value.doubleValue());
    }
    public static void addCell(HSSFRow row, int columnIndex, HSSFCellStyle style, Double value){
        HSSFCell cell = row.createCell(columnIndex);
        cell.setCellStyle(style);
        cell.setCellValue(value);
    }


    public static HSSFCellStyle titleStyle(HSSFWorkbook workbook){
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        HSSFFont titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 12);
        titleFont.setBoldweight((short) 500);
        titleStyle.setFont(titleFont);
        return titleStyle;
    }

    public static HSSFCellStyle headerStyle(HSSFWorkbook workbook){
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont headerFont = workbook.createFont();
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerStyle.setFont(headerFont);
        return  headerStyle;
    }

    public static HSSFCellStyle cellStyle(HSSFWorkbook workbook){
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFFont cellFont = workbook.createFont();
        cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        cellStyle.setFont(cellFont);
        return cellStyle;
    }
    private static String[] array = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public static String transColumnToString(int num){
        int count = 26;
        String out = "";
        if(num/count != 0){
            out = array[num/count - 1];
            if(num%count == 0){
                out = out + array[num%count];
            }else{
                out = out + array[num%count - 1];
            }
        }else{
            out = array[num -1 ];
        }
        return out;
    }
}
*/
