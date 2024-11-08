import java.util.*;
import java.util.stream.Collectors;

class Course
{
    String title;
    int credits;
    public Course(String title, int credits)
    {
        this.title = title;
        this.credits = credits;
    }
    public String getTitle()
    {
        return title;
    }
    public int getCredits()
    {
        return credits;
    }
    class Student
    {
        String name;
        int year;
        public Student(String name, int year)
        {
            this.name = name;
            this.year = year;
        }
        public String getName()
        {
            return name;
        }
        public int getYear()
        {
            return year;
        }
    }
    class Enrollment
    {
        Course course;
        Student student;
        double grade;
        public Enrollment(Course course, Student student, double grade)
        {
            this.course = course;
            this.student = student;
            this.grade = grade;
        }
        public Course getCourse()
        {
            return course;
        }
        public Student getStudent()
        {
            return student;
        }
        public double getGrade()
        {
            return grade;
        }
    }
    public void main(String[] args)
    {
        Course course1 = new Course("Mathematics", 5);
        Course course2 = new Course("History", 3);
        Student student1 = new Student("Oleksandr", 3);
        Student student2 = new Student("Caesar", 4);
        Student student3 = new Student("Stepan", 2);
        List<Enrollment> enrollments = Arrays.asList(
                new Enrollment(course1, student1, 85),
                new Enrollment(course1, student2, 90),
                new Enrollment(course2, student1, 88),
                new Enrollment(course2, student2, 92),
                new Enrollment(course2, student3, 78)
        );
        Map<Course, Double> averageGrades = enrollments.stream()
                .filter(e -> e.getStudent().getYear() > 2)
                .collect(Collectors.groupingBy(
                        Enrollment::getCourse,
                        Collectors.averagingDouble(Enrollment::getGrade)
                ));
        averageGrades.forEach((course, avgGrade) ->
                System.out.println(course.getTitle() + ": " + avgGrade)
        );
    }
}
