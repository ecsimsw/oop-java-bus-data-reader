package controller;

import repository.ResultRepository;
import service.*;

import java.time.LocalDate;
import java.util.List;

public class MainController {
    // XXX :: CONSTANT
    private static final String CONFIGURE_FILE_PATH = "./\\요금정보.txt";
    private static final String userDataPath = "./\\버스직원데이터.xls";
    private static final int[] prices = new int[]{500,1500,1500,500};
    private static final String[] busNames = new String[]{"평택", "교대", "사당", "천안"};
    private static final String basePath = "./\\검색자료";
    //

    // XXX :: READ BY VIEW
    private final List<String> historyPaths;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;
    //

    public MainController(List<String> paths, LocalDate dateFrom, LocalDate dateTo) {
        this.historyPaths = paths;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String run() {
        try{
            String line = ReadConfigureFile.readFile(CONFIGURE_FILE_PATH);
            setPrices(line);
        }catch (Exception e){
            return "잘못된 요금 정보 파일입니다.";
        }

        try {
            ExcelDataHandler excelDataHandler = ExcelDataHandler.getInstance();
            excelDataHandler.readUserData(userDataPath);
        } catch (Exception e) {
            return "잘못된 사원 정보 파일입니다.";
        }

        try {
            TextDataHandler textDataHandler = TextDataHandler.getInstance();
            for (int busIndex = 0; busIndex < historyPaths.size(); busIndex++) {
                textDataHandler.readHistory(historyPaths.get(busIndex), dateFrom, dateTo, busNames[busIndex], prices[busIndex]);
            }
        }catch (Exception e){
            return "잘못된 버스 데이터 파일 입력입니다.";
        }

        Search.search();

        try{
            ExcelWriter excelWriter = new ExcelWriter(basePath);
            excelWriter.writeExcelFile();
        }catch (Exception e){
            return "잘못된 결과 목록 엑셀 파일 생성 위치입니다.";
        }

        return "검색 완료 : "+getDataCount()+"\n";
    }

    public int getDataCount(){
        return ResultRepository.getSize();
    }

    public void setPrices(String line){
        String[] strings = line.split(",");
        prices[0] = Integer.parseInt(strings[0]);
        prices[1] = Integer.parseInt(strings[1]);
        prices[2] = Integer.parseInt(strings[2]);
        prices[3] = Integer.parseInt(strings[3]);
    }
}