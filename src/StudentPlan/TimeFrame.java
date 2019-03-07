package StudentPlan;

public interface TimeFrame {
    Schedule generateSchedule(double TKnowledge, double PKnowledge);

    boolean validateDay(Day day);
}
