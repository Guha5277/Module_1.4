package guhar4k.crud.view;

import guhar4k.crud.controller.UserController;

import java.io.PrintStream;
import java.util.Scanner;

public class UserView extends View{
    private UserController userController;

    UserView() {
        super(new Scanner(System.in), new PrintStream(System.out), "База данных пользователей");
        userController = new UserController();
    }
}
