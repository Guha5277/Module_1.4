package ru.guhar4k.view;

import ru.guhar4k.controller.RegionController;
import ru.guhar4k.model.Region;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RegionView {
    private RegionController regionController;
    private Scanner scanner;
    private PrintStream out;
    private boolean isInterrupted;

    private final String CMD_HELP = "?";
    private final String CMD_EXIT = "exit";
    private final String CMD_GET_ALL = "get_all";
    private final String CMD_GET_BY_ID = "get";
    private final String CMD_EDIT_BY_ID = "edit";
    private final String CMD_CREATE = "new";
    private final String CMD_DELETE = "del";

    public RegionView() {
        regionController = new RegionController();
        scanner = new Scanner(System.in);
        out = new PrintStream(System.out);
        start();
    }

    private void start() {
        out.print("База данных регионов. Введите " + CMD_HELP + " - для получения справки, " + CMD_EXIT + " - для выхода из программы \n");

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

    private void showError(String cause) {
        out.print("Ошибка! " + cause + "\n");
    }

    private void showMsg(String msg) {
        out.print(msg + "\n");
    }

    private void showHelp() {
        out.print("Записи выводятся в формате <ID>:<NAME>\n\t" +
                CMD_HELP + " - вывод справки\n\t" +
                CMD_CREATE + " <NAME> - создание новой записи\n\t" +
                CMD_DELETE + " <ID> - удаление записи\n\t" +
                CMD_GET_ALL + " - получение всех записей\n\t" +
                CMD_GET_BY_ID + " <ID> - получение одной записи по ID\n\t" +
                CMD_EDIT_BY_ID + " <ID> <NAME> - изменение существующей записи\n\t" +
                CMD_EXIT + " - выход из программы\n");
    }

    private void exit() {
        showMsg("Выход из программы...");
        isInterrupted = true;
    }

    private void showAllRecords() {
        showMsg("Получение всех записей...");
        List<Region> regionList = regionController.getAll();
        if (regionList.size() == 0) {
            showError("Репозиторий пуст!");
            return;
        }
        regionList.forEach(r -> showMsg(r.toString()));
    }

    private void getById(String[] command) {
        if (command.length > 1) {
            showMsg("Получение записи...");
            try {
                Long id = Long.valueOf(command[1]);
                Region region = regionController.getById(id);
                showMsg(region.toString());
            } catch (NumberFormatException e) {
                showError("Неверный аргумент для комманды " + CMD_GET_BY_ID);
            } catch (NoSuchElementException e) {
                showError("Запись с указанным ID отсутствует!");
            }
        } else {
            showError("Отсутствуют аргументы для комманды " + CMD_GET_BY_ID);
        }
    }

    private void createNewRecord(String[] command) {
        if (command.length > 1) {
            String name = command[1];
            Region region = new Region(name);
            regionController.save(region);
            showMsg("Запись создана!");
            showAllRecords();
        } else {
            showError("Отсутствуют аргументы для комманды " + CMD_CREATE);
        }
    }

    private void deleteRecord(String[] command) {
        if (command.length > 1) {
            showMsg("Удаление записи...");
            try {
                Long id = Long.valueOf(command[1]);
                regionController.deleteById(id);
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

    private void editRecord(String[] command) {
        int ID = 1;
        int NAME = 2;
        if (command.length > 2) {
            showMsg("Изменение записи...");
            try {
                Long id = Long.valueOf(command[1]);
                //TODO получение записи с таким ID, если её нет - пишем ошибку, если есть - меняем
                regionController.update(new Region(Integer.valueOf(command[ID]), command[NAME]));
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
}
