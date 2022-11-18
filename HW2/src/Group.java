import java.util.ArrayList;
import java.util.Random;

class Group {
    protected static Random rng = new Random();
    private final int size;
    protected Student[] groupList;
    private final ArrayList<Student> students;

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
            name = generateString();
            surname = generateString();
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

        private static String generateString() {
            int length = rng.nextInt(6, 12);
            StringBuilder string = new StringBuilder();
            string.append((char)('A' + rng.nextInt(0, 26)));
            for(int i = 0; i < length; ++i) {
                string.append((char)('a' + rng.nextInt(0, 26)));
            }
            return string.toString();
        }
    }

    protected Student chooseStudent() {
        if (students.size() == 0) {
            throw new IndexOutOfBoundsException("No students left");
        }
        int index = rng.nextInt(0, students.size());
        return students.remove(index);
    }
}
