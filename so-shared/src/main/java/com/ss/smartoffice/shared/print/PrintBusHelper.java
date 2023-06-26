package com.ss.smartoffice.shared.print;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.interceptor.EmployeeSummariesRepo;
import com.ss.smartoffice.shared.model.BankAdviseData;
import com.ss.smartoffice.shared.model.ExportOrg;
import com.ss.smartoffice.shared.model.ExportTestResultView;
import com.ss.smartoffice.shared.model.IncidentApplicant;
import com.ss.smartoffice.shared.model.OrderInTake;
import com.ss.smartoffice.shared.model.Revenue;
import com.ss.smartoffice.shared.model.employee.memployee;

@Component
public class PrintBusHelper {

	@Autowired
	EmployeeSummariesRepo employeeSummariesRepo;

	public String downloadHelper(Model model) {
		Map<String, String> printAttributes = new HashMap<>();
		printAttributes.put("EmpName", "empName");
		printAttributes.put("EmpCode", "empCode");
		model.addAttribute("data", employeeSummariesRepo.findAll());
		model.addAttribute("printAttributes", printAttributes);
		model.addAttribute("fileName", "my-emp");
		return "";
	}

	public static String populateData(String dataType, Object obj, Object objValue) throws Exception {
		switch (dataType) {
		case "ExpenseClaim":
			return null;
		case "memployee":
			return CommonUtils.getValue((memployee) obj, objValue.toString());
		case "BankAdviseData":
			return CommonUtils.getValue((BankAdviseData) obj, objValue.toString());
		case "IncidentApplicant":
			return CommonUtils.getValue((IncidentApplicant) obj, objValue.toString());
		case "ExportOrg":
			return CommonUtils.getValue((ExportOrg) obj, objValue.toString());
		case "ExportTestResultView":
			return CommonUtils.getValue((ExportTestResultView) obj, objValue.toString());
		case "OrderInTake":
			return CommonUtils.getValue((OrderInTake) obj, objValue.toString());
		case "Revenue":
			return CommonUtils.getValue((Revenue) obj, objValue.toString());
		default:
			return "error";
		}
	}

	public static void buildExcelsDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName = (String) model.get("fileName");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xls" + "\"");
		@SuppressWarnings("unchecked")
		List<Object> dataSet = (List<Object>) model.get(model.get("dataKeyName"));
		HashMap<String, String> printAttrbts = (HashMap<String, String>) model.get("printAttributes");
		Sheet sheet = workbook.createSheet(fileName);
		Row header = sheet.createRow(0);
		int cellCount = 0;
		if (dataSet != null) {
			cellCount = 0;
			for (Map.Entry mapElement : printAttrbts.entrySet()) {
				sheet.setDefaultColumnWidth(30);
				// create style for header cells
				CellStyle style = workbook.createCellStyle();
				Font font = workbook.createFont();
				font.setFontName("Arial");
				style.setFillForegroundColor(HSSFColor.BLUE.index);
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				font.setBold(true);
				font.setColor(HSSFColor.WHITE.index);
				style.setFont(font);

				// create header row
				header.createCell(cellCount).setCellValue(mapElement.getKey().toString());
				header.getCell(cellCount).setCellStyle(style);
				cellCount = cellCount + 1;
			}
			int rowCount = 1;
			for (Object obj : dataSet) {
				try {
					cellCount = 0;
					Row userRow = sheet.createRow(rowCount++);
					for (Map.Entry mapElement : printAttrbts.entrySet()) {
						try {
							userRow.createCell(cellCount).setCellValue(
									populateData(model.get("dataType").toString(), obj, mapElement.getValue()));
							cellCount++;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new SmartOfficeException("Data should not be empty");
		}

	}

	public static void printPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String fileName = (String) model.get("fileName");

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".pdf" + "\"");

		@SuppressWarnings("unchecked")
		List<Object> dataSet = (List<Object>) model.get(model.get("dataKeyName"));
		HashMap<String, String> printAttrbts = (HashMap<String, String>) model.get("printAttributes");
		PdfPTable table = new PdfPTable(printAttrbts.size());
		document.add(new Paragraph(fileName));
		if (dataSet != null) {

			table.setWidthPercentage(100.0f);
			table.setSpacingBefore(10);

			// define font for table header row
			com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.TIMES);
			font.setColor(BaseColor.WHITE);

			// define table header cell
			PdfPCell cell = new PdfPCell();
			cell.setBackgroundColor(BaseColor.DARK_GRAY);
			cell.setPadding(5);

			// write table header
			printAttrbts.forEach((k, v) -> {
				cell.setPhrase(new Phrase(k, font));
				table.addCell(cell);

			});

			// write table body
			for (Object emp : dataSet) {
				printAttrbts.forEach((k, v) -> {
					String valueReflection = CommonUtils.getValue(emp, v);

					table.addCell(valueReflection);
				});
			}
			try {
				document.add(table);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void printCsvDocument(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		String fileName = (String) model.get("fileName");

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xls" + "\"");
		@SuppressWarnings("unchecked")
		List<Object> dataSet = (List<Object>) model.get(model.get("dataKeyName"));

		@SuppressWarnings("unchecked")
		LinkedHashMap<String, String> printAttrbts = (LinkedHashMap<String, String>) model.get("printAttributes");
		String[] header = new String[printAttrbts.size()];
		List<String> headerKeyList = new ArrayList<String>();
		List<String> headerValueList = new ArrayList<String>();
		printAttrbts.forEach((k, v) -> {
			headerKeyList.add(k);
			headerValueList.add(v);
		});

		ICsvBeanWriter csvWriter = null;
		try {
			csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			csvWriter.writeHeader(headerKeyList.toArray(new String[0]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Object emp : dataSet) {
			try {
				System.out.println(dataSet);
				populateData(model.get("dataType").toString(), emp, headerValueList.toArray(new String[0]));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			csvWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
