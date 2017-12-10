package android.chris.com.retrofittuto;

import android.chris.com.retrofittuto.Model.Users;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static final String OCR_URL = "https://api.ocr.space/parse/image";
    public static final String API_KEY = "34eab8418788957";
    public static final String LANGUAGE = "eng";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();

        Users userPost = new Users();
        userPost.setId(100);

        Log.i("GSON--- ",gson.toJson(userPost));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();

        Api api = retrofit.create(Api.class);

        String userName = "myusername";
        String password = "mypassword";
        String userAndPassword = userName + ":" + password;
        String authorization = "Basic " + Base64.encodeToString(userAndPassword.getBytes(), Base64.NO_WRAP);

        api.auth(authorization).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    Log.d("GET auth--",response.body().string());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

       /* String contentType = "application/json";
        String userAgent = "RetrofitExample";
        api.sendRequestWithHeaders(contentType,userAgent).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    Log.d("GET sendRequestWhitHeaders--",response.body().string());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/


     /*   api.sendRequestWhitHeaders().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    Log.d("GET sendRequestWhitHeaders--",response.body().string());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/

        /*String id = "501";
        String name = "Chris";

        api.postUser(id,name).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    Log.d("POST postPost--",response.body().string());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
*/
       /* String id = "1501";
        String userId = "2";
        String title = "This is title";
        String body = "This is body";

        api.postPost(id,userId,title,body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    Log.d("POST postPost--",response.body().string());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
*/

      /*  InputStream input = null;
        try {
            input = getAssets().open("image.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(getCacheDir(), "image.png");

        try {

            OutputStream output = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int read;

            while ((read = input.read(buffer)) != -1){
                output.write(buffer,0,read);
            }
            output.flush();
            output.close();
            input.close();

            RequestBody rbApiKey = RequestBody.create(MediaType.parse("multipart/form-data"), API_KEY);
            RequestBody requestBodyLanguage = RequestBody.create(MediaType.parse("multipart/form-data"),LANGUAGE);
            RequestBody requestBodyImage = RequestBody.create(MediaType.parse("multipart/form-data"),file);
            MultipartBody.Part partImage = MultipartBody.Part.createFormData("Image",file.getName(),requestBodyImage);
            api.uploadImageOcr(OCR_URL,rbApiKey,requestBodyLanguage,partImage).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try{
                        Log.d("POST uploadImageOcr img--",response.body().string());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        } catch (IOException e){
            e.printStackTrace();
        }*/
/*

        try {
            File file = new File(getCacheDir(), "hello.txt");
            FileWriter writer = new FileWriter(file);
            writer.append("Hello");
            writer.flush();
            writer.close();

            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(),requestBody);

            api.uploadFile("https://file.io",part).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try{
                        Log.d("POST uploadFile txt--",response.body().string());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        } catch (IOException e){
            e.printStackTrace();
        }
*/

        /*api.sendRequest("https://api.ipify.org").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    Log.d("GET sendRequest URL--",response.body().string());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/


      /*  api.getPostById(1).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    Log.d("GET getPostById PATH--",response.body().string());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/

        /*Map<String,String> mapParams = new HashMap<>();
        mapParams.put("userId","1");
        mapParams.put("id","2");

        api.getPostByParams(mapParams).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    Log.d("GET getPostByParams--",response.body().string());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/


       /* List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        api.getPostsByIds(ids).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d("GET getPostsByIds--",response.body().string());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/

      /*  api.getPostsByUserIdAndPostId("1","1").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    Log.d("GET getPostsByUserIdAndPostId--",response.body().string());
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/

       /* api.getCommentsByPostId(1).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    Log.d("GET getCommentsByPostId--", response.body().string());
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/

       /* api.getPostsByUserId(1).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    Log.d("GET getPostsByUserId--",response.body().string());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/

       /* RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),gson.toJson(userPost));
        api.postUser(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    Log.d("Respuesta--- ", response.body().string());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                t.printStackTrace();
            }
        });*/
    }
}
