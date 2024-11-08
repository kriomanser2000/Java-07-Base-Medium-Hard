import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Employee
{
    String name;
    double salary;
    String department;
    public Employee(String name,double salary,String department)
    {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
    public String getName()
    {
        return name;
    }
    public double getSalary()
    {
        return salary;
    }
    public String getDepartment()
    {
        return department;
    }
    @Override
    public String toString()
    {
        return name + "("+ salary +")";
    }
    public static void main(String[] args)
    {
        List<Employee> employees = Arrays.asList(
                new Employee("Oleksandr", 1200, "IT"),
                new Employee("Caesar", 1500, "IT"),
                new Employee("Volodymyr", 800, "HR"),
                new Employee("Eugene", 1700, "IT"),
                new Employee("Denis", 1900, "HR"),
                new Employee("Roman", 2200, "HR"),
                new Employee("Stepan", 900, "HR")
        );
        Map<String, List<Employee>> topSalariesByDepartament = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                                        .limit(3)
                                        .collect(Collectors.toList()))
                ));
        topSalariesByDepartament.forEach((department, emplist) ->
        {
            System.out.println(department + ": " + emplist);
        });
    }
}
