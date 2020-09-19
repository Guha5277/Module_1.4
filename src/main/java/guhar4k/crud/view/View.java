package guhar4k.crud.view;

import java.io.PrintStream;
import java.util.Scanner;

public class View {
    private Scanner scanner;
    private PrintStream out;
    private boolean isInterrupted;

    final String CMD_HELP = "?";
    final String CMD_EXIT = "exit";
    final String CMD_GET_ALL = "get_all";
    final String CMD_GET_BY_ID = "get";
    final String CMD_EDIT_BY_ID = "edit";
    final String CMD_CREATE = "new";
    final String CMD_DELETE = "del";
    private final String REPOSITORY_NAME;

    View(Scanner in, PrintStream out, String repositoryName) {
        this.scanner = in;
        this.out = out;
        this.REPOSITORY_NAME = repositoryName;
    }

    void start() {
        out.print(REPOSITORY_NAME + ". Введите " + CMD_HELP + " - для получения справки, " + CMD_EXIT + " - для выхода из программы \n");

        while (!isInterrupted) {
            handleMsg(scanner.nextLine());
        }
    }

    private void handleMsg(String msg) {
        String[] command = msg.split(" ");
        switch (command[0]) {
            case CMD_HELP:
                showHelp();
                break;

            case CMD_EXIT:
                exit();
                break;

            case CMD_GET_ALL:
                showAllRecords();
                break;

            case CMD_GET_BY_ID:
                getById(command);
                break;

            case CMD_CREATE:
                createNewRecord(command);
                break;

            case CMD_DELETE:
                deleteRecord(command);
                break;

            case CMD_EDIT_BY_ID:
                editRecord(command);
                break;

            default:
                showError("Неизвестный запрос! Введите " + CMD_HELP + " для получения справки");
        }
    }

    void editRecord(String[] command) {
        throwException();
    }

    void deleteRecord(String[] command) {
        throwException();
    }

    void createNewRecord(String[] command) {
        throwException();
    }

    void getById(String[] command) {
        throwException();
    }

    void showAllRecords() {
        throwException();
    }

    void showHelp() {
        throwException();
    }

    void exit() {
        isInterrupted = true;
    }

    void showError(String cause) {
        out.print("Ошибка! " + cause + "\n");
    }

    void showMsg(String msg) {
        out.print(msg + "\n");
    }

    private void throwException() {
        throw new RuntimeException("Метод не переопределён, продолжение работы программы невозможно!");
    }
}
