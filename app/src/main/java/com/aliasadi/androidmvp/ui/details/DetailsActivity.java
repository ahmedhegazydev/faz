package com.aliasadi.androidmvp.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aliasadi.androidmvp.data.fanz.PlayerDetails;
import com.aliasadi.androidmvp.ui.base.BaseActivity;
import com.aliasadi.androidmvp.ui.main.PlayersAdapter;
import com.fanz.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsView {

    private static final String EXTRA_PLAYERS = "EXTRA_PLAYERS";

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private PlayersAdapter playersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        playersAdapter = new PlayersAdapter(null);
        recyclerView.setAdapter(playersAdapter);
        playersAdapter.setItems(PlayerDetails.getDummyList());

        presenter.onAttach();
    }

    @Override
    public void showPlayersData(PlayerDetails playerDetails) {
//        title.setText(playerDetails.getTitle());
//        desc.setText(playerDetails.getDescription());
//        Picasso.get().load(playerDetails.getImage()).into(image);
    }

    @Override
    public void showDataUnavailableMessage() {
        Toast.makeText(this, "Data Unavailable", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    protected DetailsPresenter createPresenter() {
        PlayerDetails playerDetails = getIntent().getParcelableExtra(EXTRA_PLAYERS);
        return new DetailsPresenter(this, playerDetails);
    }

    public static void start(Context context, PlayerDetails playerDetails) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_PLAYERS, playerDetails);
        context.startActivity(intent);
    }
}
