package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    public static Object[][] getExcelData(String filePath, String sheetName) {
        Object[][] data = null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            data = new Object[rowCount - 1][colCount]; // excluding header

            for (int i = 1; i < rowCount; i++) { // start from row 1 (skip header)
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        data[i - 1][j] = "";
                    } else {
                        switch (cell.getCellType()) {
                            case BOOLEAN:
                                data[i - 1][j] = String.valueOf(cell.getBooleanCellValue()); 
                                break;
                            case NUMERIC:
                                // Convert numeric values to String (e.g., "1234")
                                data[i - 1][j] = String.valueOf((int)cell.getNumericCellValue());
                                break;
                            case STRING:
                                data[i - 1][j] = cell.getStringCellValue().trim();
                                break;
                            default:
                                data[i - 1][j] = cell.toString().trim();
                                break;
                        }
                    }
                }
            }
            workbook.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
