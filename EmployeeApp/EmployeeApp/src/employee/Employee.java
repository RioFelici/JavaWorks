package employee;


abstract public class Employee implements Comparable<Employee> {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int compareTo(Employee e) {
        return getName().compareTo(e.getName());
        //   return getAge().compareTo(e.getAge());
    }

    public Employee(String name, int age) {
        setName(name);
        setAge(age);
    }

    public Employee() {
        this("", 0);
    }

    abstract public float getIncome();
}
