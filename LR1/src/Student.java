public class Student {// test class
    private  String name;
    private  int year;
    private int course;

    public Student(String name, int year, int course) {
        this.name = name;
        this.year = year;
        this.course = course;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getCourse() {
        return course;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", course=" + course +
                '}';
    }
}
