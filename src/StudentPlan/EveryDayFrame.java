package StudentPlan;

import java.time.LocalDate;

public class EveryDayFrame implements TimeFrame {
    private LocalDate startDay;
    private LocalDate endDay;

    public EveryDayFrame(LocalDate startDay, LocalDate endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
    }

    @Override
    public Schedule generateSchedule(double TKnowledge, double PKnowledge) {
        return new Schedule(startDay, endDay, TKnowledge, PKnowledge);
    }

    @Override
    public boolean validateDay(Day day) {
        return day.getDate().equals(startDay) || day.getDate().equals(endDay) ||
                day.getDate().isAfter(startDay)
                && day.getDate().isBefore(endDay);
    }
}
