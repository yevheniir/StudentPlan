package StudentPlan;

public class Plan {
    private Schedule schedule;
    private Activities[] activities;

    public Plan(Activities... events) {
        this.activities = events;
    }

    public double[] completePlan(Student student) {
        schedule = ScheduleGenerator.generateScheduleByActivities(activities, student);
        double[] knowledges = schedule.complete(student);

        return knowledges;
    }
}
