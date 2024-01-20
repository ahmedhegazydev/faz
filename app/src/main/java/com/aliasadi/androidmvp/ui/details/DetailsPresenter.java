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
        showPlayerDetailsData();
    }

    private void showPlayerDetailsData() {
        if (playerDetails != null) {
            view.showPlayersData(playerDetails);
        } else {
            view.showDataUnavailableMessage();
        }
    }
}
