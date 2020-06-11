package com.fullstorydev.shoppedemo.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fullstorydev.shoppedemo.R;
import com.fullstorydev.shoppedemo.utilities.Constants;
import com.fullstorydev.shoppedemo.utilities.JsonHelper;
import com.fullstorydev.shoppedemo.utilities.LoadAssetsUtils;
import com.fullstorydev.shoppedemo.utilities.NetworkUtils;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.List;

public class ProductRepository {
    private MutableLiveData<List<Item>> mAllProducts; //products are sold in shop, fetched from API
    private MutableLiveData<Constants.Status> mStatus;

    public ProductRepository(Application application) {
        mStatus = new MutableLiveData<>(Constants.Status.LOADING);
        mAllProducts = new MutableLiveData<>();

        reload(application);
    }
    public void reload(Application application){
        mStatus.postValue(Constants.Status.LOADING);

        // get from API every time
        //TODO: save the product locally and call API in the backend, only update DB when it changes and notify and handle change
        new Thread(() -> {
            String hostURLStr = application.getString(R.string.products_endpoint);
            String resStr = "";
            boolean loadFromFile = false;
            try {
                resStr = NetworkUtils.getProductListFromURL(hostURLStr);
            } catch (IOException e) {
                loadFromFile = true;
                e.printStackTrace();
            }

            if(loadFromFile){
                try {
                    resStr = LoadAssetsUtils.getProductListFromFile(application,Constants.PRODUCT_DATA_FILENE);
                } catch (IOException e) {
                    mStatus.postValue(Constants.Status.ERROR);
                    e.printStackTrace();
                }
            }


            List<Item> list = JsonHelper.getProductListFromJsonString(resStr);
            mAllProducts.postValue(list);
            mStatus.postValue(Constants.Status.SUCCESS);

        }).start();
    }

    public LiveData<List<Item>> getAllFromAPI() { return mAllProducts; }
    public MutableLiveData<Constants.Status> getStatus() { return mStatus; }
}
