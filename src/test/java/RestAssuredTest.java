import API.*;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class RestAssuredTest {
    String postName = "Test";
    String postSalary = "777";
    String postAge = "25";
    String putName = "Test";
    String putSalary = "777";
    String putAge = "25";
    int id;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://dummy.restapiexample.com";
    }

    @Test(priority = 1)
    public void positiveGetAllEmployees() {
        GetResponseData data1 = new GetResponseData("1", "Tiger Nixon", "320800", "61", "");
        GetResponseData data2 = new GetResponseData("2", "Garrett Winters", "170750", "63", "");
        GetResponseData data3 = new GetResponseData("3", "Ashton Cox", "86000", "66", "");
        List<GetResponseData> dataList = new ArrayList<GetResponseData>();
        GetAllResponseModel expectedResponse = new GetAllResponseModel("success", dataList);
        dataList.add(data1);
        dataList.add(data2);
        dataList.add(data3);
        String expectedResult = expectedResponse.toString().substring(0, expectedResponse.toString().length() - 1);
        GetAllResponseModel result = when()
                .request("GET", "/api/v1/employees")
                .then()
                .statusCode(200)
                .extract()
                .as(GetAllResponseModel.class);
        assertTrue(result.toString().contains(expectedResult));
    }

    @Test(priority = 2)
    public void negativeGetAllEmployees() {
        when()
                .request("GET", "/api/v1/employees/1")
                .then()
                .statusCode(404);
    }

    @Test(priority = 3)
    public void positiveGetEmployee() {
        ResponsePutGetData data = new ResponsePutGetData("Tiger Nixon", "320800", "61");
        PutGetResponseModel expectedResponse = new PutGetResponseModel("success", data);
        PutGetResponseModel result =  with()
                .header("Cookie", "PHPSESSID=6e18943eaf715ee5483ccb71cbf1d455; ezoadgid_133674=-1; ezoref_133674=; ezoab_133674=mod92-c; active_template::133674=pub_site.1588187334")
                .when()
                .request("GET", "/api/v1/employee/1")
                .then()
                .statusCode(200)
                .extract()
                .as(PutGetResponseModel.class);
        assertEquals(result, expectedResponse);
    }

    @Test(priority = 4)
    public void negativeGetEmployee() {
        with()
                .header("Cookie", "PHPSESSID=6e18943eaf715ee5483ccb71cbf1d455; ezoadgid_133674=-1; ezoref_133674=; ezoab_133674=mod92-c; active_template::133674=pub_site.1588187334")
                .when()
                .request("GET", "/api/v1/employee/100")
                .then()
                .statusCode(401)
                .body("status", equalTo("failed"))
                .body("data", equalTo("Record does not found."));
    }

    @Test(priority = 5)
    public void positivePostEmployee() {
        PostRequestBodyModel body = new PostRequestBodyModel(postName, postSalary, postAge);
        PostResponseModel result = with()
                .body(body.toString())
                .header("Cookie", "PHPSESSID=6989709da8bc7706604256a35ede41d1; ezoadgid_133674=-1; ezoref_133674=; ezoab_133674=mod1; active_template::133674=pub_site.1588190755")
                .when()
                .request("POST", "/api/v1/create")
                .then()
                .statusCode(200)
                .extract()
                .as(PostResponseModel.class);
        PostResponseModel expectedResult = new PostResponseModel("success", new ResponseData(postName, postSalary, postAge));
        assertEquals(result, expectedResult);
        //id = result.data.id;
    }

    @Test(priority = 6)
    public void negativePostEmployee() {
        PostResponseModel result = with()
                .body("")
                .when()
                .request("POST", "/api/v1/create")
                .then()
                .statusCode(200)
                .extract()
                .as(PostResponseModel.class);
        PostResponseModel expectedResult = new PostResponseModel("success", new ResponseData(null, null, null));
        assertEquals(result, expectedResult);
    }

    @Test(priority = 7)
    public void positivePutEmployee() {
        PutRequestBodyModel body = new PutRequestBodyModel(putName, putSalary, putAge);
        PutGetResponseModel result = with()
                .body(body.toString())
                .header("Content-Type", "application/json")
                .header("Cookie", "PHPSESSID=21f8439a3679ec649e5fa44ce1337004; ezoadgid_133674=-2; ezoref_133674=; ezoab_133674=mod1; active_template::133674=pub_site.1588188647")
                .when()
                .request("PUT", "/api/v1/update/21")
                .then()
                .statusCode(200)
                .extract()
                .as(PutGetResponseModel.class);
        PutGetResponseModel expectedResult = new PutGetResponseModel("success", new ResponsePutGetData(putName, putSalary, putAge));
        assertEquals(result, expectedResult);
    }

    @Test(priority = 8)
    public void negativePutEmployee() {
        PutRequestBodyModel body = new PutRequestBodyModel(putName, putSalary, putAge);
        with()
                .body(body.toString())
                .when()
                .put("/api/v1/update/4")
                .then()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data", equalTo(null));
    }

    @Test(priority = 9)
    public void positiveDeleteEmployee() throws InterruptedException {
        with()
                .header("Cookie", "PHPSESSID=fa0dfd3bb5c21602f157cce9201f13a6; ezoadgid_133674=-1; ezoref_133674=; ezoab_133674=mod92; active_template::133674=pub_site.1588189927")
                .when()
                .request("DELETE", "/api/v1/delete/" + id)
                .then()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("message", equalTo("successfully! deleted Records"));
    }

    @Test(priority = 10)
    public void negativeDeleteEmployee() {
        when()
                .request("DELETE", "/api/v1/delete/100")
                .then()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("failed"))
                .body("message", equalTo("Error! Not able to delete record"));
    }
}
