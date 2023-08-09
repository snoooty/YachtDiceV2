package com.example.yachtdicev2.gameLogic;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimerData extends ViewModel {

    private MutableLiveData<Integer> timer;

    public MutableLiveData<Integer> getTimer(){
        if(timer == null){
            timer = new MutableLiveData<Integer>();
        }
        return timer;
    }
}
