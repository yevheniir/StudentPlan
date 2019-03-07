import StudentPlan.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StudentPlanTest {

     Student student_100;
    Student student_100_withoutLaptop;
     Activities chdbc;
     Activities oop;
     Activities chibs;
     Activities book;
    Activities chdbcWeek;
    Activities oop_custom;
    Activities oop_custom_year;
    Activities meet;
    Condition isCreditTrue;
    Condition isCreditFalse;
    Activities chdbc_isCredit;
    Activities chdbc_isCreditFalse;
    Activities meetUp_requiredLaptop;
    Condition requiredLaptop;
    Activities University_5years;
    Activities University_2years;
    Activities meetUpEveryThursday1InMonth;
    Activities selfEducation;
    Activities internship;
    Activities internshipAfterUniversity;

    @BeforeEach
    void setUp() {

        student_100 = new Student(100, true);
        student_100_withoutLaptop = new Student(100, false);
        isCreditTrue = new CreditedCondition(new Student[] {student_100}, true);
        isCreditFalse = new CreditedCondition(new Student[] {}, true);
        requiredLaptop = new HasLaptopCondition(false);

        chdbc = new Activities(5, 1, new SpanFrame(LocalDate.of(2019, 4, 3),
                                                                        LocalDate.of(2019, 4, 13)));

        chibs = new Activities(6, 2, new SpanFrame(LocalDate.of(2019, 7, 3),
                                                                        LocalDate.of(2019, 7, 13)));

        oop = new Activities(5, 5, new OneDayFrame(LocalDate.of(2019, 4, 15)));

        book = new Activities(4, 1, new EveryDayFrame(LocalDate.of(2019, 1, 1),
                                                                            LocalDate.of(2019, 2, 13)));

        chdbcWeek = new Activities(5, 1, new SpanFrame(LocalDate.of(2019, 4, 3),
                LocalDate.of(2019, 4, 10)),  new WeekDayFrame());

        oop_custom = new Activities(5, 5, new SpanFrame(LocalDate.of(2019, 4, 1),
                LocalDate.of(2019, 4, 30)),
                new CutomFrame((Day day) -> day.getDate().lengthOfMonth() - day.getDate().getDayOfMonth() < 7
                        && day.getDate().getDayOfWeek() == DayOfWeek.THURSDAY));

        oop_custom_year = new Activities(5, 5, new YearsFrame(2019, 2020),
                new CutomFrame((Day day) -> day.getDate().lengthOfMonth() - day.getDate().getDayOfMonth() < 7
                        && day.getDate().getDayOfWeek() == DayOfWeek.THURSDAY));

        meet = new Activities(new Student(100, 100, 100, true),
                new OneDayFrame(LocalDate.of(2019, 4, 15)));

        chdbc_isCredit = new Activities(isCreditTrue ,5, 1, new SpanFrame(LocalDate.of(2019, 4, 3),
                LocalDate.of(2019, 4, 13)));

        chdbc_isCreditFalse = new Activities(isCreditFalse ,5, 1, new SpanFrame(LocalDate.of(2019, 4, 3),
                LocalDate.of(2019, 4, 13)));

        meetUp_requiredLaptop = new Activities(requiredLaptop, 5, 5, new OneDayFrame(LocalDate.of(2019, 4, 15)));



        University_5years = new Activities(5, 1, new YearsFrame(2019, 2024), new WeekDayFrame());
        University_2years = new Activities(4, 2, new YearsFrame(2024, 2026), new WeekDayFrame());
        meetUpEveryThursday1InMonth = new Activities(5, 5, new YearsFrame(2019, 2026),
                new CutomFrame((Day day) -> day.getDate().lengthOfMonth() - day.getDate().getDayOfMonth() < 7
                        && day.getDate().getDayOfWeek() == DayOfWeek.THURSDAY));
        selfEducation = new Activities(4, 1, new YearsFrame(2019, 2026));
        internship = new Activities(4, 8, new SpanFrame(LocalDate.of(2019, 4, 3),
                LocalDate.of(2019, 7, 3)), new WeekDayFrame());
        internshipAfterUniversity = new Activities(4, 8, new SpanFrame(LocalDate.of(2023, 4, 3),
                LocalDate.of(2023, 7, 3)), new WeekDayFrame());
    }

    @Test
    void usePlan_1Activities() {
        student_100.usePlan(new Plan(chdbc));

        assertThat(student_100.getTKnowledge(), is(50.0));
    }

    @Test
    void usePlan_2Activities() {
        student_100.usePlan(new Plan(chdbc, chibs));

        assertThat(student_100.getPKnowledge(), is(30.0));
    }

    @Test
    void usePlan_1ActivitiesMeetUp() {
        student_100.usePlan(new Plan(oop));

        assertThat(student_100.getPKnowledge(), is(5.0));
    }

    @Test
    void usePlan_1SelfEducation() {
        student_100.usePlan(new Plan(book));

        assertThat(student_100.getPKnowledge(), is(43.0));
    }

    @Test
    void usePlan_1SelfEducation3MeetApp() {
        student_100.usePlan(new Plan(oop, oop, oop, book));

        assertThat(student_100.getPKnowledge(), is(58.0));
    }

    @Test
    void usePlan_1SelfEducation2MeetApp1Activities() {
        student_100.usePlan(new Plan(oop, oop, book, chdbc));

        assertThat(student_100.getPKnowledge(), is(63.0));
    }

    @Test
    void usePlan_3SelfEducation2MeetApp1Activities() {
        student_100.usePlan(new Plan(oop, oop, book, book, book, chdbc));

        assertThat(student_100.getPKnowledge(), is(149.0));
    }

    @Test
    void usePlan_1ActivitiesOnWeek() {
        student_100.usePlan(new Plan(chdbcWeek));

        assertThat(student_100.getTKnowledge(), is(25.0));
    }

    @Test
    void usePlan_2ActivitiesOnWeek_2MeetUp() {
        student_100.usePlan(new Plan(chdbcWeek, chdbcWeek, oop, oop));

        assertThat(student_100.getTKnowledge(), is(60.0));
    }

    @Test
    void usePlan_MeetUp_CustomFrame() {
        student_100.usePlan(new Plan(oop_custom));

        assertThat(student_100.getTKnowledge(), is(5.0));
    }

    @Test
    void usePlan_MeetUp_CustomFrame_YearFrame() {
        student_100.usePlan(new Plan(oop_custom_year));

        assertThat(student_100.getTKnowledge(), is(45.0));
    }

    @Test
    void usePlan_MeetUp_CustomFrame_2MeetUp() {
        student_100.usePlan(new Plan(oop_custom, oop, oop));

        assertThat(student_100.getTKnowledge(), is(15.0));
    }

    @Test
    void usePlan_Meet() {
        student_100.usePlan(new Plan(meet));

        assertThat(student_100.getTKnowledge(), is(100.0));
    }

    @Test
    void usePlan_universityIsCredit() {
        student_100.usePlan(new Plan(chdbc_isCredit));

        assertThat(student_100.getTKnowledge(), is(50.0));
    }

    @Test
    void usePlan_universityIsCredit_false() {
        student_100.usePlan(new Plan(chdbc_isCreditFalse));

        assertThat(student_100.getTKnowledge(), is(0.0));
    }

    @Test
    void usePlan_meetUp_hasLaptop() {
        student_100.usePlan(new Plan(meetUp_requiredLaptop));

        assertThat(student_100.getPKnowledge(), is(5.0));
    }

    @Test
    void usePlan_meetUp_hasNotLaptop() {
        student_100_withoutLaptop.usePlan(new Plan(meetUp_requiredLaptop));

        assertThat(student_100_withoutLaptop.getPKnowledge(), is(0.0));
    }

    @Test
    void usePlan_meetUp_hasNotLaptop_testKnowledge() {
        student_100_withoutLaptop.usePlan(new Plan(meetUp_requiredLaptop));

        assertThat(student_100_withoutLaptop.getTKnowledge(), is(5.0));
    }

    @Test
    void usePlan_Pacifist() {
        student_100.usePlan(new Plan(University_5years, University_2years));

        assertThat(student_100.getTKnowledge(), is(8010.0));
    }

    @Test
    void usePlan_SelfTaught() {
        student_100.usePlan(new Plan(meetUpEveryThursday1InMonth, selfEducation, internship));

        assertThat(student_100.getTKnowledge(), is(10517.0));
    }

    @Test
    void usePlan_Teach_me_completely() {
        student_100.usePlan(new Plan(University_5years, meetUpEveryThursday1InMonth));

        assertThat(student_100.getTKnowledge(), is(6595.0));
    }

    @Test
    void usePlan_Conscious_student() {
        student_100.usePlan(new Plan(University_5years, meetUpEveryThursday1InMonth, selfEducation, meet, meet, internshipAfterUniversity));

        assertThat(student_100.getTKnowledge(), is(16907.0));
    }

    @Test
    void use2Plans() {
        student_100.usePlan(new Plan(University_5years, meetUpEveryThursday1InMonth));
        student_100.usePlan(new Plan(University_5years, meetUpEveryThursday1InMonth, selfEducation, meet, meet, internshipAfterUniversity));

        assertThat(student_100.getTKnowledge(), is(23502.0));
    }

    @Test
    void use4Plans() {
        student_100.usePlan(new Plan(University_5years, University_2years));
        student_100.usePlan(new Plan(meetUpEveryThursday1InMonth, selfEducation, internship));
        student_100.usePlan(new Plan(University_5years, meetUpEveryThursday1InMonth));
        student_100.usePlan(new Plan(University_5years, meetUpEveryThursday1InMonth, selfEducation, meet, meet, internshipAfterUniversity));

        assertThat(student_100.getTKnowledge(), is(42029.0));
    }

    @Test
    void usePlan_Conscious_student_P() {
        student_100.usePlan(new Plan(University_5years, meetUpEveryThursday1InMonth, selfEducation, meet, meet, internshipAfterUniversity));

        assertThat(student_100.getPKnowledge(), is(4826.0));
    }

}