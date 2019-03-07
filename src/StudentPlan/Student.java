package StudentPlan;

public class Student {
    private final double learnCof;
    private double TKnowledge;
    private double PKnowledge;
    private boolean laptop;

    public Student(double learnCof, double tknowledge, double pknowledge, boolean laptop) {
        this.learnCof = learnCof;
        TKnowledge = tknowledge;
        PKnowledge = pknowledge;
        this.laptop = laptop;
    }
    public Student(double learnCof, boolean laptop) {
        this(learnCof, 0 , 0, laptop);
    }

    public void addKnowledge( double[] knowledges) {
        TKnowledge += knowledges[0] * (learnCof / 100);
        PKnowledge += knowledges[1] * (learnCof / 100);
    }

    public void usePlan(Plan plan) {
        plan.completePlan(this);
    }

    public double getPKnowledge() {
        return PKnowledge;
    }

    public double getTKnowledge() {
        return TKnowledge;
    }

    public double getLearnCof() {
        return learnCof / 100;
    }

    public boolean isLaptop() {
        return laptop;
    }
}
