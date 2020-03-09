package com.fullstorydev.shoppedemo.ui.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CartViewModel extends ViewModel {
    //placeholder code to be replaced once data logic in place
    private MutableLiveData<String> mText;

    public CartViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is cart fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}