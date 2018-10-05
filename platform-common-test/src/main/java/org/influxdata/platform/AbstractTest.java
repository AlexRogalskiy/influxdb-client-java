/*
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.influxdata.platform;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author Jakub Bednar (bednar@github) (03/10/2018 14:54)
 */
public abstract class AbstractTest {

    private static final int DEFAULT_WAIT = 10;
    private static final int DEFAULT_INFLUXDB_SLEEP = 100;

    protected CountDownLatch countDownLatch;

    @BeforeEach
    protected void prepare() {
        countDownLatch = new CountDownLatch(1);
    }

    protected void waitToCallback() {
        waitToCallback(DEFAULT_WAIT);
    }

    protected void waitToCallback(final int seconds) {
        waitToCallback(countDownLatch, seconds);
    }

    protected void waitToCallback(@Nonnull final CountDownLatch countDownLatch, final int seconds) {
        try {
            Assertions
                    .assertThat(countDownLatch.await(seconds, TimeUnit.SECONDS))
                    .overridingErrorMessage(
                            "The countDown wasn't counted to zero. Before elapsed: %s seconds.", seconds)
                    .isTrue();
        } catch (InterruptedException e) {
            Assertions.fail("Unexpected exception", e);
        }
    }

    @Nonnull
    protected String getInfluxDbURL() {

        String influxdbIP = System.getenv().getOrDefault("INFLUXDB_IP", "127.0.0.1");
        String influxdbPort = System.getenv().getOrDefault("INFLUXDB_PORT_API", "8086");

        return "http://" + influxdbIP + ":" + influxdbPort;
    }

    protected void influxDBWrite(@Nonnull final String lineProtocol, @Nonnull final String databaseName) {

        Request request = new Request.Builder()
                .url(getInfluxDbURL() + "/write?db=" + databaseName)
                .addHeader("accept", "application/json")
                .post(RequestBody.create(MediaType.parse("text/plain"), lineProtocol))
                .build();

        influxDBRequest(request);
    }

    protected void influxDBQuery(@Nonnull final String query, @Nonnull final String databaseName) {

        Request request = new Request.Builder()
                .url(getInfluxDbURL() + "/query?db=" + databaseName + ";q=" + query)
                .addHeader("accept", "application/json")
                .get()
                .build();

        influxDBRequest(request);
    }

    private void influxDBRequest(@Nonnull final Request request) {

        Assertions.assertThat(request).isNotNull();

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
            Assertions.assertThat(response.isSuccessful()).isTrue();

            Thread.sleep(DEFAULT_INFLUXDB_SLEEP);
        } catch (Exception e) {
            Assertions.fail("Unexpected exception", e);
        }
    }
}