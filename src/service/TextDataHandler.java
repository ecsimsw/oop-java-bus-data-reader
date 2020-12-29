package service;

import dto.History;
import repository.HistoryRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TextDataHandler {
    private static final int PID_INDEX = 1;
    private static final int DATE_INDEX = 9;
    private static final String SEPARATOR = "\t";
    private static final String INVALID_PID_CODE = "-00001";
    private static final String TEST_PID_CODE = "000005";

    private static TextDataHandler textDataHandler;

    private TextDataHandler(){}

    public static TextDataHandler getInstance() {
        if(textDataHandler == null){
            textDataHandler = new TextDataHandler();
        }

        return textDataHandler;
    }

    public void readHistory(String filePath, LocalDate from, LocalDate to, String busName, int price) throws IOException {
        String path = filePath.replaceAll("\\\\", "\\\\\\\\");
        File file = new File(path);
        try {

            if (file.exists()) {
                System.out.println(path);
                BufferedReader inFile = new BufferedReader(new FileReader(file));

                String line = null;
                inFile.readLine();
                while ((line = inFile.readLine()) != null) {
                    String[] splited = line.split(SEPARATOR);
                    String pid = String.format("%06d", Integer.parseInt(splited[PID_INDEX]));

                    LocalDateTime dateTime;
                    try{
                        dateTime = LocalDateTime.parse(splited[DATE_INDEX],
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    }
                    catch (Exception e){
                        dateTime = LocalDateTime.parse(splited[DATE_INDEX],
                                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                    }

                    if (isInvalidPID(pid)) {
                        continue;
                    }

                    if (isNotInRange(from, to, dateTime)) {
                        continue;
                    }

                    saveInRepository(new History(pid, dateTime, busName, price));
                }
            }
        }catch (Exception e){
            System.out.print(e.getMessage());
        }
    }

    private boolean isInvalidPID(String pid) {
        return pid.equals(INVALID_PID_CODE) || pid.equals(TEST_PID_CODE);
    }

    private boolean isNotInRange(LocalDate from, LocalDate to, LocalDateTime dateTime) {
        return dateTime.isBefore(from.atTime(0, 0)) || dateTime.isAfter(to.atTime(23, 59));
    }

    private void saveInRepository(History history) {
        HistoryRepository.addHistory(history);
    }
}
