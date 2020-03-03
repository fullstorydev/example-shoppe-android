package com.fullstorydev.shoppedemo.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository {

    private ProductDao mProductDao;
    private LiveData<List<Product>> mAllProducts;

    // Note that in order to unit test the class, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public ProductRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mProductDao = db.productDao();
        mAllProducts = mProductDao.getAll();
    }

    public LiveData<List<Product>> getAll() {
        return mAllProducts;
    }

    public void insert(Product product) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mProductDao.insert(product);
        });
    }

    public void increaseQuantityInCart(Product product){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mProductDao.increaseQuantityOrInsert(product);
        });
    }

    public void decreaseQuantityInCart(Product product){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mProductDao.decreaseQuantityOrDelete(product);
        });
    }
}