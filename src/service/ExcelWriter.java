package service;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import dto.Result;
import dto.User;
import repository.ResultRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExcelWriter {
    private static final String RESULT_FILE_NAME = "yyyy-MM-dd HH-mm-ss";
    private static final String FILE_EXTENSION = ".xlsx";
    private final String basePath;

    public ExcelWriter(String basePath) {
        this.basePath = basePath;
    }

    public String writeExcelFile() throws FileNotFoundException {
        String path = makeFilePath();

        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("data");
        writeResult(sheet);
        try {
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    private String makeFilePath(){
        String dateTimeString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(RESULT_FILE_NAME));
        return basePath +"\\"+ dateTimeString + FILE_EXTENSION;
    }

    private void writeResult(XSSFSheet sheet){
        List<Result> results = ResultRepository.getResults();

        int row = 0;
        int index = 0;
        int total_index = results.size();

        User prevUser = null;

        while (index < total_index) {
            Result result = results.get(index);
            User user = result.getUser();

            XSSFRow curRow = sheet.createRow(row);
            if (prevUser != user) {
                row++;
                curRow = sheet.createRow(row);
                createSummery(curRow, result);
                row++;
                prevUser = user;
            } else {
                createDetail(curRow, result);
                index++;
                row++;
            }
        }
    }

    private void createSummery(XSSFRow curRow, Result result) {
        curRow.createCell(0).setCellValue(result.getPid());
        curRow.createCell(1).setCellValue(result.getName());
        curRow.createCell(2).setCellValue(result.getSection());
        curRow.createCell(3).setCellValue(result.getTotalPrice());
    }

    private void createDetail(XSSFRow curRow, Result result){
        curRow.createCell(0).setCellValue(result.getPid());
        curRow.createCell(1).setCellValue(result.getName());
        curRow.createCell(2).setCellValue(result.getCardId());
        curRow.createCell(3).setCellValue(result.getBusPrice());
        curRow.createCell(4).setCellValue(result.getBusName());
        curRow.createCell(5).setCellValue(result.getUsageTime());
    }
}
