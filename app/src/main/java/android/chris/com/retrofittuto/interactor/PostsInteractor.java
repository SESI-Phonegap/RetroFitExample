package android.chris.com.retrofittuto.interactor;


import android.chris.com.retrofittuto.data.api.client.TestExampleService;
import android.chris.com.retrofittuto.data.model.Posts;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class PostsInteractor {

    private TestExampleService testExampleService;

    public PostsInteractor(TestExampleService testExampleService){
        this.testExampleService = testExampleService;
    }

    public Observable<List<Posts>> getAllPostByGet(){
        return this.testExampleService.getAllPostByGet();
    }
}
