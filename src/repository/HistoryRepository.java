package repository;

import dto.History;
import dto.User;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryRepository {

    private HistoryRepository() {
    }

    private static List<History> histories = new LinkedList<>();

    public static void addHistory(History history) {
        histories.add(history);
    }

    public static List<History> getHistories(){
        return histories;
    }

    public static List<History> getAllSameUsers(User user){
        return histories.stream()
                .filter(history -> history.isUser(user))
                .collect(Collectors.toList());
    }

    public static History getHistory(int index){
        return histories.get(index);
    }

    public static void printAllHistory(){
        histories.stream()
                .forEach(history -> {
                    System.out.println(history.getPid());
                    System.out.println(history);
                });
    }
}


