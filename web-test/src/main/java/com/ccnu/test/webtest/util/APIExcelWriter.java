package com.ccnu.test.webtest.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.util.CollectionUtils;

import com.ccnu.test.webtest.model.APIDescriptor;
import com.ccnu.test.webtest.model.APIField;

/**
 * Created by gongyb08837 on 2016/1/23.
 */
public class APIExcelWriter {
    private static final Log log = LogFactory.getLog(APIExcelWriter.class);

    @SuppressWarnings("deprecation")
    public static void write(List<APIDescriptor> apiDescriptorList, String path, String filename) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet0 = workbook.createSheet("接口列表");

        // 右对齐 Courier New字体 黄色背景 样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(false);// 指定单元格自动换行
        cellStyle.setFillForegroundColor(new HSSFColor.LIGHT_YELLOW().getIndex());
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        // 设置单元格字体
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font.setFontName("Courier New");
        cellStyle.setFont(font);

        // 左对齐 Courier New 字体 样式
        HSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 指定单元格居中对齐
        cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle1.setWrapText(false);// 指定单元格自动换行

        // 设置单元格字体
        HSSFFont font1 = workbook.createFont();
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font1.setFontName("Courier New");
        cellStyle1.setFont(font1);

        // 左对齐 Courier New 字体 黄色背景 样式
        HSSFCellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 指定单元格居中对齐
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle2.setWrapText(false);// 指定单元格自动换行
        cellStyle2.setFillForegroundColor(new HSSFColor.LIGHT_YELLOW().getIndex());
        cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        // 设置单元格字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font2.setFontName("Courier New");
        cellStyle2.setFont(font2);

