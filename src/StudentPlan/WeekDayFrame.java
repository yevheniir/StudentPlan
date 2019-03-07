package StudentPlan;

import java.time.DayOfWeek;

public class WeekDayFrame implements TimeFrame {
    @Override
    public Schedule generateSchedule(double TKnowledge, double PKnowledge) {
        return new Schedule();
    }

    @Override
    public boolean validateDay(Day day) {
        return day.getDate().getDayOfWeek() != DayOfWeek.SATURDAY
                && day.getDate().getDayOfWeek() != DayOfWeek.SUNDAY;
    }
}
