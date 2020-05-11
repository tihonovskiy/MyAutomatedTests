package API;

import java.util.List;
import java.util.Objects;

public class GetAllResponseModel {
    String status;
    List<GetResponseData> data;

    public GetAllResponseModel(String status, List<GetResponseData> data) {
        this.status = status;
        this.data = data;
    }

    @Override
    public String toString() {
        return "status='" + status + '\'' + ", data=" + data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetAllResponseModel that = (GetAllResponseModel) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, data);
    }
}
