package guhar4k.crud.view;

import guhar4k.crud.controller.PostController;
import guhar4k.crud.model.Post;

import java.io.PrintStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PostView extends View {
    PostController postController;

    public PostView() {
        super(new Scanner(System.in), new PrintStream(System.out), "База данных постов");
        postController = new PostController();
        start();
    }

    @Override
    void editRecord(String[] command) {
        int ID = 1;
        int CONTENT = 2;
        if (command.length > 2) {
            showMsg("Изменение записи...");
            try {
                postController.update(new Post(Integer.valueOf(command[ID]), getContentString(CONTENT, command)));
                showMsg("Запись изменена!");
                showAllRecords();
            } catch (NumberFormatException e) {
                showError("Неверный аргумент для комманды " + CMD_EDIT_BY_ID);
            } catch (NoSuchElementException e) {
                showError("Изменяемая запись отсутствует");
            }
        } else {
            showError("Неверное количество аргументов для комманды " + CMD_EDIT_BY_ID);
        }
    }

    @Override
    void deleteRecord(String[] command) {
        if (command.length > 1) {
            showMsg("Удаление записи...");
            try {
                Long id = Long.valueOf(command[1]);
                postController.deleteById(id);
                showMsg("Запись удалена!");
                showAllRecords();
            } catch (NumberFormatException e) {
                showError("Неверный аргумент для комманды " + CMD_DELETE);
            } catch (NoSuchElementException e) {
                showError("Удаляемый элемент не найден!");
            }
        } else {
            showError("Отсутствуют аргументы для комманды " + CMD_DELETE);
        }
    }

    @Override
    void createNewRecord(String[] command) {
        if (command.length > 1) {
            Post post = new Post(getContentString(1, command));
            postController.save(post);
            showMsg("Запись создана!");
            showAllRecords();
        } else {
            showError("Отсутствуют аргументы для комманды " + CMD_CREATE);
        }
    }

    @Override
    void getById(String[] command) {
        if (command.length > 1) {
            showMsg("Получение записи...");
            try {
                Long id = Long.valueOf(command[1]);
                Post post = postController.getById(id);
                showMsg(post.toString());
            } catch (NumberFormatException e) {
                showError("Неверный аргумент для комманды " + CMD_GET_BY_ID);
            } catch (NoSuchElementException e) {
                showError("Запись с указанным ID отсутствует!");
            }
        } else {
            showError("Отсутствуют аргументы для комманды " + CMD_GET_BY_ID);
        }
    }

    @Override
    void showAllRecords() {
        showMsg("Получение всех записей...");
        List<Post> regionList = postController.getAll();
        if (regionList.size() == 0) {
            showError("Репозиторий пуст!");
            return;
        }
        regionList.forEach(r -> showMsg(r.toString()));
    }

    @Override
    void showHelp() {
        showMsg("Записи выводятся в формате <ID>|<CREATED>|<UPDATED> <CONTENT>\n\t" +
                CMD_HELP + " - вывод справки\n\t" +
                CMD_CREATE + " <CONTENT> - создание новой записи\n\t" +
                CMD_DELETE + " <ID> - удаление записи\n\t" +
                CMD_GET_ALL + " - получение всех записей\n\t" +
                CMD_GET_BY_ID + " <ID> - получение одной записи по ID\n\t" +
                CMD_EDIT_BY_ID + " <ID> <CONTENT> - изменение существующей записи\n\t" +
                CMD_EXIT + " - выход из программы");
    }

    @Override
    void exit() {
        showMsg("exit");
        super.exit();
    }

    private String getContentString(int indexStart, String[] commands) {
        StringBuilder sb = new StringBuilder();
        for (int i = indexStart; i < commands.length; i++) {
            sb.append(commands[i]);
            if (i != commands.length - 1) sb.append(" ");
        }
        return sb.toString();
    }
}
