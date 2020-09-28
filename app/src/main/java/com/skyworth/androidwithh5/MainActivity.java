package com.skyworth.androidwithh5;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements JsBridge{

    WebView webView;
    TextView textView;
    Handler handler;
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets(savedInstanceState);
    }

    private void InitWidgets(Bundle savedInstanceState){
        webView = findViewById(R.id.webview);
        textView = findViewById(R.id.tv_result);
        editText = findViewById(R.id.edittext);
        button = findViewById(R.id.button);
        handler = new Handler();
        //允许webview加载js
        webView.getSettings().setJavaScriptEnabled(true);
        //给webview添加js接口    name 是给js调用
        webView.addJavascriptInterface(new jsinterface(this), "launcher");

        webView.loadUrl("file:///android_asset/index.html");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editText.getText().toString();
                webView.loadUrl("javascript:if(window.remote){window.remote('"+str+"')}");
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //打开允许调试的开关
            webView.setWebContentsDebuggingEnabled(true);
        }
    }

    @Override
    public void setTextViewValue(final String value) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(value);
            }
        });
    }
}
