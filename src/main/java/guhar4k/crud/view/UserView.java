package guhar4k.crud.view;

import guhar4k.crud.controller.UserController;

import java.io.PrintStream;
import java.util.Scanner;

public class UserView extends View{
    private UserController userController;

    UserView() {
        super(new Scanner(System.in), new PrintStream(System.out), "База данных пользователей");
        userController = new UserController();
        start();
    }

    @Override
    void showHelp() {
        showMsg("Записи выводятся в формате <ID>|<FNAME>|<LNAME>|<POSTS_ID>|<REGION>\n\t" +
                CMD_HELP + " - вывод справки\n\t" +
                CMD_CREATE + " <FNAME> <LNAME> <REGION> <POSTS_ID> - создание новой записи, POSTS_ID указывается через запятую\n\t" +
                CMD_DELETE + " <ID> - удаление записи\n\t" +
                CMD_GET_ALL + " - получение всех записей\n\t" +
                CMD_GET_BY_ID + " <ID> - получение одной записи по ID\n\t" +
                CMD_EDIT_BY_ID + " <ID> <FNAME> <LNAME> <REGION> <POSTS_ID> - изменение существующей записи\n\t" +
                CMD_EXIT + " - выход из программы");
    }
}
