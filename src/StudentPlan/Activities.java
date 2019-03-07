package StudentPlan;

public class Activities {
    private Schedule schedule;
    private double TKnowledge;
    private double PKnowledge;
    private Condition condition;

    public Activities(double tKnowledge, double pKnowledge, TimeFrame... timeFrames) {
        TKnowledge = tKnowledge;
        PKnowledge = pKnowledge;
        schedule = ScheduleGenerator.generateScheduleByTimeFrames(timeFrames, TKnowledge, PKnowledge);
    }

    public Activities(Condition condition, double tKnowledge, double pKnowledge, TimeFrame... timeFrames) {
        //я не юзаю this() тому що не мені приходить timeFrames...
        this.condition = condition;
        TKnowledge = tKnowledge;
        PKnowledge = pKnowledge;
        schedule = ScheduleGenerator.generateScheduleByTimeFrames(timeFrames, TKnowledge, PKnowledge);
    }

    public Activities(Student student, TimeFrame... timeFrames) {
        TKnowledge = student.getTKnowledge() * student.getLearnCof();
        PKnowledge = student.getPKnowledge() * student.getLearnCof();
        schedule = ScheduleGenerator.generateScheduleByTimeFrames(timeFrames, TKnowledge, PKnowledge);
    }

    public Schedule getSchedule(boolean withPractice) {
        if (withPractice) {
            return schedule;
        }
        for (Day day : schedule.getDays()) {
            day.withoutPractice();
        }
        return schedule;
    }

    public Condition getCondition() {
        return condition;
    }
}
