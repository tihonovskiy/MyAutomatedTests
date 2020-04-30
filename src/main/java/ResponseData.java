import java.util.Objects;

public class ResponseData {
    String name;
    String salary;
    String age;
    int id;

    public ResponseData(String name, String salary, String age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public static boolean equals(ResponseData responseData, ResponseData responseData1) {
        return  Objects.equals(responseData.name, responseData1.name) &&
                Objects.equals(responseData.salary, responseData1.salary) &&
                Objects.equals(responseData.age, responseData1.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, age);
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                ", age='" + age + '\'' +
                ", id=" + id +
                '}';
    }
}
