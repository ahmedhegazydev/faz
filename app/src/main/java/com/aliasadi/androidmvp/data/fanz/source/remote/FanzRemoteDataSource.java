package com.aliasadi.androidmvp.data.fanz.source.remote;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.aliasadi.androidmvp.data.fanz.source.FanzDataSource;
import com.aliasadi.androidmvp.data.fanz.source.remote.services.FanZApi;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.remoteconfig.ConfigUpdate;
import com.google.firebase.remoteconfig.ConfigUpdateListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.HashMap;
import java.util.Map;

public class FanzRemoteDataSource implements FanzDataSource {

    private static FanzRemoteDataSource instance;

    private final FanZApi fanZApi;
    private final FirebaseRemoteConfig mFirebaseRemoteConfig;

    private FanzRemoteDataSource(FanZApi fanZApi) {
        this.fanZApi = fanZApi;

        // Initialize Firebase Remote Config
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);

    }

    public static FanzRemoteDataSource getInstance(FanZApi fanZApi) {
        if (instance == null) {
            instance = new FanzRemoteDataSource(fanZApi);
        }
        return instance;
    }

    @Override
    public void getRemoteConfigForPlayers(final LoadMoviesCallback callback) {
        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        mFirebaseRemoteConfig.activate();
                        Map<String, Boolean> playerVisibilityMap = new HashMap<>();

                        for (int i = 1; i <= 15; i++) {
                            String key = "player" + i;
                            boolean isPlayerVisible = mFirebaseRemoteConfig.getBoolean(key);
                            playerVisibilityMap.put(key, isPlayerVisible);
                        }

                        boolean isPlayer1Visible = playerVisibilityMap.get("player1");
                        boolean isPlayer2Visible = playerVisibilityMap.get("player2");

                        callback.onRemoteConfigLoaded(playerVisibilityMap);
                    } else {
                        // Failed to fetch data
                        // Handle the error
                        callback.onError();
                    }
                });

        mFirebaseRemoteConfig.addOnConfigUpdateListener(new ConfigUpdateListener() {
            @Override
            public void onUpdate(ConfigUpdate configUpdate) {
                Log.d("TAG", "Updated keys: " + configUpdate.getUpdatedKeys());

                mFirebaseRemoteConfig.activate().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Map<String, Boolean> playerVisibilityMap = new HashMap<>();

                        for (int i = 1; i <= 15; i++) {
                            String key = "player" + i;
                            boolean isPlayerVisible = mFirebaseRemoteConfig.getBoolean(key);
                            playerVisibilityMap.put(key, isPlayerVisible);
                        }

                        boolean isPlayer1Visible = playerVisibilityMap.get("player1");
                        boolean isPlayer2Visible = playerVisibilityMap.get("player2");

                        callback.onRemoteConfigLoaded(playerVisibilityMap);
                    } else {
                        // Failed to fetch data
                        // Handle the error
                        callback.onError();
                    }
                });
            }

            @Override
            public void onError(@NonNull FirebaseRemoteConfigException e) {
                // Handle the error
            }
        });


    }
            @Override
    public void createDynamicLink(Intent intent, CreateDynamicLinkCallback callback) {
        FirebaseDynamicLinks.getInstance().getDynamicLink(intent).addOnSuccessListener(pendingDynamicLinkData -> {
            if (pendingDynamicLinkData != null) {
                Uri deepLink = pendingDynamicLinkData.getLink();
                String deepLinkString = deepLink.toString();
                Log.e("TAG", "deepLinkString: " + deepLinkString);
                if (deepLink != null) {
                    String playerId = deepLink.getQueryParameter("playerId");
                    callback.onCreateDynamicLink(playerId);
                }
            }else {
                callback.onCreateDynamicLink("");
            }
        }).addOnFailureListener(e -> {
            callback.onError();
            e.printStackTrace();
        });
    }

}
