import java.util.Objects;

public class PutGetResponseModel {
    String status;
    ResponsePutGetData data;

    public PutGetResponseModel(String status, ResponsePutGetData data) {
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
        PutGetResponseModel that = (PutGetResponseModel) o;
        return Objects.equals(status, that.status) &&
                ResponsePutGetData.equals(data, that.data);
    }

    @Override
    public String toString() {
        return "PutResponseModel{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
