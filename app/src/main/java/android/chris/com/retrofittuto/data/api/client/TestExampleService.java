package android.chris.com.retrofittuto.data.api.client;

import android.chris.com.retrofittuto.data.model.Posts;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface TestExampleService {

    Observable<List<Posts>> getAllPostByGet();
}
