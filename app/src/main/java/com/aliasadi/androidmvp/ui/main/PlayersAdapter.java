package com.aliasadi.androidmvp.ui.main;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.aliasadi.androidmvp.data.fanz.PlayerDetails;
import com.fanz.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.MovieViewHolder> {

    public interface MovieListener {
        void onMovieClicked(PlayerDetails playerDetails);
    }

    private List<PlayerDetails> items;
    private MovieListener listener;

    public PlayersAdapter(MovieListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    public void setItems(List<PlayerDetails> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private PlayerDetails getItem(int position) {
        return items.get(position);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image) AppCompatImageView image;
        @BindView(R.id.title) TextView title;
        @BindView(R.id.desc) TextView desc;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            PlayerDetails playerDetails = getItem(position);

            setClickListener(playerDetails);
            setTitle(playerDetails.getTitle());
            setImage(playerDetails.getImage());
            setDescription(playerDetails.getDescription());
        }

        private void setTitle(String title) {
            this.title.setText(title);
        }

        private void setImage(String imageUrl) {
            Picasso.get().load(imageUrl).into(image);
        }

        private void setDescription(String description) {
            desc.setText(description);
        }

        private void setClickListener(PlayerDetails playerDetails) {
            itemView.setTag(playerDetails);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onMovieClicked((PlayerDetails) view.getTag());
        }
    }
}
