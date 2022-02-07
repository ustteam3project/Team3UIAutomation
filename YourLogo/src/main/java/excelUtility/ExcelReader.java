package excelUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public  XSSFWorkbook workbook;
	public  XSSFSheet sheet;

	public ExcelReader() {

		FileInputStream fis;
		String path=System.getProperty("user.dir");

		try {
			fis = new FileInputStream(path+"\\ExcelDataSource\\Data.xlsx");
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public  String[][] getExcelData() {

		sheet = workbook.getSheetAt(0);
		int lastrownumber = sheet.getLastRowNum();
		short lastcolnumber = sheet.getRow(0).getLastCellNum();
		String[][] data = new String[lastrownumber][lastcolnumber];
		for (int i = 1; i <= lastrownumber; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < lastcolnumber; j++) {
				XSSFCell cell = row.getCell(j);
				DataFormatter dtf = new DataFormatter();
				String value = dtf.formatCellValue(cell);
				// System.out.println(value);
				data[i - 1][j] = value;
			}
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}
	
	public  String getSingleExcelData(int sheetnumber, int row, int col){
		
		sheet = workbook.getSheetAt(0);
		String Data=sheet.getRow(row).getCell(col).getStringCellValue();
		
		return Data;
		
		
	}

}
