package dto;

public class User implements Comparable<User>{

    private String pid;
    private String name;
    private String section;
    private String cardId;
    private int amount;

    public User(String pid, String name, String section, String cardId) {
        this.cardId = cardId;
        this.name = name;
        this.pid = pid;
        this.section = section;
        this.amount = 0;
    }

    public boolean isPid(String pid){
        return this.pid.equals(pid);
    }

    public String getPid(){
        return this.pid;
    }

    public String getName(){
        return this.name;
    }

    public String getSection(){
        return this.section;
    }

    public String getCardId(){
        return this.cardId;
    }

    public int getAmount(){
        return this.amount;
    }

    public void updateAmount(int additionPrice){
        amount += additionPrice;
    }

    @Override
    public int compareTo(User o) {
        return this.pid.compareTo(o.pid);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            return ((User) o).pid == this.pid;
        }
        return false;
    }

    @Override
    public String toString() {
        return pid + ", " + name + ", " + section + ", " + cardId+ ", "+ amount;
    }
}
