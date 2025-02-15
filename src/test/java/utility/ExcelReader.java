package utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;

public class ExcelReader {

    public static String getSearchTextFromExcel(String filePath, int rowNumber, int columnNumber) throws Exception {
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(rowNumber);
        Cell cell = row.getCell(columnNumber);
        return cell.getStringCellValue();
    }
}
