package dto;

public class Result implements Comparable<Result>{
    private User user;
    private History history;

    public Result(User user, History dateTime){
        this.user = user;
        this.history = dateTime;
    }

    public User getUser(){
        return user;
    }

    public String getPid(){
        return user.getPid();
    }

    public String getName(){
        return user.getName();
    }

    public String getSection() {
        return user.getSection();
    }

    public String getCardId(){
        return user.getCardId();
    }

    public int getTotalPrice(){
        return user.getAmount();
    }

    public int getBusPrice(){
        return history.getPrice();
    }

    public String getBusName(){
        return history.getBusName();
    }

    public String getUsageTime(){
        return history.getDateTimeString();
    }

    @Override
    public String toString(){
        return user.toString() + ", " + history.toString();
    }

    @Override
    public int compareTo(Result o) {
        return this.user.compareTo(o.user);
    }
}
