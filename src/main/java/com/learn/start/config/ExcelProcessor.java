package com.learn.start.config;

import com.learn.start.dto.request.CauHoiDTO_Req;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
@Component
public class ExcelProcessor {

    public List<CauHoiDTO_Req> processExcelFile(Path excelFilePath, Integer teacherId, Integer selectedChapterId) throws IOException {
        List<CauHoiDTO_Req> questions = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(excelFilePath.toFile());
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);  // Đọc sheet đầu tiên trong file Excel
            int rowCount = sheet.getPhysicalNumberOfRows();

            // Bắt đầu từ dòng 1 (thường dòng 0 là header)
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);

                if (row == null) continue; // Nếu dòng trống thì bỏ qua

                CauHoiDTO_Req question = new CauHoiDTO_Req();

                // Giả sử các cột trong Excel có thứ tự như sau:
                // Cột 0: De, Cột 1: A, Cột 2: B, Cột 3: C, Cột 4: D, Cột 5: DapAn, ...
                question.setDe(getCellValue(row.getCell(0)));
                question.setA(getCellValue(row.getCell(1)));
                question.setB(getCellValue(row.getCell(2)));
                question.setC(getCellValue(row.getCell(3)));
                question.setD(getCellValue(row.getCell(4)));
                question.setDapAn(getCellValue(row.getCell(5)));
                question.setChiTiet(getCellValue(row.getCell(6)));
                question.setMucDo(getNumericCellValue(row.getCell(7)));
                question.setIdGiaoVien(teacherId);
                question.setIdChuong(selectedChapterId);
                questions.add(question);
            }
        } catch (IOException e) {
            throw new IOException("Lỗi khi đọc file Excel", e);
        }

        return questions;
    }

    // Hàm phụ để lấy giá trị của ô, trả về "" nếu ô là null hoặc không có giá trị
    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        }
        return "";
    }

    // Hàm phụ để lấy giá trị số từ ô, trả về 0 nếu ô không có giá trị hợp lệ
    private int getNumericCellValue(Cell cell) {
        if (cell == null) return 0;
        if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        }
        return 0;
    }
}
