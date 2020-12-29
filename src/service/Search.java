package service;

import dto.History;
import dto.Result;
import dto.User;
import repository.HistoryRepository;
import repository.ResultRepository;
import repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Search {

    public static void search(){
        List<History> histories = HistoryRepository.getHistories();
        Collections.sort(histories);

        Map<String, User> userTable = UserRepository.getUserTable();
        int size_history = histories.size();

        int index_history = 0;

        while(index_history < size_history){
            String pid = histories.get(index_history).getPid();

            if(userTable.containsKey(pid)){
                User user = userTable.get(pid);
                List<History> sameUsers = HistoryRepository.getAllSameUsers(user);

                saveResult(user, sameUsers);
                index_history += sameUsers.size();
            }

            if(!userTable.containsKey(pid)){
                saveResult(new User(pid, "Non-user", "사원 정보가 없는 사용자 입니다.", "FFFFFF"),HistoryRepository.getHistory(index_history));
                index_history++;
            }
        }
    }

    private static void saveResult(User user,History history){
        ResultRepository.addResult(new Result(user, history));
    }

    private static void saveResult(User user, List<History> historiesSameUser){
        for(History history : historiesSameUser){
            user.updateAmount(history.getPrice());
            ResultRepository.addResult(new Result(user, history));
        }
    }
}
