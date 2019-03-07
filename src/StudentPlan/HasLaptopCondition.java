package StudentPlan;

public class HasLaptopCondition implements Condition {
    private boolean required;

    public HasLaptopCondition(boolean required) {
        this.required = required;
    }

    @Override
    public boolean checkStudent(Student student) {
        return student.isLaptop();
    }

    @Override
    public boolean isRequired() {
        return required;
    }
}
