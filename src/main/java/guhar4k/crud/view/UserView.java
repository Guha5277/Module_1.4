package guhar4k.crud.view;

import guhar4k.crud.controller.UserController;
import guhar4k.crud.model.Post;
import guhar4k.crud.model.Region;
import guhar4k.crud.model.User;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserView extends View {
    private static View instance;
    private final String CMD_USER_FNAME = "fname";
    private final String CMD_USER_LNAME = "lname";
    private final String CMD_USER_REGION = "region";
    private final String CMD_USER_POSTS = "posts";
    private UserController userController;

    public UserView() {
        super(new Scanner(System.in), new PrintStream(System.out), "База данных пользователей");
        userController = new UserController();
    }

    @Override
    void showHelp() {
        showMsg("Записи выводятся в формате <ID>|<FNAME>|<LNAME>|<REGION>|<POSTS_ID>\n\t" +
                CMD_HELP + " - вывод справки\n\t" +
                CMD_CREATE + " <FIRST_NAME> <LAST_NAME> <REGION_ID> - создание нового пользователя\n\t" +
                CMD_DELETE + " <ID> - удаление пользователя\n\t" +
                CMD_GET_ALL + " - получение всех пользователей\n\t" +
                CMD_GET_BY_ID + " <ID> - получение одного пользователя по ID\n\t" +
                CMD_EDIT_BY_ID + " - редактирование существующего пользователя, осуществляется по следующему шаблону:\n\t\t" +
                CMD_EDIT_BY_ID + " " + CMD_USER_FNAME + " <ID> <FIRST_NAME> - изменение имени пользователя\n\t\t" +
                CMD_EDIT_BY_ID + " " + CMD_USER_LNAME + " <ID> <LAST_NAME> - изменение фамилии пользователя\n\t\t" +
                CMD_EDIT_BY_ID + " " + CMD_USER_REGION + " <ID> <REGION_ID> - изменение страны пользователя(необходимо ввести ID страны)\n\t\t" +
                CMD_EDIT_BY_ID + " " + CMD_USER_POSTS + " <ID> <POST_ID1 POST_ID2...> - изменение списка постов пользователя (вводятся ID постов разделенные пробелом)\n\t" +
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

    @Override
    void showAllRecords() {
        showMsg("Получение всех записей...");
        List<User> users = userController.getAll();
        if (users.size() == 0) {
            showError("Список пользователей пуст");
            return;
        }
        users.forEach(u -> showMsg(u.toString()));
    }

    @Override
    void deleteRecord(String[] command) {
        if (command.length > 1) {
            showMsg("Удаление пользователя...");
            try {
                Long id = Long.valueOf(command[1]);
                userController.deleteById(id);
                showMsg("Запись удалена!");
                showAllRecords();
            } catch (NumberFormatException e) {
                showError("Неверный аргумент для комманды " + CMD_DELETE);
            } catch (NoSuchElementException e) {
                showError("Удаляемый пользователь не найден!");
            }
        } else {
            showError("Отсутствуют аргументы для комманды " + CMD_DELETE);
        }
    }

    @Override
    void getById(String[] command) {
        if (command.length > 1) {
            showMsg("Получение пользователя...");
            try {
                Long id = Long.valueOf(command[1]);
                User user = userController.getById(id);
                showMsg(user.toString());
            } catch (NumberFormatException e) {
                showError("Неверный аргумент для комманды " + CMD_GET_BY_ID);
            } catch (NoSuchElementException e) {
                showError("Пользователь с указанным ID отсутствует!");
            }
        } else {
            showError("Отсутствуют аргументы для комманды " + CMD_GET_BY_ID);
        }
    }

    @Override
    void editRecord(String[] command) {
        if (command.length < 4) {
            showError("Неверное количество аргументов для сомманды: " + CMD_EDIT_BY_ID);
            return;
        }

        long id;
        try {
            int ID_PART = 2;
            id = Long.valueOf(command[ID_PART]);
        } catch (NumberFormatException e) {
            showError("Неверный формат комманды (неверно указан id редактируемого пользователя)!");
            return;
        }

        int SUB_COMMAND = 1;
        int FIRST_ARGUMENT = 3;
        User user;
        try {
            user = userController.getById(id);
        } catch (NoSuchElementException e) {
            showError("Отсутствует пользователь с заданным ID");
            return;
        }

        switch (command[SUB_COMMAND]) {
            case CMD_USER_FNAME:
                user.setFirstName(command[FIRST_ARGUMENT]);
                userController.update(user);
                break;
            case CMD_USER_LNAME:
                user.setLastName(command[FIRST_ARGUMENT]);
                userController.update(user);
                break;
            case CMD_USER_REGION:
                try {
                    user.setRegion(new Region(Long.valueOf(command[FIRST_ARGUMENT])));
                    userController.update(user);
                } catch (NumberFormatException e) {
                    showError("Неверный формат команды (неверно указан id регоина");
                }
                break;
            case CMD_USER_POSTS:
                List<Post> posts = new ArrayList<>();
                try {
                for (int i = 3; i < command.length ; i++) {
                    posts.add(new Post(Long.valueOf(command[i])));
                }
                } catch (NumberFormatException e){
                    showError("Неверный формат комманды (неверно указаны id постов)");
                    return;
                }
                user.setPosts(posts);
                userController.update(user);
                break;
            default:
                showError("Неизвестная суб-комманда комманды " + CMD_EDIT_BY_ID);
        }
        showAllRecords();
    }

    static View getInstance() {
        if (instance == null) instance = new UserView();
        return instance;
    }
}
