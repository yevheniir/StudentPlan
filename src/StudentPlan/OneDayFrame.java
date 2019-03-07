package StudentPlan;
import java.time.LocalDate;

public class OneDayFrame implements TimeFrame {
    private LocalDate date;

    public OneDayFrame(LocalDate date) {
        this.date = date;
    }

    @Override
    public Schedule generateSchedule(double TKnowledge, double PKnowledge) {
        return new Schedule(date, TKnowledge, PKnowledge);
    }

    @Override
    public boolean validateDay(Day day) {
        return day.getDate().equals(date);
    }
}
