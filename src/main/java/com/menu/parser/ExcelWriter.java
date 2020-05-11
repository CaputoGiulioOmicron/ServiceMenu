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
	private int rowNum=1;

	private static final String[] columns = { "0", "1", "2", "3", "4", "5", "6", "ServiceId", "NodeName", "NodeType",
			"GroupType", "FlowType", "ResourceId" };

	public ExcelWriter(MenuContent mc) {
		super();
		rowNum=1;
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

		
		rowWriter(mc.getNodes(), sheet);
		
		
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}
		try {
			FileOutputStream fileOut = new FileOutputStream("output/ServiceMenu.xlsx");
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void rowWriter(List<MenuNode> mnl, Sheet sheet) {
		for(int i=0;i<mnl.size();i++) {
			Row row = sheet.createRow(rowNum++);
			MenuNode mn=mnl.get(i);
			row.createCell(0).setCellValue("X");
			if(mn.getNodeType().equals("service")) row.createCell(7).setCellValue(mn.getNodeId());
			row.createCell(8).setCellValue(mn.getNodeName());
			row.createCell(9).setCellValue(mn.getNodeType());
			if(mn.getGroupType()!=null) row.createCell(10).setCellValue(mn.getGroupType());
			if(mn.getFlowType()!=null) row.createCell(11).setCellValue(mn.getFlowType());
			if(mn.getResource()!=null) row.createCell(12).setCellValue(mn.getResource().getId());
			if(mn.getNodes()!=null) {
				rowWriter(mn.getNodes(), sheet, 1);
			}
		}
	}
	private void rowWriter(List<MenuNode> mnl, Sheet sheet, int l) {
		for(int i=0;i<mnl.size();i++) {
			Row row = sheet.createRow(rowNum++);
			MenuNode mn=mnl.get(i);
			row.createCell(l).setCellValue("X");
			if(mn.getNodeType().equals("service")) row.createCell(7).setCellValue(mn.getNodeId());
			row.createCell(8).setCellValue(mn.getNodeName());
			row.createCell(9).setCellValue(mn.getNodeType());
			if(mn.getGroupType()!=null) row.createCell(10).setCellValue(mn.getGroupType());
			if(mn.getFlowType()!=null) row.createCell(11).setCellValue(mn.getFlowType());
			if(mn.getResource()!=null) row.createCell(12).setCellValue(mn.getResource().getId());
			if(mn.getNodes()!=null) {
				rowWriter(mn.getNodes(), sheet, l+1);
			}
		}
	}
}
