package guhar4k.crud;

import guhar4k.crud.view.PostView;
import guhar4k.crud.view.RegionView;
import guhar4k.crud.view.UserView;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            switch (args[0]){
                case "region":
                    new RegionView();
                    break;
                case "user":
                    new UserView();
                    break;
                case "post":
                    new PostView();
                    break;
                default:
                    System.out.println("Незивестный аргумент. Завершение программы...");
            }
        } else {
            System.out.println("Запустите приложение с одним из аргументов, для продолжения работы!");
        }
    }
}
