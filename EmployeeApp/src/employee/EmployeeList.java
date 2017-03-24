package employee;
import java.util.*;



public class EmployeeList {
    
    ArrayList<Employee> employees = new ArrayList<Employee>();
    
    public int length(){ return employees.size(); }
    
    public Employee get(int index){ return employees.get( index ); }
    
    public void getData ( String input ){
        String data[] = input.trim().split("\\s+");
        for ( int i = 0 ; i < data.length ; i +=3 ){
            String name = data[i];
            int age = Integer.parseInt( data [i+1] );
            float salary = Float.parseFloat(data[i+2]);
            employees.add(new SalaryEmployee( name, age, salary));
        }
    }
    
    public void sortByName(){
       /* 
        for (int i=0 ; i<length()-1 ; i++)
        for (int j=0 ; j<length()-1 ; j++){
            if (get(j+1).getName().compareTo(get(j).getName())<0){
                Employee temp = employees.get(j);
                employees.set(j, employees.get(j+1));
                employees.set(j+1, temp);
            }
        }*/
       
       Collections.sort(employees);
    }
    
    public void sortByIncome(){
        Collections.sort(employees, (e1,e2) -> Float.compare(e1.getIncome(), e2.getIncome()));
    }
    
    public float getAverageAge(){
        float total = 0;
        for (int i = 0 ; i < length() ; i++)
            total += get(i).getIncome();
        return total/length();
    }
    
    public void sortByAge(){
        /*
        for (int i=0 ; i<length()-1 ; i++)
        for (int j=0 ; j<length()-1 ; j++){
            if (get(j+1).getAge().compareTo(get(j).getAge())<0){
                Employee temp = employees.get(j);
                employees.set(j, employees.get(j+1));
                employees.set(j+1, temp);
            }
        */
    }
}

    
