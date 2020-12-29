package controller;

import dto.*;
import repository.ResultRepository;
import service.*;

import java.time.LocalDate;
import java.util.List;

public class MainController {

    private static final int[] BUS_PRICES = new int[Configuration.getNumberOfBus()];

    // XXX :: READ FROM VIEW
    private final List<String> historyPaths;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;
    //

    public MainController(List<String> paths, LocalDate dateFrom, LocalDate dateTo) {
        this.historyPaths = paths;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String run() throws Exception {
        readConfigureFile();
        readUserDataFile();
        readBusDataFile();

        Search.search();

        writeResultFile();

        return "검색 완료 : " + getDataCount() + "\n";
    }

    private void readConfigureFile() throws Exception {
        String line = ReadConfigureFile.readFile(Configuration.getConfigureFilePath());
        setPrices(line);
    }

    private void setPrices(String line) {
        String[] prices = line.split(Configuration.getPriceSeparator());
        int numberOfBus = Configuration.getNumberOfBus();
        for (int i = 0; i < numberOfBus; i++) {
            BUS_PRICES[i] = Integer.parseInt(prices[i]);
        }
    }

    private void readUserDataFile() throws Exception {
        try {
            ExcelDataHandler excelDataHandler = ExcelDataHandler.getInstance();
            excelDataHandler.readUserData(Configuration.getUserDataPath());
        } catch (Exception e) {
            throw new Exception("잘못된 사원 정보 파일입니다.");
        }
    }

    private void readBusDataFile() throws Exception {
        TextDataHandler textDataHandler = TextDataHandler.getInstance();
        for (int busIndex = 0; busIndex < historyPaths.size(); busIndex++) {
            textDataHandler.readHistory(
                    historyPaths.get(busIndex),
                    dateFrom,
                    dateTo,
                    Configuration.getBusNames()[busIndex],
                    BUS_PRICES[busIndex]);
        }
    }

    private void writeResultFile() throws Exception {
        ExcelWriter excelWriter = new ExcelWriter(Configuration.getSavePath());
        excelWriter.writeExcelFile();
    }

    private int getDataCount() {
        return ResultRepository.getSize();
    }
}