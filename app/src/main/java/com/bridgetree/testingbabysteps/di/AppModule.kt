package com.bridgetree.testingbabysteps.di

import android.content.Context
import androidx.room.Room
import com.bridgetree.testingbabysteps.data.local.ShoppingDao
import com.bridgetree.testingbabysteps.data.local.ShoppingItemDatabase
import com.bridgetree.testingbabysteps.data.remote.PixabayAPI
import com.bridgetree.testingbabysteps.other.Constants.BASE_URL
import com.bridgetree.testingbabysteps.other.Constants.DATABASE_NAME
import com.bridgetree.testingbabysteps.respositories.DefaultShoppingRepository
import com.bridgetree.testingbabysteps.respositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()


    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()


    @Singleton
    @Provides
    fun providePixabayApi(): PixabayAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
            dao: ShoppingDao,
            api: PixabayAPI
    ) = DefaultShoppingRepository(dao, api) as ShoppingRepository
}