class Teacher {
    private final Group group;

    Teacher() {
        group = new Group();
    }

    public void askStudent() {
        Group.Student student;
        try {
            student = group.chooseStudent();
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

    public void showGroupList() {
        System.out.printf("Всего учащихся: %d\n", group.getSize());
        for (Group.Student student : group.groupList) {
            System.out.println(student);
        }
    }
}