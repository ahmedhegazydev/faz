package com.aliasadi.androidmvp.ui.main;

import com.aliasadi.androidmvp.ui.base.BaseView;

import java.util.Map;

public interface MainView extends BaseView {
void showPlayersFromRemoteConfig(Map<String, Boolean> player);
    void createDynamicLink(String playerId);

    void showErrorMessage();

    void showThereIsNoMovies();
}
