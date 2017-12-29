package android.chris.com.retrofittuto.data.api.retrofit;


import android.chris.com.retrofittuto.data.api.Constants;
import android.chris.com.retrofittuto.data.model.BreakfastMenu;
import android.chris.com.retrofittuto.data.model.Posts;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

import static android.chris.com.retrofittuto.data.api.Constants.BaseUrl.BASE_URL_AUTH;
import static android.chris.com.retrofittuto.data.api.Constants.BaseUrl.BASE_URL_HEADERS;
import static android.chris.com.retrofittuto.data.api.Constants.BaseUrl.BASE_URL_IPIFY;
import static android.chris.com.retrofittuto.data.api.Constants.CommentsParams.POST_ID;
import static android.chris.com.retrofittuto.data.api.Constants.EndPoint.COMMENTS;
import static android.chris.com.retrofittuto.data.api.Constants.EndPoint.POSTS;
import static android.chris.com.retrofittuto.data.api.Constants.EndPoint.USERS;
import static android.chris.com.retrofittuto.data.api.Constants.OcrImageParams.API_KEY;
import static android.chris.com.retrofittuto.data.api.Constants.OcrImageParams.LANGUAGE;
import static android.chris.com.retrofittuto.data.api.Constants.PostsParams.BODY;
import static android.chris.com.retrofittuto.data.api.Constants.PostsParams.TITLE;
import static android.chris.com.retrofittuto.data.api.Constants.PostsParams.USER_ID;
import static android.chris.com.retrofittuto.data.api.Constants.Users.NAME;

public interface TestExampleRetrofitService {
    @GET(POSTS)
    Observable<List<Posts>> getPosts();

    @GET(USERS)
    Call<ResponseBody> getUsers();

    @POST(USERS)
    Call<ResponseBody> postUser(@Body RequestBody requestBody);

    @POST(POSTS)
    Call<ResponseBody> postPosts(@Body RequestBody requestBody);

    @GET(POSTS)
    Call<ResponseBody> getPostsByUserId(@Query(USER_ID) int userId);

    @GET(POSTS)
    Call<ResponseBody> getPostsByUserIdAndPostId(@Query(USER_ID) int userId, @Query(Constants.PostsParams.ID) int postId);

    @GET(POSTS)
    Call<ResponseBody> getPostsByUserIdAndPostId(@Query(USER_ID) String userId, @Query(Constants.PostsParams.ID) String postId);

    @GET(POSTS)
    Call<ResponseBody> getPostsByIds(@Query(Constants.PostsParams.ID) List<Integer> ids);

    @GET(POSTS)
    Call<ResponseBody> getPostByParams(@QueryMap Map<String,String> params);

    @GET(POSTS + "/{id}")
    Call<ResponseBody> getPostById(@Path(Constants.PostsParams.ID) int id);

    @GET(COMMENTS)
    Call<ResponseBody> getCommentsByPostId(@Query(POST_ID) int postId);

    @GET(BASE_URL_IPIFY)
    Call<ResponseBody> getIp();

    @GET
    Call<ResponseBody> sendRequest(@Url String url);

    @Multipart
    @POST
    Call<ResponseBody> uploadFile(@Url String url, @Part MultipartBody.Part part);

    @Multipart
    @POST
    Call<ResponseBody> uploadImageOcr(@Url String url,
                                      @Part(API_KEY) RequestBody apiKey,
                                      @Part(LANGUAGE) RequestBody language,
                                      @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST(POSTS)
    Call<ResponseBody> postPost(@Field(Constants.PostsParams.ID) String id,
                                @Field(USER_ID) String userId,
                                @Field(TITLE) String title,
                                @Field(BODY) String body);

    @FormUrlEncoded
    @POST(USERS)
    Call<ResponseBody> postUser(@Field(Constants.Users.ID) String id,
                                @Field(NAME) String name);


    @Headers({"Content-Type: application/json", "User-Agent: RetrofitExample"})
    @GET(BASE_URL_HEADERS)
    Call<ResponseBody> sendRequestWhitHeaders();

    @GET(BASE_URL_HEADERS)
    Call<ResponseBody> sendRequestWithHeaders(@Header("Content-Type") String contentType,
                                              @Header("User-Agent") String userAgent);

    @GET(BASE_URL_AUTH)
    Call<ResponseBody> auth(@Header("Authorization") String authorization);

    @GET("https://www.w3schools.com/xml/simple.xml")
    Call<BreakfastMenu> getMenu();
}
