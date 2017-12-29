package android.chris.com.retrofittuto.data.api.retrofit;


import android.chris.com.retrofittuto.data.api.retrofit.deserializer.PostsDeserializer;
import android.chris.com.retrofittuto.data.model.Posts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import static android.chris.com.retrofittuto.data.api.Constants.BaseUrl.BASE_URL_EXAMPLE;

public abstract class TestExampleRetrofitClient {

    private TestExampleRetrofitService testExampleRetrofitService;

    public TestExampleRetrofitClient(){
        initRetrofit();
    }

    private void initRetrofit(){
        Retrofit retrofit = retrofitBuilder();
        this.testExampleRetrofitService = retrofit.create(getTestExampleServiceClass());
    }

    private Retrofit retrofitBuilder(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_EXAMPLE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(getTestExampleDeserializer()))
                .client(getOkHttpClient())
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        client.addInterceptor(loggingInterceptor);
        return client.build();
    }

    private Class<TestExampleRetrofitService> getTestExampleServiceClass() {
        return TestExampleRetrofitService.class;
    }

    private Gson getTestExampleDeserializer(){
        return new GsonBuilder().registerTypeAdapter(new TypeToken<List<Posts>>(){}.getType(), new PostsDeserializer<Posts>()).create();
    }

    protected TestExampleRetrofitService getTestExampleService(){
        return this.testExampleRetrofitService;
    }
}
