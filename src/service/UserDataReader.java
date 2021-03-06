package service;

import dto.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import repository.UserRepository;

import java.io.FileInputStream;

public class UserDataReader {
    private static final int PID_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int SECTION_INDEX = 2;
    private static final int CARDID_INDEX = 3;

    private static UserDataReader userDataReader;

    private UserDataReader() {

    }

    public static UserDataReader getInstance() {
        if (userDataReader == null) {
            userDataReader = new UserDataReader();
        }
        return userDataReader;
    }

    public void readUserData(String path) throws Exception {
        FileInputStream file = new FileInputStream(path);
        HSSFWorkbook workbook = new HSSFWorkbook(file);

        HSSFSheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            HSSFRow row = sheet.getRow(rowIndex);
            if (row != null) {
                String pid = String.format("%06d", Integer.parseInt(getCellData(row.getCell(PID_INDEX))));
                String name = getCellData(row.getCell(NAME_INDEX));
                String section = getCellData(row.getCell(SECTION_INDEX));
                String cardId = getCellData(row.getCell(CARDID_INDEX));
                saveInRepository(pid, name, section, cardId);
            }
        }
    }

    private void saveInRepository(String pid, String name, String section, String cardId) {
        UserRepository.addUser(new User(pid, name, section, cardId));
    }

    private String getCellData(HSSFCell cell) {
        CellType cellType = null;
        String value = "";

        cellType = cell.getCellType();

        if (cellType == CellType.FORMULA) {
            value = cell.getCellFormula();
        }

        if (cellType == CellType.NUMERIC) {
            value = ((int) cell.getNumericCellValue()) + "";
        }

        if (cellType == CellType.STRING) {
            value = cell.getStringCellValue() + "";
        }

        if (cellType == CellType.BLANK) {
            value = cell.getBooleanCellValue() + "";
        }

        if (cellType == CellType.ERROR) {
            value = cell.getErrorCellValue() + "";
        }

        return value;
    }
}