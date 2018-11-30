package com.example.administrator.httpurlconnectionliufangnan;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

   private static final String url =  "http://v.juhe.cn/joke/content/list.php?key=c037fdc5bbb9e640d6b5a701dcb11441&page=1&pageSize=10&sort=asc&time=1418745237";
   private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUrl(url);
        Log.v(TAG,"onCreate"+json);

        Gson gson = new Gson();
        Bean bean = gson.fromJson(json,Bean.class);
        System.out.println("result"+bean.result);
        System.out.println("reason"+bean.reason);
        for (Data data:bean.result.datas){
            System.out.println("content"+data.content);
            System.out.println("hashId"+data.hashId);
            System.out.println("unixtime"+data.unixtime);
            System.out.println("updatetime"+data.updatetime);
        }
        System.out.println("error_code"+bean.error_code);
    }



    private static String json;
    private void getUrl(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    json = response.body().string().toString();
                    Log.v(TAG,"getUrl"+json);
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        });
    }

    static class Bean{
        private String reason;
        private String error_code;
       private Result result;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public String getError_code() {
            return error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "reason='" + reason + '\'' +
                    ", error_code='" + error_code + '\'' +
                    ", result=" + result +
                    '}';
        }
    }
    static class Result{
        List<Data> datas;

        public List<Data> getData() {
            return datas;
        }

        public void setData(List<Data> data) {
            this.datas = datas;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "datas=" + datas +
                    '}';
        }
    }
    static class Data{
        private String content;
        private String hashId;
        private String unixtime;
        private String updatetime;


        public String getContent(String content) {
            return content;
        }
        public void setContent(String content){
            this.content =content;
        }

        public String getHashId(String hashId) {
            return hashId;
        }
        public void setHashId(String hashId){
            this.hashId = hashId;
        }

        public String getUnixtime(String unixtime) {
            return unixtime;
        }
        public void setUnixtime(String unixtime){
            this.unixtime =unixtime;
        }

        public String getUpdatetime(String updatetime) {
            return updatetime;
        }
        public void setUpdatetime(String updatetime){
            this.updatetime = updatetime;
        }


        @Override
        public String toString() {
            return "Data{" +
                    "content='" + content + '\'' +
                    ", hashId='" + hashId + '\'' +
                    ", unixtime='" + unixtime + '\'' +
                    ", updatetime='" + updatetime + '\'' +
                    '}';
        }
    }
}
