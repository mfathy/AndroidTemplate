package me.mfathy.task.data.store.remote.service

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import me.mfathy.task.BuildConfig
import me.mfathy.task.data.preference.PreferenceHelperImpl
import me.mfathy.task.data.store.remote.service.auth.AuthServiceApi
import me.mfathy.task.data.store.remote.service.auth.LoginHeaderInterceptor
import me.mfathy.task.data.store.remote.service.doctors.DoctorsServiceApi
import me.mfathy.task.data.store.remote.service.doctors.OAuthHeaderInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Mohammed Fathy
 * dev.mfathy@gmail.com
 *
 * ServiceFactory is a class which is responsible for creating Okhttp client and creates
 * an implementation of retrofit remote service.
 */
object ServiceFactory {

    /**
     * Makes Authentication API Service.
     * @param isDebug to control logging.
     * @param headerInterceptor Login API header interceptor.
     * @return DoctorsServiceApi object
     */
    fun makeAuthService(
        isDebug: Boolean,
        headerInterceptor: LoginHeaderInterceptor
    ): AuthServiceApi {
        val okHttpClient =
            makeOkHttpClient(
                makeLoggingInterceptor(
                    (isDebug)
                ),
                headerInterceptor
            )
        return makeAuthService(okHttpClient, GsonBuilder().create())
    }

    /**
     * Makes a remote service
     * @param okHttpClient client.
     * @param gson parser.
     */
    private fun makeAuthService(okHttpClient: OkHttpClient, gson: Gson): AuthServiceApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(AuthServiceApi::class.java)
    }

    /**
     * Makes a remote service api.
     * @param isDebug to control logging.
     * @param headerInterceptor Doctors API header interceptor.
     * @return DoctorsServiceApi object
     */
    fun makeRemoteService(
        isDebug: Boolean,
        headerInterceptor: OAuthHeaderInterceptor
    ): DoctorsServiceApi {
        val okHttpClient =
            makeOkHttpClient(
                makeLoggingInterceptor(
                    (isDebug)
                ),
                headerInterceptor
            )
        return makeRemoteService(
            okHttpClient,
            GsonBuilder().create()
        )
    }

    /**
     * Makes a remote service
     * @param okHttpClient client.
     * @param gson parser.
     */
    private fun makeRemoteService(okHttpClient: OkHttpClient, gson: Gson): DoctorsServiceApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(DoctorsServiceApi::class.java)
    }

    /**
     * Makes an OkHttp Client.
     */
    private fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Makes a logging interceptor
     */
    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    /**
     * Makes a picasso loader using API header.
     */
    fun makePicassoLoader(mContext: Context): Picasso {
        return Picasso.Builder(mContext)
            .downloader(
                OkHttp3Downloader(
                    makeOkHttpClient(
                        makeLoggingInterceptor(false),
                        OAuthHeaderInterceptor(PreferenceHelperImpl(mContext))
                    )
                )
            )
            .build()
    }
}