import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task {

    public DailyTask(String title, String description, Type type, LocalDateTime taskDateTime) {
        super(title, description, type, taskDateTime);
    }

    @Override
    public boolean isAvailable(LocalDate inputDate) {
        var startDate = getDateTime().toLocalDate();
        while (!startDate.isAfter(inputDate)) {
            if (startDate.equals(inputDate)) {
                return true;
            }
            startDate = startDate.plusDays(1);
        }
        return false;
    }
}
