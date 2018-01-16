package android.chris.com.retrofittuto.view.adapter;


import android.chris.com.retrofittuto.R;
import android.chris.com.retrofittuto.data.model.Posts;
import android.chris.com.retrofittuto.presenter.PostsPresenter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    private List<Posts> posts;

    public PostsAdapter(){
        posts = Collections.emptyList();
    }
    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row_posts, parent, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Posts post = posts.get(position);
        holder.post = post;
        holder.tvId.setText(String.valueOf(post.getId()));
        holder.tvUserId.setText(String.valueOf(post.getUserId()));
        holder.tvTitle.setText(post.getTitle());
        holder.tvBody.setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Posts> posts){
        this.posts = posts;
    }
    public static class PostViewHolder extends RecyclerView.ViewHolder{
        TextView tvId;
        TextView tvUserId;
        TextView tvTitle;
        TextView tvBody;

        View view;
        Posts post;
        public PostViewHolder(View itemView){
            super(itemView);
            tvId = itemView.findViewById(R.id.tvid);
            tvUserId = itemView.findViewById(R.id.tvuserId);
            tvTitle = itemView.findViewById(R.id.tvtitle);
            tvBody = itemView.findViewById(R.id.tvbody);
            view = itemView;
        }
    }
}
