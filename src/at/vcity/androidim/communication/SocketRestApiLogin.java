package at.vcity.androidim.communication;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Object;

import at.vcity.androidim.domain.domainToken;
import at.vcity.androidim.domain.DomainRealmToken;


public class SocketRestApiLogin {

    String domainAccessUrl = "10.0.2.2:8080";
    static String username = "ari";
    static String password = "ari";
    String grant_type = "password";
    String scope = "read";
    //String url = "http://10.0.2.2:8080/oauth/token";
    static String withClient = "client_storage";
    static String secret = "Victornya123";

    public void tokenOauth2() {
        /*RealmToken realmToken = new RealmToken();
        realmToken.deleteData("12334");
        realmToken.deleteData("123456");
        realmToken.createdData("123456", "45677", "bearer");
        realmToken.updateData("123456", "sssss", "bearer");
        realmToken.readData();*/
        new ReceiveConnection().start();
    }

    //ReceiveConnection
    private class ReceiveConnection extends Thread {
        @Override
        public void run() {
            //cek token and connecting to server
            //data will be saving in domain
            RealmToken realmToken = new RealmToken();
            DomainRealmToken tokenRealm = realmToken.readData();
            int statusConnection = checkConnection(tokenRealm.getToken(),tokenRealm.getBearer() );
            //status not connected
            if (statusConnection == 0 ){
                //get token dan refresh token
                domainToken dToken = getTokenOauth2();
                if (dToken != null  ){
                    realmToken.deleteDataAllToken();
                    realmToken.createdData(dToken.getAccesToken(), dToken.getAccessTokenRefresh(), dToken.getTokeType());
                    System.out.println("ini adalah tokennya " + dToken.getAccesToken());
                    System.out.println("ini adalah type " + dToken.getTokeType());
                    System.out.println("ini adalah token refresh " + dToken.getAccessTokenRefresh());

                    tokenRealm = realmToken.readData();
                    System.out.println("=====================");
                    System.out.println("ini token dari database : " + tokenRealm.getToken());
                    System.out.println("ini adalah bearer database " + tokenRealm.getBearer());
                    System.out.println("ini adalah token refresh database " + tokenRealm.getRefresh_token());
                }
                else{
                    System.out.println("tidak data");
                }
            }
            //status connected
            else{

            }
        }
    }


    private int checkConnection(String token, String bearer){
        /*
        Request
        curl "client_storage:victornya@localhost:8080/openyoureye/oauth/token" -X POST -d "grant_type=client_credentials&scope=read&username=ari&password=ari"
        curl -X GET -H "Authorization: bearer a560c017-1256-42a1-9af6-0f0a656a7661" localhost:8080/api/testConnection

        Json Result : { "testConnection" : 1 }
         */
        try{
            String urlTestConnection = "http://"+domainAccessUrl+"/api/testConnection";
            String method = "GET";

            HashMap<String, Object> header = new HashMap();
            header.put("Authorization", " " + bearer + " " + token );
            HttpResponse responseNya = makeServiceCallWtihParam(urlTestConnection, method, null, header);

            HttpEntity result = responseNya.getEntity();
            String reponseResult = EntityUtils.toString(result);
            System.out.println(reponseResult);
            JSONObject jsonObject = new JSONObject(reponseResult);
            System.out.println(jsonObject.getString("testConnection"));
            return Integer.valueOf(jsonObject.getString("testConnection"));
        }
        catch (Exception x){
            System.out.println(x.toString());
            x.printStackTrace();
            //System.out.println(x.printStackTrace());
            Log.e("testConnection ", x.getMessage() + x.getLocalizedMessage());
            return 0;
        }
    }

