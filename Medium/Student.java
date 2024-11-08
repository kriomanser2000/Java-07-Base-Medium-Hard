import java.util.*;
import java.util.stream.Collectors;

public class Student
{
    String name;
    List<Integer> grades;
    public Student(String name, List<Integer> grades)
    {
        this.name = name;
        this.grades = grades;
    }
    public String getName()
    {
        return name;
    }
    public List<Integer> getGrades()
    {
        return grades;
    }
    public static void main(String[] args)
    {
        List<Student> students = Arrays.asList(
                new Student("Oleksandr", Arrays.asList(70, 75, 85)),
                new Student("Caesar", Arrays.asList(90, 80, 85)),
                new Student("Stepan", Arrays.asList(85, 95, 90))
        );
        Map<String, Double> avgGrades = students.stream()
                .collect(Collectors.toMap(
                        Student::getName,
                        student -> student.getGrades().stream()
                                .mapToInt(Integer::intValue)
                                .average()
                                .orElse(0.0)
                ));
        Map<String, Double> sortedAvgGrades = avgGrades.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        sortedAvgGrades.forEach((name, avg) -> System.out.println(name + ": " + avg));
    }
}
