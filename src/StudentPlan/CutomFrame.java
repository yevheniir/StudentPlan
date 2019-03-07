package StudentPlan;

import java.util.function.Predicate;

public class CutomFrame implements TimeFrame {
    Predicate<Day> filter;

    public CutomFrame(Predicate<Day> filter) {
        this.filter = filter;
    }

    @Override
    public Schedule generateSchedule(double TKnowledge, double PKnowledge) {
        return new Schedule();
    }

    @Override
    public boolean validateDay(Day day) {
        return filter.test(day);
    }
}
