package repository;

import dto.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private UserRepository() {
    }

    private static final Map<String, User> userTable = new HashMap<>();

    public static void addUser(User user) {
        userTable.put(user.getPid(), user);
    }

    public static Map<String, User> getUserTable(){
        return userTable;
    }
}
