package dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class History implements Comparable<History>{
    private String pid;
    private String busName;
    private LocalDateTime localDateTime;
    private int price;

    public History(String pid, LocalDateTime localDateTime, String busName, int price) {
        this.pid = pid;
        this.localDateTime = localDateTime;
        this.busName = busName;
        this.price = price;
    }

    public boolean isUser(User user){
        return user.isPid(this.pid);
    }

    public String getDateTimeString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Configuration.getDateTimeFormat());
        return localDateTime.format(formatter);
    }

    public String getPid(){
        return pid;
    }

    public int getPrice(){
        return price;
    }

    public String getBusName(){
        return busName;
    }

    @Override
    public int compareTo(History o) {
        return this.pid.compareTo(o.pid);
    }

    @Override
    public String toString() {
        return busName + ", " +localDateTime.format(DateTimeFormatter.ofPattern(Configuration.getDateTimeFormat()));
    }
}
