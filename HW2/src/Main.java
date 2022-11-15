import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

class Group {
    protected static Random rng = new Random();
    private final int size;
    protected Student[] groupList;
    private ArrayList<Student> students;

    public Group() {
        size = rng.nextInt(7, 33);
        groupList = new Student[size];
        students = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            Student student = new Student();
            groupList[i] = student;
            students.add(i, student);
        }
    }

    public int getSize() {
        return size;
    }

    protected static class Student {
        private final String name;
        private final String surname;
        private final boolean attended;
        private int grade;

        public Student() {
            name = GenerateString();
            surname = GenerateString();
            attended = rng.nextBoolean();
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public boolean isAttended() {
            return attended;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        @Override
        public String toString() {
            return name + " " + surname + " " + (attended?"+":"-") + " Оценка: " + grade;
        }

        private static String GenerateString() {
            int length = rng.nextInt(6, 12);
            StringBuilder string = new StringBuilder();
            string.append((char)('A' + rng.nextInt(0, 26)));
            for(int i = 0; i < length; ++i) {
                string.append((char)('a' + rng.nextInt(0, 26)));
            }
            return string.toString();
        }
    }

    protected Student ChooseStudent() {
        if (students.size() == 0) {
            throw new IndexOutOfBoundsException("No students left");
        }
        int index = rng.nextInt(0, students.size());
        return students.remove(index);
    }
}

class Teacher {
    private Group group;

    Teacher() {
        group = new Group();
    }

    public void AskStudent() {
        Group.Student student;
        try {
            student = group.ChooseStudent();
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Урок окончен, все присутствующие ученики опрошены.");
            return;
        }
        System.out.printf(">> На вопрос отвечает %s %s.\n",
                          student.getName(),
                          student.getSurname());
        if (student.isAttended()) {
            System.out.println("> Да, я здесь.");
            System.out.println(" *Отвечает на вопрос*.");
            int result = Group.rng.nextInt(1, 11);
            if (result < 4) {
                System.out.printf(">> Нет, к сожалению это вовсе неверно." +
                                  " За такой ответ могу поставить только %d.\n", result);
            } else if (result < 6) {
                System.out.printf(">> Частично Вы правы, но в вашем ответе есть важное упущение." +
                        " Так что ставлю Вам %d.\n", result);
            } else if (result < 8) {
                System.out.printf(">> В целом все верно, но Вы забыли небольшую деталь." +
                        " Но в целом хороший ответ, поставлю Вам %d.\n", result);
            } else {
                System.out.printf(">> Да, все верно, отлично, %d.\n", result);
            }
            student.setGrade(result);
        } else {
            System.out.println("> *Тишина* ");
            System.out.println(">> Понятно, нету, тогда ставлю 0 за отсутствие.");
        }
    }

    public void ShowGroupList() {
        System.out.printf("Всего учащихся: %d\n", group.getSize());
        for (Group.Student student : group.groupList) {
            System.out.println(student);
        }
    }
}

public class Main {

    private static void Help() {
        System.out.println("""
                Для использования программы необходимо вводить один символ:
                e - для выхода
                h - для вывода этой подсказки
                a - спросить ученика, который еще не был опрошен за урок
                l - вывести список группы
                """);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Help();
        Teacher teacher = new Teacher();
        char command = '0';
        do {
            try {
                command = scan.nextLine().charAt(0);
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("Некорректный ввод, попробуйте снова.");
                continue;
            }
            switch (command) {
                case 'a':
                    teacher.AskStudent();
                    break;
                case 'l':
                    teacher.ShowGroupList();
                    break;
                case 'h':
                    Help();
                    break;
                case 'e':
                    continue;
                default:
                    System.out.println("Неизвестная команда, для вызова подсказки напишите h");
            }
        } while (command != 'e');
    }
}