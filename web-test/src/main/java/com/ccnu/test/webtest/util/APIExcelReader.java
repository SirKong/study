package com.ccnu.test.webtest.util;

import com.ccnu.test.webtest.model.APIDescriptor;
import com.ccnu.test.webtest.model.APIField;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class APIExcelReader {
	private static Log log = LogFactory.getLog(APIExcelReader.class);

	public static List<APIDescriptor> readAPIDescriptors(InputStream is) throws Exception {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		List<APIDescriptor> apiDescriptors = new ArrayList<APIDescriptor>();
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			List<APIField> apiFields = new ArrayList<APIField>();
			APIDescriptor apiDescriptor = new APIDescriptor(apiFields);
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (null == hssfSheet) {
				continue;
			}
			log.debug(String.format("开始解析[%s]sheet页的内容", hssfSheet.getSheetName()));
			String functionNo = hssfSheet.getRow(1).getCell(2).getStringCellValue();
			String functionDesc = hssfSheet.getRow(2).getCell(2).getStringCellValue();
			String createDate = new DecimalFormat("#").format(hssfSheet.getRow(1).getCell(8).getNumericCellValue());
			boolean isContainList = "是".equals(hssfSheet.getRow(1).getCell(6).getStringCellValue());

			boolean hasPrivateInRegion = false;

			// 先获取“输入参数”-->“私有参数”所在合并单元格，以便解析入参字段
			CellRangeAddress inRegion = getCellRangeAddressByCellValue(hssfSheet, "输入参数");
			// 确保至少有一个入参字段
			if (null != inRegion && (inRegion.getLastRow() - inRegion.getFirstRow() > 0)) {
				// 至少有2个输入参数,表明有[私有参数]的入参合并单元格
				if (inRegion.getLastRow() - inRegion.getFirstRow() > 1) {
					hasPrivateInRegion = true;
				}
				for (int i = (inRegion.getFirstRow() + 1); i <= inRegion.getLastRow(); i++) {
					APIField field = new APIField();
					field.setFieldname(hssfSheet.getRow(i).getCell(3).getStringCellValue());
					field.setFieldtype(hssfSheet.getRow(i).getCell(5).getStringCellValue());
					field.setFielddesc(hssfSheet.getRow(i).getCell(6).getStringCellValue());
					field.setRequired("N".equals((hssfSheet.getRow(i).getCell(7).getStringCellValue())) ? false : true);
					field.setComment(hssfSheet.getRow(i).getCell(8).getStringCellValue());
					field.setFielddirection(APIField.FIELD_DIRECTION_IN);
					apiFields.add(field);
					log.debug("入参私有字段-->" + field);
				}
			}
			// 获取“输出参数”-->“公共参数”所在的合并单元格，以便解析公共的出参
			CellRangeAddress publicOutRegion = getCellRangeAddressByCellValue(hssfSheet, "公共参数");
			if (null != publicOutRegion && (publicOutRegion.getLastRow() - publicOutRegion.getFirstRow() > 0)) {
				for (int i = publicOutRegion.getFirstRow(); i <= publicOutRegion.getLastRow(); i++) {
					APIField field = new APIField();
					field.setFieldname(hssfSheet.getRow(i).getCell(3).getStringCellValue());
					field.setFieldtype(hssfSheet.getRow(i).getCell(5).getStringCellValue());
					field.setFielddesc(hssfSheet.getRow(i).getCell(6).getStringCellValue());
					field.setRequired("N".equals((hssfSheet.getRow(i).getCell(7).getStringCellValue())) ? false : true);
					field.setComment(hssfSheet.getRow(i).getCell(8).getStringCellValue());
					field.setFielddirection(APIField.FIELD_DIRECTION_OUT);
					field.setPublicfield(true);
					apiFields.add(field);
					log.debug("出参公共字段-->" + field);

				}
			}
			// 获取“输出参数”-->“私有参数”所在的合并单元格，以便解析私有的出参
			List<CellRangeAddress> privateRegions = getCellRangeAddressesByCellValue(hssfSheet, "私有参数");
			CellRangeAddress privateOutRegin = null;
			if (null != privateRegions && privateRegions.size() == 1) {
				// 通过[私有参数]获取到只有一个合并单元格区域，且没有入参的情况下，表明这个合并单元格对应的是出参中的私有字段
				if (!hasPrivateInRegion) {
					privateOutRegin = privateRegions.get(0);
				}
			} else if (null != privateRegions && privateRegions.size() > 1) {
				if (privateRegions.get(0).getFirstRow() > privateRegions.get(1).getFirstRow()) {
					privateOutRegin = privateRegions.get(0);
				} else {
					privateOutRegin = privateRegions.get(1);
				}
			}
			if (null != privateOutRegin) {
				for (int i = privateOutRegin.getFirstRow(); i <= privateOutRegin.getLastRow(); i++) {
					APIField field = new APIField();
					if (isContainList) {
						if (!"results".equals(hssfSheet.getRow(i).getCell(3).getStringCellValue())
								&& !"".equals(hssfSheet.getRow(i).getCell(3).getStringCellValue())) {
							field.setFieldname(hssfSheet.getRow(i).getCell(3).getStringCellValue());
							field.setListfield(false);
						} else {
							field.setFieldname(hssfSheet.getRow(i).getCell(4).getStringCellValue());
							field.setListfield(true);
						}
					} else {
						field.setFieldname(hssfSheet.getRow(i).getCell(3).getStringCellValue());
					}
					field.setFieldtype(hssfSheet.getRow(i).getCell(5).getStringCellValue());
					field.setFielddesc(hssfSheet.getRow(i).getCell(6).getStringCellValue());
					field.setRequired("N".equals((hssfSheet.getRow(i).getCell(7).getStringCellValue())) ? false : true);
					field.setComment(hssfSheet.getRow(i).getCell(8).getStringCellValue());
					field.setFielddirection(APIField.FIELD_DIRECTION_OUT);
					field.setPublicfield(false);
					apiFields.add(field);
					log.debug("出参私有字段-->" + field);
				}
			}
			apiDescriptor.setFunctionno(functionNo);
			apiDescriptor.setFunctiondesc(functionDesc);
			apiDescriptor.setCreatedate(createDate);
			apiDescriptors.add(apiDescriptor);
			log.info(String.format("结束解析[%s]sheet页的内容，该接口为[%s]", hssfSheet.getSheetName(), apiDescriptor));
		}
		hssfWorkbook.close();

		Collections.sort(apiDescriptors, new Comparator<APIDescriptor>() {
			public int compare(APIDescriptor arg0, APIDescriptor arg1) {
				return arg0.getFunctionno().compareTo(arg1.getFunctionno());
			}
		});

		return apiDescriptors;
	}

	public static List<APIDescriptor> readAPIDescriptors(String filePath) throws Exception {
		InputStream is = ClassLoader.getSystemResourceAsStream(filePath);
		return readAPIDescriptors(is);
	}

	/**
	 * 根据指定的单元格内容从sheet中获取该单元格所在的合并单元格
	 * 
	 * @param hssfSheet
	 * @param value
	 * @return
	 */
	private static CellRangeAddress getCellRangeAddressByCellValue(HSSFSheet hssfSheet, String value) {
		List<CellRangeAddress> cellRangeAddresses = hssfSheet.getMergedRegions();
		for (int i = 0; i < cellRangeAddresses.size(); i++) {
			if (value.equals(hssfSheet.getRow(cellRangeAddresses.get(i).getFirstRow())
					.getCell(cellRangeAddresses.get(i).getFirstColumn()).getStringCellValue())) {
				return cellRangeAddresses.get(i);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param hssfSheet
	 * @param value
	 * @return
	 */
	private static List<CellRangeAddress> getCellRangeAddressesByCellValue(HSSFSheet hssfSheet, String value) {
		List<CellRangeAddress> ret = new ArrayList<CellRangeAddress>();
		List<CellRangeAddress> cellRangeAddresses = hssfSheet.getMergedRegions();
		for (int i = 0; i < cellRangeAddresses.size(); i++) {
			if (value.equals(hssfSheet.getRow(cellRangeAddresses.get(i).getFirstRow())
					.getCell(cellRangeAddresses.get(i).getFirstColumn()).getStringCellValue())) {
				ret.add(cellRangeAddresses.get(i));
			}
		}
		return ret;
	}
}
