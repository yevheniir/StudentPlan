package StudentPlan;

import java.time.LocalDate;

public class Day {
    private LocalDate date;
    private double TKnowledge;
    private double PKnowledge;

    public Day(LocalDate date, double tKnowledge, double pKnowledge) {
        this.date = date;
        TKnowledge = tKnowledge;
        PKnowledge = pKnowledge;
    }

    public double[] live() {
        return new double[]{TKnowledge, PKnowledge};
    }

    public LocalDate getDate() {
        return date;
    }

    public void addEvent(double[] knowledge) {
        TKnowledge += knowledge[0];
        PKnowledge += knowledge[1];
    }

    public void withoutPractice() {
        this.PKnowledge = 0;
    }
}
