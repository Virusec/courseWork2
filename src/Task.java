import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task implements Repeatable {
    private String title;
    private String description;
    private final Type type;
    private LocalDateTime dateTime;
    private final int id;
    private static int idGenerator = 0;

    public Task(String title, String description, Type type, LocalDateTime taskDateTime) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.dateTime = taskDateTime;
        this.id = idGenerator++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public static int getIdGenerator() {
        return idGenerator;
    }

    public static void setIdGenerator(int idGenerator) {
        Task.idGenerator = idGenerator;
    }

    @Override
    public boolean isAvailable(LocalDate inputDate) {
        return inputDate.isEqual(getDateTime().toLocalDate());
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", dateTime=" + dateTime +
                ", id=" + id +
                '}';
    }
}
