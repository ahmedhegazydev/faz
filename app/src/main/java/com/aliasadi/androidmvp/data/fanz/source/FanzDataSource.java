package com.aliasadi.androidmvp.data.fanz.source;

import android.content.Intent;

import java.util.Map;

public interface FanzDataSource {

    interface LoadRemoteConfigCallback {
        void onRemoteConfigLoaded(Map<String, Boolean> players);
        void onDataNotAvailable();
        void onError();
    }

    interface CreateDynamicLinkCallback {
        void onCreateDynamicLink(String playerId);
        void onError();
    }

    void getRemoteConfigForPlayers(LoadRemoteConfigCallback callback);
    void createDynamicLink(Intent intent, CreateDynamicLinkCallback callback);

}
