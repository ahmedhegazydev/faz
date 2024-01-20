package com.aliasadi.androidmvp.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.aliasadi.androidmvp.data.DataManager;
import com.aliasadi.androidmvp.data.fanz.PlayerDetails;
import com.aliasadi.androidmvp.data.fanz.source.FanzRepository;
import com.aliasadi.androidmvp.ui.base.BaseActivity;
import com.aliasadi.androidmvp.ui.details.DetailsActivity;
import com.fanz.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity<MainPresenter> implements MainView
{

//    adb shell am start \
//    //        -W -a android.intent.action.VIEW \
////        -d https://fanz.com/applinks/ \
////        com.aliasadi.androidmvp.ui.main.MainActivity

    private PlayersAdapter playersAdapter;
    private List<ImageView> playerImageViews;

    @BindView(R.id.player_1)
    ImageView player1;
    @BindView(R.id.player_2)
    ImageView player2;
    @BindView(R.id.player_3)
    ImageView player3;
    @BindView(R.id.player_4)
    ImageView player4;
    @BindView(R.id.player_5)
    ImageView player5;
    @BindView(R.id.player_6)
    ImageView player6;
    @BindView(R.id.player_7)
    ImageView player7;
    @BindView(R.id.player_8)
    ImageView player8;
    @BindView(R.id.player_9)
    ImageView player9;
    @BindView(R.id.player_10)
    ImageView player10;
    @BindView(R.id.player_11)
    ImageView player11;
    @BindView(R.id.player_12)
    ImageView player12;
    @BindView(R.id.player_13)
    ImageView player13;
    @BindView(R.id.player_14)
    ImageView player14;
    @BindView(R.id.player_15)
    ImageView player15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        presenter.onAttach();
        presenter.createDynamicLink(getIntent());


        // Initialize the list and add ImageView references
        initializePlayerImageViewsList();

    }

    private void initializePlayerImageViewsList() {
        playerImageViews = new ArrayList<>();
        playerImageViews.add(player1);
        playerImageViews.add(player2);
        playerImageViews.add(player3);
        playerImageViews.add(player4);
        playerImageViews.add(player5);
        playerImageViews.add(player6);
        playerImageViews.add(player7);
        playerImageViews.add(player8);
        playerImageViews.add(player9);
        playerImageViews.add(player10);
        playerImageViews.add(player11);
        playerImageViews.add(player12);
        playerImageViews.add(player13);
        playerImageViews.add(player14);
        playerImageViews.add(player15);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            playerImageViews.forEach(imageView -> {
                imageView.setOnClickListener(v -> {
                    DetailsActivity.start(this, new PlayerDetails());
                });
            });
        }
    }

    @NonNull
    @Override
    protected MainPresenter createPresenter() {
        FanzRepository fanzRepository = DataManager.getInstance().getPlayersRepository();
        return new MainPresenter(this, fanzRepository);
    }


    @Override
    public void showPlayersFromRemoteConfig(Map<String, Boolean> playerVisibilityMap) {
        setImageViewVisibility(player1, playerVisibilityMap.get("player1"));
        setImageViewVisibility(player2, playerVisibilityMap.get("player2"));
        setImageViewVisibility(player3, playerVisibilityMap.get("player3"));
        setImageViewVisibility(player4, playerVisibilityMap.get("player4"));
        setImageViewVisibility(player5, playerVisibilityMap.get("player5"));
        setImageViewVisibility(player6, playerVisibilityMap.get("player6"));
        setImageViewVisibility(player7, playerVisibilityMap.get("player7"));
        setImageViewVisibility(player8, playerVisibilityMap.get("player8"));
        setImageViewVisibility(player9, playerVisibilityMap.get("player9"));
        setImageViewVisibility(player10, playerVisibilityMap.get("player10"));
        setImageViewVisibility(player11, playerVisibilityMap.get("player11"));
        setImageViewVisibility(player12, playerVisibilityMap.get("player12"));
        setImageViewVisibility(player13, playerVisibilityMap.get("player13"));
        setImageViewVisibility(player14, playerVisibilityMap.get("player14"));
        setImageViewVisibility(player15, playerVisibilityMap.get("player15"));
    }

    @Override
    public void createDynamicLink(String playerId) {
//        DetailsActivity.start(this, new PlayerDetails().setId(playerId));
    }

    private void setImageViewVisibility(ImageView imageView, Boolean isVisible) {
        if (imageView != null) {
            imageView.setVisibility(isVisible != null ? (isVisible ? View.VISIBLE : View.INVISIBLE) : View.VISIBLE);
        }
    }


    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "Server error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showThereIsNoPlayers() {
        Toast.makeText(this, "There is no players!", Toast.LENGTH_SHORT).show();
    }

}