    /*9-
    how to used :
    url             = domain.com/list_url
    method          = "POST" or "GET"
    paramsData      = --d or data
                    Example :
                    List<NameValuePair> paramsData = new ArrayList<NameValuePair>();
                    paramsData.add(new BasicNameValuePair("name", "ari prasetiyo 2"));
    hashmapHeader   = --header
                    Example :
                    HashMap<String, Object> headerNya = new HashMap<String, Object>();
                    List listData = new ArrayList();
                    listData.add("JSESSIONID=" + getJSessionId() );
                    listData.add("XSRF-TOKEN=" + getXSrfToken());
                    headerNya.put("cookie", listData);
                    or
                    headerNya.put("cookie", "data");
                    HttpResponse httpResponse = makeServiceCallWtihParam(AUTHENTICATION_SERVER_ADDRESS, "POST", params, headerNya);
     */
    private HttpResponse makeServiceCallWtihParam(String url, String method, List<NameValuePair> paramsData, HashMap<String, Object> hashmapHeader) {
        HttpResponse httpResponse = null;
        try {
            // http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            // Checking http request method type
            if (method == "POST") {
                HttpPost httpPost = new HttpPost(url);
                //this below code inspiration from  httpPost.addHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
                //adding header
                if(hashmapHeader != null && !hashmapHeader.isEmpty()){
                    int loopMap = hashmapHeader.size();
                    for(String key : hashmapHeader.keySet()){
                        try{
                            List data =  (List)  hashmapHeader.get(key);
                            for(Object loopList :data){
                                httpPost.addHeader(key, loopList.toString());
                                //System.out.println(key + " :" +loopList.toString()+"xxx1");
                            }
                        }
                        catch (Exception x ){
                            httpPost.addHeader(key, (String) hashmapHeader.get(key));
                            //System.out.println(key + " :" +(String) hashmapHeader.get(key)+ "xxx2");
                        }
                    }
                }
                // adding post params
                //databody
                if (paramsData != null) {
                    //httpPost.setEntity(new UrlEncodedFormEntity(paramsData));
                    httpPost.setEntity(new UrlEncodedFormEntity(paramsData,"UTF-8"));
                }
                httpResponse = httpClient.execute(httpPost);
            } else if (method == "GET") {
                // appending params to url
                if (paramsData != null) {
                    String paramString = URLEncodedUtils
                            .format(paramsData, "UTF-8");
                    url += "?" + paramString;
                }
                HttpGet httpGet = new HttpGet(url);
                //adding header
                if(hashmapHeader != null || !hashmapHeader.isEmpty()){
                    //int loopMap = hashmapHeader.size();
                    for(String key : hashmapHeader.keySet()){
                        try{
                            List data =  (List)  hashmapHeader.get(key);
                            for(Object loopList :data){
                                httpGet.addHeader(key, loopList.toString());
                                System.out.println(key + " :" + loopList.toString() + "xxx1");
                            }
                        }
                        catch (Exception x ){
                            httpGet.addHeader(key, (String) hashmapHeader.get(key));
                            System.out.println(key + " :" + (String) hashmapHeader.get(key) + "xxx2");
                        }
                    }
                }
                httpResponse = httpClient.execute(httpGet);
            }
            return httpResponse;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }

    //currently while not used
    private domainToken getRefreshToken(String tokenRefresh){
         /*
        Request
        curl localhost:8080/openyoureye/oauth/token -X POST -d "grant_type=refresh_token&refresh_token=52dd1078-0710-46d3-abbd-6b85ac6e687b" -u client_storage:victornya
         */
        try{
            String authentication = withClient+ ":" + secret;
            String url = "http://"+authentication+"@"+domainAccessUrl+"/openyoureye/oauth/token";
            String method = "POST";

            ArrayList<NameValuePair> paramsData = new ArrayList<NameValuePair>();
            paramsData.add(new BasicNameValuePair("grant_type","refresh_token"));
            paramsData.add(new BasicNameValuePair("refresh_token",tokenRefresh));
            paramsData.add(new BasicNameValuePair("client_id", withClient));
            paramsData.add(new BasicNameValuePair("client_secret", secret));

            HashMap<String, Object> headerNya = new HashMap<String, Object>();
            headerNya.put("Content-Type", "application/x-www-form-urlencoded");

            HttpResponse responseNya = makeServiceCallWtihParam(url, method, paramsData, headerNya);

            HttpEntity result = responseNya.getEntity();
            String reponseResult = EntityUtils.toString(result);
            return JsonConvertToToken(reponseResult);
        }
        catch (Exception x){
            x.printStackTrace();
            //System.out.println(x.printStackTrace());
            Log.e("getRefreshToken ", x.getMessage() + x.getLocalizedMessage());
            return null;
        }
    }

    /*
    Request :
	curl "localhost:8080/openyoureye/oauth/token" -X POST -d "client_id=client_storage&client_secret=victornya&grant_type=password&scope=read&username=ari&password=ari"
	curl "client_storage:victornya@localhost:8080/openyoureye/oauth/token" -X POST -d "grant_type=client_credentials&scope=read&username=ari&password=ari"
	*/
    private domainToken getTokenOauth2(){

        String authentication = withClient+ ":" + secret;
        String url = "http://"+authentication+"@"+domainAccessUrl+"/openyoureye/oauth/token";

        //gettoken();
   		String methodNya = "POST";

   		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("client_id", withClient));
        params.add(new BasicNameValuePair("client_secret", secret));
        params.add(new BasicNameValuePair("grant_type", grant_type));
        params.add(new BasicNameValuePair("scope", scope));
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));

        //byte[] byteArray = params.toString().getBytes("UTF-8");
        HashMap<String, Object> headerNya = new HashMap<String, Object>();
        headerNya.put("Content-Type", "application/x-www-form-urlencoded");
        //headerNya.put("Content-Length", "" + byteArray.length);

        HttpResponse resultResponse =  makeServiceCallWtihParam(url, methodNya, params, headerNya);

        HttpEntity result = resultResponse.getEntity();
        try {
            String response = EntityUtils.toString(result);
            System.out.println(response + " test 1 ");
            return JsonConvertToToken(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private domainToken JsonConvertToToken(String response) {
        domainToken dToken = new domainToken();
        try{
            JSONObject objectJson = new JSONObject(response);
            dToken.setAccesToken(objectJson.getString("access_token"));
            dToken.setTokeType(objectJson.getString("token_type"));
            //dToken.setAccessTokenRefresh(objectJson.getString("refresh_token"));
            dToken.setExpiresIn(objectJson.getString("expires_in"));
            dToken.setScope(objectJson.getString("scope"));
            return dToken;
        }
        catch (JSONException x){
            x.getMessage();
            Log.e("JSONStr", x.toString());
        }
        return null;
    }
}
