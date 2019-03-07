package StudentPlan;

import java.time.LocalDate;

public class YearsFrame implements TimeFrame {
    private int startYear;
    private int endYear;

    public YearsFrame(int startYear, int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
    }

    @Override
    public Schedule generateSchedule(double TKnowledge, double PKnowledge) {
        return new Schedule(LocalDate.of(startYear, 9, 1),
                            LocalDate.of(endYear, 5, 30), TKnowledge, PKnowledge);
    }

    @Override
    public boolean validateDay(Day day) {
        return day.getDate().equals(LocalDate.of(startYear, 9, 1)) ||
                day.getDate().equals(LocalDate.of(endYear, 5, 30)) ||
                day.getDate().isAfter(LocalDate.of(startYear, 9, 1)) &&
                day.getDate().isBefore(LocalDate.of(endYear, 5, 30));
    }
}
