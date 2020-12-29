package service;

import java.io.*;

public class ReadConfigureFile {
    private ReadConfigureFile(){}

    public static String readFile(String path) throws Exception {
        try{
            File file = new File(path);
            FileReader filereader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = bufReader.readLine();
            bufReader.close();
            return line;
        }catch(Exception e){
            throw new Exception("잘못된 요금 정보 파일입니다.");
        }
    }
}
