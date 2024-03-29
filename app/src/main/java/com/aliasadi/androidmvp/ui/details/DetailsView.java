package com.aliasadi.androidmvp.ui.details;

import com.aliasadi.androidmvp.data.fanz.PlayerDetails;
import com.aliasadi.androidmvp.ui.base.BaseView;

public interface DetailsView extends BaseView {

    void showPlayersData(PlayerDetails playerDetails);

    void showDataUnavailableMessage();

}
