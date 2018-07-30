package br.com.alura.leilao.api.retrofit;

import android.support.annotation.NonNull;

import br.com.alura.leilao.api.retrofit.service.LeilaoService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public class RetrofitInicializador {

    private static final String URL_BASE = "http://endereco_ip_da_api/";
    private final Retrofit retrofit;

    public RetrofitInicializador() {
        OkHttpClient client = configuraHttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @NonNull
    private OkHttpClient configuraHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BODY);
        return new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build();
    }

    public LeilaoService getLeilaoService() {
        return retrofit.create(LeilaoService.class);
    }

}
