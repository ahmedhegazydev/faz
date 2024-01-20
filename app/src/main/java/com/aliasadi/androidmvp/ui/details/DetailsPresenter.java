package com.aliasadi.androidmvp.ui.details;

import com.aliasadi.androidmvp.data.fanz.PlayerDetails;
import com.aliasadi.androidmvp.ui.base.BasePresenter;


public class DetailsPresenter extends BasePresenter<DetailsView> {

    private final PlayerDetails playerDetails;

    DetailsPresenter(DetailsView view, PlayerDetails playerDetails) {
        super(view);
        this.playerDetails = playerDetails;
    }

    public void onAttach() {
        showMovieData();
    }

    private void showMovieData() {
        if (playerDetails != null) {
            view.showMovieData(playerDetails);
        } else {
            view.showDataUnavailableMessage();
        }
    }
}
