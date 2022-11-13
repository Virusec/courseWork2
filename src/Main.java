import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskService taskService = new TaskService();
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(taskService, scanner);
                            break;
                        case 2:
                            removeTask(taskService, scanner);
                            break;
                        case 3:
                            getTaskByDay(taskService, scanner);
                            break;
//                        case 4:
//                            getAllTaskFromTaskList();
//                            break;
//                        case 5:
//                            editTask(scanner);
//                            break;
//                        case 6:
//                            getDeletedTask();
//                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static LocalDate checkDate(Scanner scanner) {
        LocalDate result = null;
        boolean ForceUserToAnswer = true;
        while (ForceUserToAnswer) {
            try {
                System.out.print("Введите дату задачи в формате dd.mm.yyyy: ");
                String date = scanner.nextLine();
                result = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                ForceUserToAnswer = false;
            } catch (Exception e) {
                System.out.println("Формат не корректный! Введите дату еще раз!");
            }
        }
        return result;
    }

    private static LocalTime checkTime(Scanner scanner) {
        LocalTime result = null;
        boolean ForceUserToAnswer = true;
        while (ForceUserToAnswer) {
            try {
                System.out.print("Введите время задачи в формате HH:mm: ");
                String time = scanner.nextLine();
                result = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
                ForceUserToAnswer = false;
            } catch (Exception e) {
                System.out.println("Формат не корректный! Введите время еще раз!");
            }
        }
        return result;
    }

    private static void inputTask(TaskService taskService, Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String name = scanner.next();
        scanner.nextLine();
        System.out.print("Введите описание задачи: ");
        String description = scanner.nextLine();

        LocalDate taskDate = checkDate(scanner);

        LocalTime taskTime = checkTime(scanner);

        LocalDateTime resultDate = LocalDateTime.of(taskDate, taskTime);
        System.out.print("Введите тип задачи(Личный - 1, Рабочий - 2) : ");
        int type = scanner.nextInt();
        Type taskType = type == 1 ? Type.PERSONAL : Type.WORK;
        System.out.println("""
                Введите повторяемость задачи :
                не повторяется - 0
                дневная - 1
                недельная - 2
                месячная - 3
                годовая - 4""");
        int number = scanner.nextInt();
        switch (number) {
            case 0:
                taskService.add(new Task(name, description, taskType, resultDate));
                break;
            case 1:
                taskService.add(new DailyTask(name, description, taskType, resultDate));
                break;
            case 2:
                taskService.add(new WeeklyTask(name, description, taskType, resultDate));
                break;
            case 3:
                taskService.add(new MonthlyTask(name, description, taskType, resultDate));
                break;
            case 4:
                taskService.add(new YearlyTask(name, description, taskType, resultDate));
                break;
            default:
                throw new RuntimeException("Не существует!");
        }
    }

    private static void removeTask(TaskService taskService, Scanner scanner) {
        System.out.println("Введите id задачи которую необходимо удалить: ");
        int idNum = scanner.nextInt();
        taskService.remove(idNum);
    }

    private static void getTaskByDay(TaskService taskService, Scanner scanner) {
        System.out.println("Введите дату задачи в формате dd.mm.yyyy: ");
        scanner.nextLine();
        String date = scanner.nextLine();
        LocalDate taskDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        var allTaskByDay = taskService.getAllByDate(taskDate);
        System.out.println("Список задач этого дня:");
        for (Task task : allTaskByDay) {
            System.out.println(task);
        }
    }


    private static void printMenu() {
        System.out.println(
                """
                        1. Добавить задачу
                        2. Удалить задачу
                        3. Получить задачу на указанный день
                        4. Получить все задачи
                        5. Редактировать заголовок и описание задачи
                        6. Получить список всех удаленных задач
                        0. Выход
                        """
        );
    }

}
