package employee;


public class HourlyEmployee extends Employee {

    private float wage;
    private float hours;

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public float getWage() {
        return wage;
    }

    public void setWage(float wage) {
        this.wage = wage;
    }

    public float getIncome() {
        return getWage();
    }

    public HourlyEmployee(String name, int age, float wage, float hours) {
        super(name, age);
        setWage(wage);
        setHours(hours);
    }

    public HourlyEmployee() {
        this("", 0, 0, 0);
    }

}
