package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();

        userService.saveUser("Milan", "Milanov", (byte)26);
        userService.saveUser("Anton", "Antonov", (byte)47);
        userService.saveUser("Chris", "Chrisov", (byte)15);
        userService.saveUser("Pol", "Polov", (byte)30);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
