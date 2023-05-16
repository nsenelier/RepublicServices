package com.example.republicservices.di

import android.app.Application
import androidx.room.Room
import com.example.republicservices.data.local.DAO.DriverDao
import com.example.republicservices.data.local.DAO.RoutesDao
import com.example.republicservices.data.local.datasource.LocalDataSource
import com.example.republicservices.data.local.datasource.LocalDataSourceImpl
import com.example.republicservices.data.local.db.DriverAndRouteDatabase
import com.example.republicservices.data.remote.api.RSApi
import com.example.republicservices.data.remote.api.RSApi.Companion.BASE_URL
import com.example.republicservices.data.remote.datasource.RemoteDataSource
import com.example.republicservices.data.remote.datasource.RemoteDataSourceImpl
import com.example.republicservices.domain.repository.RepositoryImpl
import com.example.republicservices.domain.repository.RepublicServiceRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(gson: Gson, okHttpClient: OkHttpClient): RSApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
            .create(RSApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): RepublicServiceRepository {
        return RepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun providesDatabase(app: Application): DriverAndRouteDatabase {
        return Room.databaseBuilder(
            app,
            DriverAndRouteDatabase::class.java,
            "DriversAndRoutes-db.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun getLocalDS(
        driverDao: DriverDao,
        routeDao: RoutesDao
    ): LocalDataSource {
        return LocalDataSourceImpl(driverDao, routeDao)
    }

    @Provides
    @Singleton
    fun provideDriverDao(database: DriverAndRouteDatabase) = database.driverDao()

    @Provides
    @Singleton
    fun provideRouteDao(database: DriverAndRouteDatabase) = database.routeDao()

    @Provides
    @Singleton
    fun getRemoteDS(rsApi: RSApi): RemoteDataSource =
        RemoteDataSourceImpl(rsApi)

}
