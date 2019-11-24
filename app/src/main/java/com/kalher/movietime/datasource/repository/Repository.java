package com.kalher.movietime.datasource.repository;

import androidx.lifecycle.LiveData;

import com.kalher.movietime.common.interfaces.IShow;
import com.kalher.movietime.common.UIAction;

import java.util.List;

public class Repository {

    protected String TAG = this.getClass().getSimpleName();

    public void getShowList(UIAction uiAction, LiveData<List<? extends IShow>> liveData){
        if(this instanceof MovieRepo){
//            ((MovieRepo) this).fetchShowListFromApi(uiAction, liveData);
        }
    }

}
