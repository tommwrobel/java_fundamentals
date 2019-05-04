package designpatterns;

import java.util.Enumeration;

public class Decorator {

    public static void main(String[] args) {
        UserService userService1 = new UserServiceImpl();

        UserService userService2 = new UserServiceWithLoggingImpl(userService1);

        userService2.addUser("Kamil");
        userService2.getNumberOfUsers();


    }
}

interface UserService {
    int getNumberOfUsers();

    void addUser(String username);
}

class UserServiceImpl implements UserService {

    @Override
    public int getNumberOfUsers() {
        return 10;
    }

    @Override
    public void addUser(String username) {
    }
}

class UserServiceWithLoggingImpl implements UserService {
    private final UserService userService;

    UserServiceWithLoggingImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public int getNumberOfUsers() {
        int numberOfUsers = userService.getNumberOfUsers();
        System.out.printf("Number of users: %s\n", numberOfUsers);
        return numberOfUsers;
    }

    @Override
    public void addUser(String username) {
        System.out.printf("Adding user %s\n", username);
        userService.addUser(username);
    }
}
