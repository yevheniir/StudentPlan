package StudentPlan;

import java.util.Arrays;

public class CreditedCondition implements Condition {
    private Student[] credited;
    private boolean required;

    public CreditedCondition(Student[] credited, boolean required) {
        this.credited = credited;
        this.required = required;
    }

    @Override
    public boolean checkStudent(Student student) {
        for (Student st : credited) {
            if (st == student) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isRequired() {
        return required;
    }
}
