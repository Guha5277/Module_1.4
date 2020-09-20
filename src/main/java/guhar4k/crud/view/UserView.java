package guhar4k.crud.view;

import guhar4k.crud.controller.UserController;
import guhar4k.crud.model.Region;
import guhar4k.crud.model.User;

import java.io.PrintStream;
import java.util.Scanner;

public class UserView extends View{
    private final String CMD_USER_FNAME = "fname";
    private final String CMD_USER_LNAME = "lname";
    private final String CMD_USER_REGION = "region";
    private final String CMD_USER_POSTS = "posts";
    private UserController userController;

    public UserView() {
        super(new Scanner(System.in), new PrintStream(System.out), "База данных пользователей");
        userController = new UserController();
        start();
    }

    @Override
    void showHelp() {
        showMsg("Записи выводятся в формате <ID>|<FNAME>|<LNAME>|<POSTS_ID>|<REGION>\n\t" +
                CMD_HELP + " - вывод справки\n\t" +
                CMD_CREATE + " <FIRST_NAME> <LAST_NAME> <REGION_ID> - создание нового пользователя\n\t" +
                CMD_DELETE + " <ID> - удаление пользователя\n\t" +
                CMD_GET_ALL + " - получение всех пользователей\n\t" +
                CMD_GET_BY_ID + " <ID> - получение одного пользователя по ID\n\t" +
                CMD_EDIT_BY_ID + " - редактирование существующего пользователя, осуществляется по следующему шаблону:\n\t\t" +
                CMD_EDIT_BY_ID + " " + CMD_USER_FNAME + " <FIRST_NAME> - изменение имени пользователя\n\t\t" +
                CMD_EDIT_BY_ID + " " + CMD_USER_LNAME + " <LAST_NAME> - изменение фамилии пользователя\n\t\t" +
                CMD_EDIT_BY_ID + " " + CMD_USER_REGION + " <REGION_ID> - изменение страны пользователя(необходимо ввести ID страны)\n\t\t" +
                CMD_EDIT_BY_ID + " " + CMD_USER_POSTS + " <POST_ID1 POST_ID2...> - изменение списка постов пользователя (вводятся ID постов разделенные пробелом)\n\t" +
                CMD_EXIT + " - выход из программы");
    }

    @Override
    void createNewRecord(String[] command) {
        int FNAME_PART = 1;
        int LNAME_PART = 2;
        int REGION_PART = 3;

        if (command.length > 3) {
            try {
                User user = new User(command[FNAME_PART], command[LNAME_PART], new Region(Long.valueOf(command[REGION_PART])));
                userController.save(user);
                showMsg("Запись создана!");
                showAllRecords();
            } catch (NumberFormatException e) {
                showError("Неверные аргументы для комманды " + CMD_CREATE);
            }
        } else {
            showError("Невереное количество аргументов для команды " + CMD_CREATE);
        }
    }
}
