package com.hubertyoung.common.net.interceptor;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/**
 * @author:Yang
 * @date:2017/11/30 09:56
 * @since:V1.0
 * @desc:com.hubertyoung.common.net.interceptor
 * @param:包含Gzip压缩的请求拦截
 */
public class GzipRequestInterceptor implements Interceptor {
    @Override
    public Response intercept( @NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
            return chain.proceed(originalRequest);
        }
        Request compressedRequest = originalRequest.newBuilder().header("Accept-Encoding", "gzip").method(originalRequest.method(), gzip
                (originalRequest.body())).build();
        return chain.proceed(compressedRequest);
    }

    private RequestBody gzip( final RequestBody body) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return body.contentType();
            }

            @Override
            public long contentLength() {
                return -1;
            }

            @Override
            public void writeTo(@NonNull BufferedSink sink) throws IOException {
                BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                body.writeTo(gzipSink);
                gzipSink.close();
            }
        };
    }
}
