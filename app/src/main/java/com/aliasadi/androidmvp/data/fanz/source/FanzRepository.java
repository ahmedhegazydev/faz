package com.aliasadi.androidmvp.data.fanz.source;

import android.content.Intent;

import com.aliasadi.androidmvp.data.fanz.source.remote.FanzRemoteDataSource;

import java.util.Map;

public class FanzRepository implements FanzDataSource {

    private final FanzDataSource fanzDataSource;

    private static FanzRepository instance;

    private FanzRepository(FanzRemoteDataSource fanzDataSource
    ) {

        this.fanzDataSource = fanzDataSource;

    }

    public static FanzRepository getInstance(FanzRemoteDataSource fanzRemoteDataSource
    ) {
        if (instance == null) {
            instance = new FanzRepository(fanzRemoteDataSource
            );
        }
        return instance;
    }

    @Override
    public void getRemoteConfigForPlayers(final LoadRemoteConfigCallback callback) {
        if (callback == null) return;

        fanzDataSource.getRemoteConfigForPlayers(new LoadRemoteConfigCallback() {

            @Override
            public void onRemoteConfigLoaded(Map<String, Boolean> players) {
                callback.onRemoteConfigLoaded(players);
            }

            @Override
            public void onDataNotAvailable() {
                getMRemoteConfigFromLocalDataSource(callback);
                callback.onDataNotAvailable();

            }

            @Override
            public void onError() {
                //not implemented in cache data source
                callback.onError();
            }
        });



    }

    @Override
    public void createDynamicLink(Intent intent, CreateDynamicLinkCallback callback) {
        if (callback == null) return;

        fanzDataSource.createDynamicLink(intent, new CreateDynamicLinkCallback() {

            @Override
            public void onCreateDynamicLink(String playerId) {
                callback.onCreateDynamicLink(intent.getStringExtra("playerId"));
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }


    private void getMRemoteConfigFromLocalDataSource(final LoadRemoteConfigCallback callback) {
        fanzDataSource.getRemoteConfigForPlayers(new LoadRemoteConfigCallback() {
            @Override
            public void onRemoteConfigLoaded(Map<String, Boolean> players) {
                callback.onRemoteConfigLoaded(players);
            }

            @Override
            public void onDataNotAvailable() {
                getRemoteConfigForPlayers(callback);
            }

            @Override
            public void onError() {
                //not implemented in local data source
            }
        });
    }

    public void destroyInstance() {
        instance = null;
    }
}
