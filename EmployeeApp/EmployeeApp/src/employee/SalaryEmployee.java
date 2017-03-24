package employee;


public class SalaryEmployee extends Employee {

    private float salary;

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getIncome() {
        return getSalary();
    }

    public SalaryEmployee(String name, int age, float salary) {
        super(name, age);
        setSalary(salary);
    }

    public SalaryEmployee() {
        this("", 0, 0);
    }

}
