package com.aliasadi.androidmvp.ui.main;

import android.content.Intent;

import com.aliasadi.androidmvp.data.fanz.source.FanzDataSource;
import com.aliasadi.androidmvp.data.fanz.source.FanzRepository;
import com.aliasadi.androidmvp.ui.base.BasePresenter;

import java.lang.ref.WeakReference;
import java.util.Map;

public class MainPresenter extends BasePresenter<MainView> {

    private final FanzRepository fanzRepository;

    MainPresenter(MainView view, FanzRepository fanzRepository) {
        super(view);
        this.fanzRepository = fanzRepository;



    }

    public void onAttach() {
        getAllMovies();
    }

    /**
     * Network
     **/
    private void getAllMovies() {
        fanzRepository.getRemoteConfigForPlayers(new MovieCallListener(view));
    }

    public void createDynamicLink(Intent intent) {
        fanzRepository.createDynamicLink(intent, new CreateCynamicLinkCallListener( view));
    }

        /**
         * Callback
         **/
    private static class MovieCallListener implements FanzDataSource.LoadMoviesCallback {

        private WeakReference<MainView> view;

        private MovieCallListener(MainView view) {
            this.view = new WeakReference<>(view);
        }

        @Override
        public void onRemoteConfigLoaded(Map<String, Boolean> players) {
            if (view.get() == null) return;
            view.get().showPlayersFromRemoteConfig(players);
        }

        @Override
        public void onDataNotAvailable() {
            if (view.get() == null) return;
            view.get().showThereIsNoMovies();

        }

        @Override
        public void onError() {
            if (view.get() == null) return;
            view.get().showErrorMessage();

        }
    }

    private static class CreateCynamicLinkCallListener implements FanzDataSource.CreateDynamicLinkCallback {

        private WeakReference<MainView> view;

        private CreateCynamicLinkCallListener(MainView view) {
            this.view = new WeakReference<>(view);
        }


        @Override
        public void onCreateDynamicLink(String playerId) {
            if (view.get() == null) return;
            view.get().createDynamicLink(playerId);
        }

        @Override
        public void onError() {
            if (view.get() == null) return;
            view.get().showErrorMessage();

        }
    }
}
