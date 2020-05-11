package com.menu.parser;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.menu.models.MenuContent;
import com.menu.models.MenuNode;

public class ExcelWriter {
	private int rowNum;
	MenuContent mc;
	String path;

	private int numColumn;

	private String[] columns;

	private static final String[] COLUMNS = { "ServiceId", "NodeName", "NodeType", "GroupType", "FlowType",
			"ResourceId" };

	private static final String SIGN = "X";
	private static final String CONTROL = "service";

	public ExcelWriter(MenuContent mc, String path) {
		super();
		rowNum = 1;
		this.mc = mc;
		this.path = path;
		numColumn = 0;
		countColumn(mc.getNodes(), 0);
		columns = new String[numColumn + COLUMNS.length + 1];
		for (int i = 0; i <= numColumn; i++) {
			columns[i] = "" + i;
		}
		int j=0;
		for (int i = numColumn+1; i < columns.length; i++) {
			columns[i] = COLUMNS[j];
			j++;
		}
	}

	public void parse() {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Menu " + mc.getVersion());
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		rowWriter(mc.getNodes(), sheet, 0);

		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}
		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void rowWriter(List<MenuNode> mnl, Sheet sheet, int l) {
		for (int i = 0; i < mnl.size(); i++) {
			Row row = sheet.createRow(rowNum++);
			MenuNode mn = mnl.get(i);
			row.createCell(l).setCellValue(SIGN);
			if (mn.getNodeType().equals(CONTROL))
				row.createCell(numColumn+1).setCellValue(mn.getNodeId());
			row.createCell(numColumn+2).setCellValue(mn.getNodeName());
			row.createCell(numColumn+3).setCellValue(mn.getNodeType());
			if (mn.getGroupType() != null)
				row.createCell(numColumn+4).setCellValue(mn.getGroupType());
			if (mn.getFlowType() != null)
				row.createCell(numColumn+5).setCellValue(mn.getFlowType());
			if (mn.getResource() != null)
				row.createCell(numColumn+6).setCellValue(mn.getResource().getId());
			if (mn.getNodes() != null) {
				rowWriter(mn.getNodes(), sheet, l + 1);
			}
		}
	}

	private void countColumn(List<MenuNode> mnl, int l) {
		for (int i = 0; i < mnl.size(); i++) {
			MenuNode mn = mnl.get(i);
			if (mn.getNodes() != null) {
				if (l >= numColumn) {
					numColumn = l + 1;
				}
				countColumn(mn.getNodes(), l + 1);
			}
		}
	}

}
