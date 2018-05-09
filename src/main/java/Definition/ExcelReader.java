package Definition;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;

public class ExcelReader {
	public static Map<String, String> inputTestDataLoad() {
		
	

		HashMap<String, String> hm = new HashMap<String, String>();

		//System.out.println(System.getProperty("user.dir") + "\\Input.xlsx");
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Input.xlsx");
			XSSFWorkbook workBook = new XSSFWorkbook(fis);
			XSSFSheet wSheet = workBook.getSheet("Sheet1");
			int rowCount = wSheet.getLastRowNum();
			for (int i = 0; i <=rowCount; i++) {
				XSSFRow row = wSheet.getRow(i);
				String key = "";
				String value = "";
				for (int j = 0; j < 2; j++) {
					XSSFCell cell = row.getCell(j);
					if (j == 0) {
						key = cell.getStringCellValue();
					}
					if (j == 1) {
						value = cell.getStringCellValue();
						hm.put(key, value);
					}

				}

			}
	
			
		

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hm;

	}
	// File f = new File(System.getProperty("user.dir")+"//input.xlsx");

}