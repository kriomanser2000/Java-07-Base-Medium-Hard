import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Person
{
    private String name;
    private int age;
    private String city;
    public Person(String name, int age, String city)
    {
        this.name = name;
        this.age = age;
        this.city = city;
    }
    public String getName()
    {
        return name;
    }
    public String getCity()
    {
        return city;
    }
    public static void main(String[] args)
    {
        List<Person> persons = Arrays.asList(
                new Person("Oleksandr", 17, "Kryvyi Rih"),
                new Person("Eugene", 47, "Kryvyi Rih"),
                new Person("Valentina", 88, "Kryvyi Rih"),
                new Person("Roman", 43, "Lviv"),
                new Person("Dmitry", 16, "Lviv"),
                new Person("Caesar", 55, "Rome"),
                new Person("notCaesar", 35, "Rome")
        );
        Map<String, List<String>> cityToNamesMap = persons.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.mapping(Person::getName, Collectors.toList())
                ));
        System.out.println(cityToNamesMap);
    }
}
