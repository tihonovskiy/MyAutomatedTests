package API;

import java.util.Objects;

public class ResponsePutGetData {
    String id;
    String employee_name;
    String employee_salary;
    String employee_age;
    String profile_image;

    public ResponsePutGetData(String employee_name, String employee_salary, String employee_age) {
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
    }

    public static boolean equals(ResponsePutGetData responseData, ResponsePutGetData responseData1) {
        return Objects.equals(responseData.employee_name, responseData1.employee_name) &&
                Objects.equals(responseData.employee_salary, responseData1.employee_salary) &&
                Objects.equals(responseData.employee_age, responseData1.employee_age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_name, employee_salary, employee_age);
    }

    @Override
    public String toString() {
        return "ResponsePutData{" +
                "name='" + employee_name + '\'' +
                ", salary='" + employee_salary + '\'' +
                ", age='" + employee_age + '\'' +
                ", id=" + id +
                '}';
    }
}
