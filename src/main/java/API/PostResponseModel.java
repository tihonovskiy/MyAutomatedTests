package API;

import java.util.Objects;

public class PostResponseModel {
    String status;
    ResponseData data;

    public PostResponseModel(String status, ResponseData data) {
        this.status = status;
        this.data = data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostResponseModel that = (PostResponseModel) o;
        return Objects.equals(status, that.status) &&
                ResponseData.equals(data, that.data);
    }

    @Override
    public String toString() {
        return "API.PostResponseModel{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
