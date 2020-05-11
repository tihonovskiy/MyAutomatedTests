package API;

import java.util.Objects;

public class GetResponseData {
    String id;
    String employee_name;
    String employee_salary;
    String employee_age;
    String profile_image;

    public GetResponseData(String id, String employee_name, String employee_salary, String employee_age, String profile_image) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }

    @Override
    public String toString() {
        return "API.GetResponseData{" +
                "id='" + id + '\'' +
                ", employee_name='" + employee_name + '\'' +
                ", employee_salary='" + employee_salary + '\'' +
                ", employee_age='" + employee_age + '\'' +
                ", profile_image='" + profile_image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetResponseData that = (GetResponseData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(employee_name, that.employee_name) &&
                Objects.equals(employee_salary, that.employee_salary) &&
                Objects.equals(employee_age, that.employee_age) &&
                Objects.equals(profile_image, that.profile_image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee_name, employee_salary, employee_age, profile_image);
    }
}
