package guhar4k.crud;

import guhar4k.crud.view.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            View view = ViewFactory.getView(args[0]);
            view.start();

        } else {
            System.out.println("Запустите приложение с одним из аргументов(regions, users или posts), для продолжения работы!");
        }
    }
}
