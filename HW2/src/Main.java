import java.util.Scanner;

public class Main {

    private static void help() {
        System.out.println("""
                Для использования программы необходимо вводить один символ:
                e - для выхода
                h - для вывода этой подсказки
                s - начать новый урок
                a - спросить ученика, который еще не был опрошен за урок
                l - вывести список группы
                """);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        help();
        Teacher teacher = new Teacher();
        System.out.println("Новый урок начался.");
        char command = '0';
        do {
            try {
                command = scan.nextLine().charAt(0);
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("Некорректный ввод, попробуйте снова.");
                continue;
            }
            switch (command) {
                case 's' -> {
                    teacher = new Teacher();
                    System.out.println("Новый урок начался.");
                }
                case 'a' -> teacher.askStudent();
                case 'l' -> teacher.showGroupList();
                case 'h' -> help();
                case 'e' -> {}
                default -> System.out.println("Неизвестная команда, для вызова подсказки напишите h");
            }
        } while (command != 'e');
    }
}