        for (APIDescriptor apiDescriptor : apiDescriptorList) {
            log.debug(String.format("开始写入[%s][%s]接口[%s]", apiDescriptor.getFunctionno(),
                    apiDescriptor.getFunctiondesc(), apiDescriptor));
            // 私有出参字段（结果集字段）
            List<APIField> privateOutListFields = getPrivateOutListFields(apiDescriptor.getApifields());
            // 入参字段
            List<APIField> inFields = getInFields(apiDescriptor.getApifields());
            // 公共出参字段
            List<APIField> publicOutFields = getPublicOutFields(apiDescriptor.getApifields());
            // 私有出参字段（非结果集字段）
            List<APIField> privateOutFields = getPrivateOutFields(apiDescriptor.getApifields());
            int inFieldSize = (CollectionUtils.isEmpty(inFields) ? 0 : inFields.size());
            int privateOutListFieldSize = (CollectionUtils.isEmpty(privateOutListFields) ? 0 : privateOutListFields
                    .size());
            int publicOutFieldSize = (CollectionUtils.isEmpty(publicOutFields) ? 0 : publicOutFields.size());
            int privateOutFieldSize = (CollectionUtils.isEmpty(privateOutFields) ? 0 : privateOutFields.size());

            HSSFSheet sheet = workbook.createSheet(apiDescriptor.getFunctionno() + apiDescriptor.getFunctiondesc());
            sheet.setColumnWidth(5, 15 * 256);
            sheet.setColumnWidth(6, 20 * 256);
            sheet.setColumnWidth(7, 15 * 256);
            sheet.setColumnWidth(8, 50 * 256);
            HSSFRow row = sheet.createRow(1);
            HSSFCell cell_11 = row.createCell(1);
            cell_11.setCellValue("编号/名称");
            cell_11.setCellStyle(cellStyle);
            HSSFCell cell_12 = row.createCell(2);
            cell_12.setCellValue(apiDescriptor.getFunctionno() + "(" + apiDescriptor.getFunctiondesc() + ")");
            cell_12.setCellStyle(cellStyle1);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 4));

            HSSFCell cell_13 = row.createCell(5);
            cell_13.setCellValue("是否返回结果集");
            cell_13.setCellStyle(cellStyle);

            HSSFCell cell_14 = row.createCell(6);
            cell_14.setCellValue(CollectionUtils.isEmpty(privateOutListFields) ? "否" : "是");
            cell_14.setCellStyle(cellStyle1);

            HSSFCell cell_15 = row.createCell(7);
            cell_15.setCellValue("版本");
            cell_15.setCellStyle(cellStyle);

            HSSFCell cell_16 = row.createCell(8);
            cell_16.setCellValue(apiDescriptor.getVersion());
            cell_16.setCellStyle(cellStyle1);

            HSSFRow row2 = sheet.createRow(2);
            HSSFCell cell_21 = row2.createCell(1);
            cell_21.setCellValue("API入口");
            cell_21.setCellStyle(cellStyle);
            HSSFCell cell_22 = row2.createCell(2);
            cell_22.setCellValue(apiDescriptor.getClassname());
            cell_22.setCellStyle(cellStyle1);
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 4));

            HSSFCell cell_23 = row2.createCell(5);
            cell_23.setCellValue("创建人");
            cell_23.setCellStyle(cellStyle);

            HSSFCell cell_24 = row2.createCell(6);
            cell_24.setCellValue(apiDescriptor.getCreateuser());
            cell_24.setCellStyle(cellStyle1);

            HSSFCell cell_25 = row2.createCell(7);
            cell_25.setCellValue("接口创建日期");
            cell_25.setCellStyle(cellStyle);

            HSSFCell cell_26 = row2.createCell(8);
            cell_26.setCellValue(apiDescriptor.getCreatedate());
            cell_26.setCellStyle(cellStyle1);

            HSSFRow row3 = sheet.createRow(3);
            HSSFCell cell_31 = row3.createCell(1);
            cell_31.setCellValue("功能URL");
            cell_31.setCellStyle(cellStyle);
            HSSFCell cell_32 = row3.createCell(2);
            cell_32.setCellValue(apiDescriptor.getUrl());
            cell_32.setCellStyle(cellStyle1);
            sheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 4));

            HSSFCell cell_33 = row3.createCell(5);
            cell_33.setCellValue("最后修改人");
            cell_33.setCellStyle(cellStyle);

            HSSFCell cell_34 = row3.createCell(6);
            cell_34.setCellValue(apiDescriptor.getLastupdateuser());
            cell_34.setCellStyle(cellStyle1);

            HSSFCell cell_35 = row3.createCell(7);
            cell_35.setCellValue("最后更新日期");
            cell_35.setCellStyle(cellStyle);

            HSSFCell cell_36 = row3.createCell(8);
            cell_36.setCellValue(apiDescriptor.getLastupdatedate());
            cell_36.setCellStyle(cellStyle1);

            HSSFRow row4 = sheet.createRow(4);
            HSSFCell cell_41 = row4.createCell(1);
            cell_41.setCellValue("输入参数");
            cell_41.setCellStyle(cellStyle2);
            HSSFCell cell_42 = row4.createCell(2);
            cell_42.setCellValue("参数名");
            cell_42.setCellStyle(cellStyle2);
            sheet.addMergedRegion(new CellRangeAddress(4, 4, 2, 4));
            HSSFCell cell_43 = row4.createCell(5);
            cell_43.setCellValue("类型");
            cell_43.setCellStyle(cellStyle2);
            HSSFCell cell_44 = row4.createCell(6);
            cell_44.setCellValue("说明");
            cell_44.setCellStyle(cellStyle2);
            HSSFCell cell_45 = row4.createCell(7);
            cell_45.setCellValue("是否必填");
            cell_45.setCellStyle(cellStyle2);
            HSSFCell cell_46 = row4.createCell(8);
            cell_46.setCellValue("说明/缺省值");
            cell_46.setCellStyle(cellStyle2);

            if (inFieldSize > 0) {
                sheet.addMergedRegion(new CellRangeAddress(4, 4 + inFieldSize, 1, 1));
                for (int i = 0; i < inFieldSize; i++) {
                    APIField field = inFields.get(i);
                    HSSFRow row5 = sheet.createRow(5 + i);
                    HSSFCell cell_51 = row5.createCell(3);
                    cell_51.setCellValue(field.getFieldname());
                    cell_51.setCellStyle(cellStyle1);
                    sheet.addMergedRegion(new CellRangeAddress(5 + i, 5 + i, 3, 4));

                    HSSFCell cell_52 = row5.createCell(5);
                    cell_52.setCellValue(field.getFieldtype());
                    cell_52.setCellStyle(cellStyle1);

                    HSSFCell cell_53 = row5.createCell(6);
                    cell_53.setCellValue(field.getFielddesc());
                    cell_53.setCellStyle(cellStyle1);

                    HSSFCell cell_54 = row5.createCell(7);
                    cell_54.setCellValue(field.isRequired() ? "Y" : "N");
                    cell_54.setCellStyle(cellStyle1);

                    HSSFCell cell_55 = row5.createCell(8);
                    cell_55.setCellValue(field.getComment());
                    cell_55.setCellStyle(cellStyle1);
                }
                HSSFRow row6 = sheet.getRow(5);
                HSSFCell cell = row6.createCell(2);
                cell.setCellValue("私有参数");
                cell.setCellStyle(cellStyle2);
                if (inFieldSize > 1) {
                    sheet.addMergedRegion(new CellRangeAddress(5, 4 + inFieldSize, 2, 2));
                }
                sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(4, 4 + inFieldSize, 1, 1));
            }

            HSSFRow row7 = sheet.createRow(5 + inFieldSize);
            HSSFCell cell_71 = row7.createCell(1);
            cell_71.setCellValue("输出参数");
            cell_71.setCellStyle(cellStyle2);
            HSSFCell cell_72 = row7.createCell(2);
            cell_72.setCellValue("参数名");
            cell_72.setCellStyle(cellStyle2);
            sheet.addMergedRegion(new CellRangeAddress(5 + inFieldSize, 5 + inFieldSize, 2, 4));
            HSSFCell cell_73 = row7.createCell(5);
            cell_73.setCellValue("类型");
            cell_73.setCellStyle(cellStyle2);
            HSSFCell cell_74 = row7.createCell(6);
            cell_74.setCellValue("说明");
            cell_74.setCellStyle(cellStyle2);
            HSSFCell cell_75 = row7.createCell(7);
            cell_75.setCellValue("是否必填");
            cell_75.setCellStyle(cellStyle2);
            HSSFCell cell_76 = row7.createCell(8);
            cell_76.setCellValue("说明/缺省值");
            cell_76.setCellStyle(cellStyle2);

            for (int i = 0; i < publicOutFieldSize; i++) {
                APIField field = publicOutFields.get(i);
                HSSFRow row8 = sheet.createRow(6 + inFieldSize + i);
                HSSFCell cell_81 = row8.createCell(3);
                cell_81.setCellValue(field.getFieldname());
                cell_81.setCellStyle(cellStyle1);
                sheet.addMergedRegion(new CellRangeAddress(6 + i + inFieldSize, 6 + i + inFieldSize, 3, 4));

                HSSFCell cell_82 = row8.createCell(5);
                cell_82.setCellValue(field.getFieldtype());
                cell_82.setCellStyle(cellStyle1);

                HSSFCell cell_83 = row8.createCell(6);
                cell_83.setCellValue(field.getFielddesc());
                cell_83.setCellStyle(cellStyle1);

                HSSFCell cell_84 = row8.createCell(7);
                cell_84.setCellValue(field.isRequired() ? "Y" : "N");
                cell_84.setCellStyle(cellStyle1);

                HSSFCell cell_85 = row8.createCell(8);
                cell_85.setCellValue(field.getComment());
                cell_85.setCellStyle(cellStyle1);
            }
            HSSFRow row9 = sheet.getRow(6 + inFieldSize);

            HSSFCell cell_92 = row9.createCell(2);
            cell_92.setCellValue("公共参数");
            cell_92.setCellStyle(cellStyle2);
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(6 + inFieldSize, 6 + inFieldSize
                    + publicOutFieldSize - 1, 2, 2));

            if (!CollectionUtils.isEmpty(privateOutFields)) {
                for (int i = 0; i < privateOutFieldSize; i++) {
                    APIField field = privateOutFields.get(i);
                    HSSFRow row10 = sheet.createRow(6 + inFieldSize + publicOutFieldSize + i);
                    HSSFCell cell_101 = row10.createCell(3);
                    cell_101.setCellValue(field.getFieldname());
                    cell_101.setCellStyle(cellStyle1);
                    sheet.addMergedRegion(new CellRangeAddress(6 + inFieldSize + publicOutFieldSize + i, 6
                            + inFieldSize + publicOutFieldSize + i, 3, 4));

                    HSSFCell cell_102 = row10.createCell(5);
                    cell_102.setCellValue(field.getFieldtype());
                    cell_102.setCellStyle(cellStyle1);

                    HSSFCell cell_103 = row10.createCell(6);
                    cell_103.setCellValue(field.getFielddesc());
                    cell_103.setCellStyle(cellStyle1);

                    HSSFCell cell_104 = row10.createCell(7);
                    cell_104.setCellValue(field.isRequired() ? "Y" : "N");
                    cell_104.setCellStyle(cellStyle1);

                    HSSFCell cell_105 = row10.createCell(8);
                    cell_105.setCellValue(field.getComment());
                    cell_105.setCellStyle(cellStyle1);
                }
            }

            if (!CollectionUtils.isEmpty(privateOutListFields)) {
                for (int i = 0; i < privateOutListFieldSize; i++) {
                    APIField field = privateOutListFields.get(i);
                    HSSFRow row11 = sheet.createRow(6 + inFieldSize + privateOutFieldSize + publicOutFieldSize + i);
                    HSSFCell cell_111 = row11.createCell(4);
                    cell_111.setCellValue(field.getFieldname());
                    cell_111.setCellStyle(cellStyle1);

                    HSSFCell cell_112 = row11.createCell(5);
                    cell_112.setCellValue(field.getFieldtype());
                    cell_112.setCellStyle(cellStyle1);

                    HSSFCell cell_113 = row11.createCell(6);
                    cell_113.setCellValue(field.getFielddesc());
                    cell_113.setCellStyle(cellStyle1);

                    HSSFCell cell_114 = row11.createCell(7);
                    cell_114.setCellValue(field.isRequired() ? "Y" : "N");
                    cell_114.setCellStyle(cellStyle1);

                    HSSFCell cell_115 = row11.createCell(8);
                    cell_115.setCellValue(field.getComment());
                    cell_115.setCellStyle(cellStyle1);
                }
                HSSFCell cell_116 = sheet.getRow(6 + inFieldSize + privateOutFieldSize + publicOutFieldSize)
                        .createCell(3);
                cell_116.setCellValue("results");
                cell_116.setCellStyle(cellStyle1);
                sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(6 + inFieldSize + privateOutFieldSize
                        + publicOutFieldSize, 6 + inFieldSize + privateOutFieldSize + publicOutFieldSize - 1
                        + privateOutListFieldSize, 3, 3));
            }

            if (privateOutFieldSize + privateOutListFieldSize > 0) {
                HSSFRow row12 = sheet.getRow(6 + publicOutFieldSize + inFieldSize);
                HSSFCell cell_122 = row12.createCell(2);
                cell_122.setCellValue("私有参数");
                cell_122.setCellStyle(cellStyle2);
                if (privateOutFieldSize + privateOutListFieldSize > 1) {
                    sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(6 + publicOutFieldSize
                            + inFieldSize, 6 + publicOutFieldSize + inFieldSize + privateOutFieldSize
                            + privateOutListFieldSize - 1, 2, 2));
                }
            }
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(5 + inFieldSize, 6 + inFieldSize
                    + publicOutFieldSize + privateOutFieldSize + privateOutListFieldSize - 1, 1, 1));

            int rownum = 6 + inFieldSize + publicOutFieldSize + privateOutFieldSize + privateOutListFieldSize;
            HSSFRow row13 = sheet.createRow(rownum);
            HSSFCell cell_131 = row13.createCell(1);
            cell_131.setCellValue("变更记录");
            cell_131.setCellStyle(cellStyle);

            HSSFCell cell_132 = row13.createCell(2);
            cell_132.setCellValue(apiDescriptor.getUpdatelog());
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(rownum, rownum, 2, 8));

            log.debug(String.format("结束写入[%s][%s]接口[%s]", apiDescriptor.getFunctionno(),
                    apiDescriptor.getFunctiondesc(), apiDescriptor));
        }

        HSSFRow sheet0_row1 = sheet0.createRow(1);
        sheet0.setColumnWidth(2, 40 * 256);
        sheet0.setColumnWidth(3, 40 * 256);
        sheet0.setColumnWidth(4, 40 * 256);
        sheet0.setColumnWidth(5, 80 * 256);

        HSSFCell sheet0_row1_cell1 = sheet0_row1.createCell(1);
        sheet0_row1_cell1.setCellStyle(cellStyle2);
        sheet0_row1_cell1.setCellValue("接口编号");
        HSSFCell sheet0_row1_cell2 = sheet0_row1.createCell(2);
        sheet0_row1_cell2.setCellStyle(cellStyle2);
        sheet0_row1_cell2.setCellValue("接口名称");
        HSSFCell sheet0_row1_cell3 = sheet0_row1.createCell(3);
        sheet0_row1_cell3.setCellStyle(cellStyle2);
        sheet0_row1_cell3.setCellValue("API入口");
        HSSFCell sheet0_row1_cell4 = sheet0_row1.createCell(4);
        sheet0_row1_cell4.setCellStyle(cellStyle2);
        sheet0_row1_cell4.setCellValue("URL");
        HSSFCell sheet0_row1_cell5 = sheet0_row1.createCell(5);
        sheet0_row1_cell5.setCellStyle(cellStyle2);
        sheet0_row1_cell5.setCellValue("说明");

        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle hlink_style = workbook.createCellStyle();
        Font hlink_font = workbook.createFont();
        hlink_font.setUnderline(Font.U_SINGLE);
        hlink_font.setColor(IndexedColors.BLUE.getIndex());
        hlink_style.setFont(hlink_font);


        // 处理第一页 接口列表页
        for (int i = 0; i < apiDescriptorList.size(); i++) {
            APIDescriptor apiDescriptor = apiDescriptorList.get(i);
            HSSFRow sheet0_rowi = sheet0.createRow(2 + i);
            HSSFCell sheet0_rowi_cell1 = sheet0_rowi.createCell(1);
            sheet0_rowi_cell1.setCellStyle(cellStyle1);
            sheet0_rowi_cell1.setCellValue(apiDescriptor.getFunctionno());
            HSSFCell sheet0_rowi_cell2 = sheet0_rowi.createCell(2);
            sheet0_rowi_cell2.setCellStyle(cellStyle1);
            sheet0_rowi_cell2.setCellValue(apiDescriptor.getFunctiondesc());
            Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_DOCUMENT);
            link.setAddress(apiDescriptor.getFunctionno() + apiDescriptor.getFunctiondesc() + "!B2");
            sheet0_rowi_cell2.setHyperlink(link);
            sheet0_rowi_cell2.setCellStyle(hlink_style);
            HSSFCell sheet0_rowi_cell3 = sheet0_rowi.createCell(3);
            sheet0_rowi_cell3.setCellStyle(cellStyle1);
            sheet0_rowi_cell3.setCellValue(apiDescriptor.getClassname());
            HSSFCell sheet0_rowi_cell4 = sheet0_rowi.createCell(4);
            sheet0_rowi_cell4.setCellStyle(cellStyle1);
            sheet0_rowi_cell4.setCellValue(apiDescriptor.getUrl());
            HSSFCell sheet0_rowi_cell5 = sheet0_rowi.createCell(5);
            sheet0_rowi_cell5.setCellStyle(cellStyle1);
            sheet0_rowi_cell5.setCellValue(apiDescriptor.getCreatedate());
        }

        FileOutputStream out = new FileOutputStream(path + File.separator + filename);
        workbook.write(out);
        out.close();
    }

    /**
     * 获取入参字段
     *
     * @param apiFields
     * @return
     */
    private static List<APIField> getInFields(List<APIField> apiFields) {
        List<APIField> apiFieldList = new ArrayList<APIField>();
        for (APIField apiField : apiFields) {
            if (APIField.FIELD_DIRECTION_IN.equals(apiField.getFielddirection())) {
                apiFieldList.add(apiField);
            }
        }
        return apiFieldList;
    }

    /**
     * 获取公共出参字段
     *
     * @param apiFields
     * @return
     */
    private static List<APIField> getPublicOutFields(List<APIField> apiFields) {
        List<APIField> apiFieldList = new ArrayList<APIField>();
        for (APIField apiField : apiFields) {
            if (APIField.FIELD_DIRECTION_OUT.equals(apiField.getFielddirection()) && apiField.isPublicfield()) {
                apiFieldList.add(apiField);
            }
        }
        return apiFieldList;
    }

    /**
     * 获取私有出参字段（非结果集字段）
     *
     * @param apiFields
     * @return
     */
    private static List<APIField> getPrivateOutFields(List<APIField> apiFields) {
        List<APIField> apiFieldList = new ArrayList<APIField>();
        for (APIField apiField : apiFields) {
            if (APIField.FIELD_DIRECTION_OUT.equals(apiField.getFielddirection()) && !apiField.isPublicfield()
                    && !apiField.isListfield()) {
                apiFieldList.add(apiField);
            }
        }
        return apiFieldList;
    }

    /**
     * 获取私有出参字段（结果集字段）
     *
     * @param apiFields
     * @return
     */
    private static List<APIField> getPrivateOutListFields(List<APIField> apiFields) {
        List<APIField> apiFieldList = new ArrayList<APIField>();
        for (APIField apiField : apiFields) {
            if (APIField.FIELD_DIRECTION_OUT.equals(apiField.getFielddirection()) && !apiField.isPublicfield()
                    && apiField.isListfield()) {
                apiFieldList.add(apiField);
            }
        }
        return apiFieldList;
    }
}
