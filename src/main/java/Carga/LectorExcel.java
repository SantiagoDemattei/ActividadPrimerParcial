package Carga;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LectorExcel {

    public static String findRows(String ruta, String codigoBuscado) throws Exception {
        FileInputStream inputStream = new FileInputStream(new File(ruta));//Crea un objeto FileInputStream para leer el archivo
        Workbook workbook = new XSSFWorkbook(inputStream);//Crea un objeto Workbook para leer el archivo con formato Excel
        Sheet sheet = workbook.getSheetAt(0);//agarra la primera hoja del archivo
        for (Row row : sheet){
            if(row.getCell(0).getStringCellValue().equals(codigoBuscado))
                return row.getCell(1).getStringCellValue();
        }
        return null;
    }

}
