package StudentPlan;

import java.time.LocalDate;
import java.util.ArrayList;

public class Schedule {
    private ArrayList<Day> days = new ArrayList<Day>();

    public Schedule(LocalDate startDate, LocalDate endDate, double TKnowledge, double PKnowledge) {
        if (startDate.equals(endDate)) {
            days.add(new Day(startDate, TKnowledge, PKnowledge));
        }
        while (!startDate.equals(endDate)) {
            days.add(new Day(startDate, TKnowledge, PKnowledge));
            startDate = startDate.plusDays(1);
        }
    }

    public Schedule(LocalDate date, double TKnowledge, double PKnowledge) {
        days.add(new Day(date, TKnowledge, PKnowledge));
    }

    public Schedule() {}

    public ArrayList<Day> getDays() {
        return days;
    }

    public double[] complete(Student student) {
        double[] knowledge = {0, 0};
        for (Day day : days) {
            double[] newKnowledge = day.live();
            student.addKnowledge(newKnowledge);
        }
        return knowledge;
    }

    public void addEvent(Schedule schedule) {

        for (Day day : schedule.getDays()) {
            addDay(day);
        }

//        days.add(new Day(date, TKnowledge, PKnowledge));
    }

    public void addDay(Day day) {
        for (Day busyDay : days) {
            if (busyDay.getDate().equals(day.getDate())) {
                busyDay.addEvent(day.live());
                return;
            }
        }
        days.add(new Day(day.getDate(), day.live()[0], day.live()[1]));
    }

}
