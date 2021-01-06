package service;

import dto.Configuration;
import dto.History;
import repository.HistoryRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TagRecordDataReader {
    private static final int PID_INDEX = 1;
    private static final int DATE_INDEX = 9;
    private static final String SEPARATOR = "\t";
    private static final String INVALID_PID_CODE = "-00001";
    private static final String TEST_PID_CODE = "000005";

    private static TagRecordDataReader tagRecordDataReader;

    private TagRecordDataReader() {
    }

    public static TagRecordDataReader getInstance() {
        if (tagRecordDataReader == null) {
            tagRecordDataReader = new TagRecordDataReader();
        }

        return tagRecordDataReader;
    }

    public void readHistory(String filePath, LocalDate from, LocalDate to, String busName, int price) throws Exception {
        String path = filePath.replaceAll("\\\\", "\\\\\\\\");
        File file = new File(path);
        try {
            BufferedReader inFile = new BufferedReader(new FileReader(file));
            inFile.readLine();  // 첫 줄 제외

            String line;
            while ((line = inFile.readLine()) != null) {
                String[] splited = line.split(SEPARATOR);
                String pid = String.format("%06d", Integer.parseInt(splited[PID_INDEX]));

                LocalDateTime dateTime = parseToLocalDateTime(splited[DATE_INDEX]);

                if (isInvalidPID(pid) || isNotInRange(from, to, dateTime)) {
                    continue;
                }

                saveInRepository(new History(pid, dateTime, busName, price));
            }
        } catch (Exception e) {
            throw new Exception("잘못된 버스 기록 파일입니다.");
        }
    }

    private LocalDateTime parseToLocalDateTime(String stringDateTime){
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(stringDateTime,
                    DateTimeFormatter.ofPattern(Configuration.getDateTimeFormat()));
        } catch (Exception e) {
            dateTime = LocalDateTime.parse(stringDateTime,
                    DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        }
        return dateTime;
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
