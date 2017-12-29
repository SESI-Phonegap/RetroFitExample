package android.chris.com.retrofittuto.data.api.client;


import android.chris.com.retrofittuto.data.api.retrofit.TestExampleRetrofitClient;
import android.chris.com.retrofittuto.data.model.Posts;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class TestExampleClient extends TestExampleRetrofitClient implements TestExampleService {

    @Override
    public Observable<List<Posts>> getAllPostByGet() {
        return getTestExampleService().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
