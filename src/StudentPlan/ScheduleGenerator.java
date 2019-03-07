package StudentPlan;

public class ScheduleGenerator {
    public static Schedule generateScheduleByActivities(Activities[] events, Student student) {
        Schedule schedule = new Schedule();

        for (Activities activities : events) {
            try {
                if (activities.getCondition().checkStudent(student) && activities.getCondition().isRequired()) {
                    schedule.addEvent(activities.getSchedule(true));
                } else if (activities.getCondition().checkStudent(student) && !activities.getCondition().isRequired()) {
                    schedule.addEvent(activities.getSchedule(true));
                } else if (!activities.getCondition().isRequired()) {
                    schedule.addEvent(activities.getSchedule(false));
                }
            } catch (Exception e) {
                schedule.addEvent(activities.getSchedule(true));
            }
        }
        return schedule;
    }

    public static Schedule generateScheduleByTimeFrames(TimeFrame[] frames, double TKnowledge, double PKnowledge) {
        Schedule schedule = new Schedule();
        Schedule validSchedule = new Schedule();

        for (TimeFrame frame : frames) {
            schedule.addEvent(frame.generateSchedule(TKnowledge, PKnowledge));
        }

        for (Day day : schedule.getDays()) {
            if(isWork(day, frames)) {
                validSchedule.addDay(new Day(day.getDate(), TKnowledge, PKnowledge));
            }
        }
        return validSchedule;
    }

    private static boolean isWork(Day day, TimeFrame[] frames) {
        boolean work = true;
        for (TimeFrame frame : frames) {
            if (!frame.validateDay(day)) {
                work = false;
                break;
            }
        }
        return work;
    }
}
