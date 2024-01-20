package com.aliasadi.androidmvp.data;

import com.aliasadi.androidmvp.data.fanz.source.FanzRepository;
import com.aliasadi.androidmvp.data.fanz.source.remote.FanzRemoteDataSource;
import com.aliasadi.androidmvp.data.fanz.source.remote.services.FanZApi;
import com.aliasadi.androidmvp.data.fanz.source.remote.services.FanzService;
import com.preference.PowerPreference;
import com.preference.Preference;

public class DataManager {

    private static DataManager sInstance;

    private DataManager() {
        // This class is not publicly instantiable
    }

    public static synchronized DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }

    public Preference getDefaultPreference() {
        return PowerPreference.getDefaultFile();
    }

    public Preference getUserPreference() { return PowerPreference.getFileByName("UserPreference"); }

    public FanzRepository getPlayersRepository() {

        FanZApi fanZApi = FanzService.getInstance().getFanzApi();
        FanzRemoteDataSource fanZRemote = FanzRemoteDataSource.getInstance(fanZApi);


        return FanzRepository.getInstance(fanZRemote
        );
    }

}
