package guhar4k.crud.view;

import guhar4k.crud.controller.RegionController;
import guhar4k.crud.model.Region;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RegionView extends View{
    private RegionController regionController;

    public RegionView() {
        super(new Scanner(System.in), new PrintStream(System.out), "База данных регионов");
        regionController = new RegionController();
        start();
    }

    @Override
    void showHelp() {
        showMsg("Записи выводятся в формате <ID>:<NAME>\n\t" +
                CMD_HELP + " - вывод справки\n\t" +
                CMD_CREATE + " <NAME> - создание новой записи\n\t" +
                CMD_DELETE + " <ID> - удаление записи\n\t" +
                CMD_GET_ALL + " - получение всех записей\n\t" +
                CMD_GET_BY_ID + " <ID> - получение одной записи по ID\n\t" +
                CMD_EDIT_BY_ID + " <ID> <NAME> - изменение существующей записи\n\t" +
                CMD_EXIT + " - выход из программы");
    }

    @Override
    void showAllRecords() {
        showMsg("Получение всех записей...");
        List<Region> regionList = regionController.getAll();
        if (regionList.size() == 0) {
            showError("Репозиторий пуст!");
            return;
        }
        regionList.forEach(r -> showMsg(r.toString()));
    }

    @Override
    void getById(String[] command) {
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

    @Override
    void createNewRecord(String[] command) {
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

    @Override
    void deleteRecord(String[] command) {
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

    @Override
    void editRecord(String[] command) {
        int ID = 1;
        int NAME = 2;
        if (command.length > 2) {
            showMsg("Изменение записи...");
            try {
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
