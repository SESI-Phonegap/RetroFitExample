package android.chris.com.retrofittuto.view.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.chris.com.retrofittuto.R;
import android.chris.com.retrofittuto.data.api.client.TestExampleClient;
import android.chris.com.retrofittuto.data.model.Posts;
import android.chris.com.retrofittuto.interactor.PostsInteractor;
import android.chris.com.retrofittuto.presenter.PostsPresenter;
import android.chris.com.retrofittuto.view.adapter.PostsAdapter;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

public class PostsActivity extends AppCompatActivity implements PostsPresenter.View,View.OnClickListener {

    private Button btn_get;
    private RecyclerView rv_posts;
    private ProgressBar progressBar;
    private ConstraintLayout constraintLayout;

    private PostsPresenter postsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        btn_get =  findViewById(R.id.btn_get);
        btn_get.setOnClickListener(this);
        rv_posts = findViewById(R.id.rv_post);
        constraintLayout = findViewById(R.id.contraintLayout);
        progressBar = findViewById(R.id.progressBar);
        postsPresenter = new PostsPresenter(new PostsInteractor(new TestExampleClient()));
        postsPresenter.setView(this);
        setupRecyclerView();
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        rv_posts.setVisibility(View.INVISIBLE);
        btn_get.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        rv_posts.setVisibility(View.VISIBLE);
        btn_get.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPostNotFoundMessage() {
        rv_posts.setVisibility(View.GONE);
        Toast.makeText(this, "No se encontraron datos",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showConnectionErrorMessage() {
        Toast.makeText(this,"Revisa tu conexión a Internet",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showServerError() {
        Toast.makeText(this,"Ocurrio un error en el servidor",Toast.LENGTH_LONG).show();
    }

    @Override
    public void renderPosts(List<Posts> posts) {
        PostsAdapter adapter = (PostsAdapter) rv_posts.getAdapter();
        Toast.makeText(this,"Numero de registros: "+posts.size(),Toast.LENGTH_LONG).show();
        adapter.setPosts(posts);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_get:
                postsPresenter.onGetAllPostByGet();
                break;
        }
    }

    private void setupRecyclerView(){
        PostsAdapter adapter = new PostsAdapter();
        rv_posts.setLayoutManager(new LinearLayoutManager(this));
        rv_posts.setAdapter(adapter);
        rv_posts.setHasFixedSize(true);
    }

    @Override
    protected void onDestroy() {
        postsPresenter.terminate();
        super.onDestroy();
    }
}
