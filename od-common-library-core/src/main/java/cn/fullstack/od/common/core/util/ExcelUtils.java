package cn.fullstack.od.common.core.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.InputStream;

public class ExcelUtils {

    public static void read(InputStream inputStream) {
        ExcelReaderBuilder excelReaderBuilder = EasyExcel.read(inputStream);
        excelReaderBuilder.autoCloseStream(true);
        excelReaderBuilder.excelType(getExcelType(inputStream));
    }

    public static ExcelTypeEnum getExcelType(InputStream inputStream) {
        return null;
    }
}
