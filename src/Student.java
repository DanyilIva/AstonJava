public class Student {
    String name;
    String group;
    int course;
    double averageMark;

    public Student (String name, String group, int course, double averageMark) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.averageMark = averageMark;
    }

    public String getName() {
        return name;
    }
    public String getGroup() {
        return group;
    }
    public int getCourse() {
        return course;
    }
    public double getAverageMark() {
        return averageMark;
    }

    public void nextCourse() {
        course++;
    }

    @Override
    public String toString() {
        return "Студент: " + name + ", группа: " + group + ", курс: " + course + ", средняя оценка: " + averageMark;
    }
}
