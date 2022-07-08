import db.dao.DbDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
public class TestMain {
    DbDao dao = new DbDao();

    @ParameterizedTest
    @ValueSource(strings = {"hoanganh-hoanganh2", "hoanganh1-hoanganh", "hoanganh3-hoanganh2"})
    public void testloginPass(String tcase) {
        assertNull(dao.GetUsertest(tcase.split("-")[0].trim(), tcase.split("-")[1].trim()));
    }
    @ParameterizedTest
    @ValueSource(strings = {"hoanganh-hoanganh-hoanganh2", "hoanganh-hoanganh1-hoanganh", "hoanganh-hoanganh3-hoanganh", " - -hoanganh"})
    public void testloginFail(String tcase) {
        assertEquals(tcase.split("-")[0], dao.GetUsertest(tcase.split("-")[1], tcase.split("-")[2]));
    }
    @ParameterizedTest
    @ValueSource(strings = {"- ", "-hoanganh", "user- "})
    public void loginvalidatenull(String tcase) {
        assertEquals("", dao.validate(tcase.split("-")[0], tcase.split("-")[1]));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "0"})
    public void getFailUserbyId(String tcase) {
        assertEquals(Integer.parseInt(tcase), dao.getUserByid(78));
    }

    @Test
    public void getAllFeedBackfail() {
        assertNull(dao.getAllFeedBack());
    }

    @Test
    public void getAllFeedBacksuccess() {
        assertNotNull(dao.getAllFeedBack());
    }

    @ParameterizedTest
    @ValueSource(strings = {"minsaler-SALE", "hoanganh-USER", "admin-ADMIN"})
    public void getrole(String tcase) {
        assertEquals(tcase.split("-")[1], dao.getRole(tcase.split("-")[0]));
    }

    @ParameterizedTest
    @ValueSource(strings = {"minsaler-1005", "hoanganh-3", "admin-1"})
    public void getidByaccount(String tcase) {
        assertEquals(Integer.parseInt(tcase.split("-")[1]), dao.getidaccount(tcase.split("-")[0]));
    }

    @ParameterizedTest
    @ValueSource(strings = {"anhh34711@gmail.com-true", "anhnhhe153607@fpt.edu.vn-true", "anhnhhe153607@f-false"})
    public void confirm_mail(String tcase) {
        assertEquals(tcase.split("-")[1], dao.confirm_mail(tcase.split("-")[0]) + "");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1009-1", "1012-1", "1000-0", "1001-0"})
    public void selectsalerbyid(String tcase) {
        assertEquals(tcase.split("-")[1], dao.getSalerByid(Integer.parseInt(tcase.split("-")[0])) + "");
    }

    @ParameterizedTest
    @ValueSource(strings = {"anhnh@fpt-0123456-phutho-hoang anh", " -01234568457-phutho-hoang anh", "anhnh@fpt-0123456977788-phutho,hoav-hoang anh",
            "anhnh@fpt-0123456-phutho-hoang anh", "anhnh@fpt-0123456-phutho,hoavi,manlinh- ", "anhnh@fpt-0123456- - nguyen hoang anh"})
    public void checkUpdateuser_invalid(String tcase) {
        assertEquals(false, dao.validateUserInfor(tcase.split("-")[0], tcase.split("-")[1],
                tcase.split("-")[2], tcase.split("-")[3]));
    }

    @ParameterizedTest
    @ValueSource(strings = {"anhh34711@gmail.com-0123456789-phu tho,thanh thuy,tu vu-nguyen hoang anh"})
    public void checkUpdateuser_valid(String tcase) {
        assertEquals(true, dao.validateUserInfor(tcase.split("-")[0], tcase.split("-")[1],
                tcase.split("-")[2], tcase.split("-")[3]));
    }

    @Test
    public void checkUpdateuser_nullAddress() {
        assertEquals(false, dao.validateUserInfor("anh@mfhgkkkd.com", "0127568493", null, "hoangnah"));
    }

    @Test
    public void checkUpdateuser_nullPhone() {
        assertEquals(false, dao.validateUserInfor("anh@mfhgkkkd.com", null, "anh,anh,anh", "hoang anh"));
    }

    @Test
    public void checkUpdateuser_nullName() {
        assertEquals(false, dao.validateUserInfor("anh@mfhgkkkd.com", "0127568493", "anh,anh,anh", null));
    }

    @Test
    public void checkUpdateuser_nullemail() {
        assertEquals(false, dao.validateUserInfor(null, "0127568493", "anh,anh,anh", "hoang anh"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0123456789-phu tho-nguyen hoang anh"})
    public void checkSaler_valid(String tcase) {
        assertEquals(true, dao.validateSaler(tcase.split("-")[0], tcase.split("-")[1],
                tcase.split("-")[2]));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0123456789-phu tho- ", " -phu tho-nguyen hoang anh", "0123456789- -nguyen hoang anh", " - - "})
    public void checkSaler_invalid(String tcase) {
        assertEquals(false, dao.validateSaler(tcase.split("-")[0], tcase.split("-")[1],
                tcase.split("-")[2]));
    }

    @Test
    public void checkSaler_nullPhone() {
        assertEquals(false, dao.validateSaler(null, "phutho", "hoang anh"));
    }

    @Test
    public void checkSaler_nullName() {
        assertEquals(false, dao.validateSaler("0123467898", "phutho", null));
    }

    @Test
    public void checkSaler_nulleaddress() {
        assertEquals(false, dao.validateSaler("0123467898", null, "hoang anh"));
    }
    @ParameterizedTest
    @ValueSource(strings = {"hoanganh"})
    public void checkSaler_validpassword(String tcase) {
        assertEquals(true, dao.validateSalerPassword(tcase));
    }
    @ParameterizedTest
    @ValueSource(strings = {"hoang",""})
    public void checkSaler_Invalidpassword(String tcase) {
        assertEquals(false, dao.validateSalerPassword(tcase));
    }
    @Test
    public void checkSaler_Nullpassword() {
        assertEquals(false, dao.validateSalerPassword(null));
    }
}
