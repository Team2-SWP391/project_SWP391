package service;

import Model.Constant;
import Model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class Handler {


//    private String obtainAccessToken(String username, String password) throws Exception {
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "password");
//        params.add("client_id", "fooClientIdPassword");
//        params.add("username", username);
//        params.add("password", password);
//        Resul
//        ResultActions result
//                = mockMvc.perform(post("/oauth/token")
//                        .params(params)
//                        .with(httpBasic("fooClientIdPassword","secret"))
//                        .accept("application/json;charset=UTF-8"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json;charset=UTF-8"));
//
//        String resultString = result.andReturn().getResponse().getContentAsString();
//
//        JacksonJsonParser jsonParser = new JacksonJsonParser();
//        return jsonParser.parseMap(resultString).get("access_token").toString();
//    }
    public String getToken(final String code) throws IOException {
        String response = Request.Post(Constant.GOOGLE_LINK_GET_TOKEN)//submit tới link token với bodyform gồm tham số client_id,... by metohd post
                .connectTimeout(100).bodyForm(Form.form().add("client_id", Constant.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constant.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri",Constant.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constant.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();//execute là submit tới google và trả về 1 respone dạng json

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);//chuyển đổi string dang json sang object
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");//chỉ nhận vào access_token ,other(id_token,scope,expire)

        return accessToken;
    }
    public User getUserInfo(final String accessToken) throws IOException {//user access_token to get user's infor
        String link = Constant.GOOGLE_LINK_GET_USER_INFO + accessToken;//link nhận user's infor by token
        String response = Request.Get(link).execute().returnContent().asString();// submit tới link để nhận về user's infor theo  ở dạng json string
        User user = new Gson().fromJson(response, User.class);//transform json string sang User object
        return user;
    }
}
