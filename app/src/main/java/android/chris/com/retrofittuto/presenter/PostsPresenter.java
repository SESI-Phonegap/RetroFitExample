package android.chris.com.retrofittuto.presenter;



import android.chris.com.retrofittuto.data.model.Posts;
import android.chris.com.retrofittuto.interactor.PostsInteractor;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class PostsPresenter extends Presenter<PostsPresenter.View>{

    private PostsInteractor postsInteractor;

    public PostsPresenter(PostsInteractor interactor){
        this.postsInteractor = interactor;
    }

    public void onGetAllPostByGet(){
        getView().showLoading();
        Disposable disposable = this.postsInteractor.getAllPostByGet().subscribe(posts -> {
            if(!posts.isEmpty() && posts.size() > 0){
                getView().hideLoading();
                getView().renderPosts(posts);
            } else {
                getView().showPostNotFoundMessage();
            }
        }, Throwable::printStackTrace );

        addDisposableObserver(disposable);
    }

    @Override
    public void terminate() {
        super.terminate();
        setView(null);
    }

    public interface View extends Presenter.View{
        void showLoading();

        void hideLoading();

        void showPostNotFoundMessage();

        void showConnectionErrorMessage();

        void showServerError();

        void renderPosts(List<Posts> posts);
    }
}
