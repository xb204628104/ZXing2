package com.example.administrator.zxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class MainActivity extends AppCompatActivity {
    private EditText et;
    private ImageView imageView;
    private static int REQUEST_CODE = 0;
    private static int REQUEST_IMAGE=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = (Button) findViewById(R.id.bt);
        Button bt2 = (Button) findViewById(R.id.bt2);
        Button bt3 = (Button) findViewById(R.id.bt3);
        et = (EditText) findViewById(R.id.et);
        imageView = (ImageView) findViewById(R.id.image_content);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                /*if (TextUtils.isEmpty(s)) {
                    Toast.makeText(MainActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                et.setText("");
                Bitmap mBitmap = CodeUtils.createImage(s, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.mao));

                imageView.setImageBitmap(mBitmap);*/
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = et.getText().toString().trim();
                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(MainActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                et.setText("");
                Bitmap mBitmap = CodeUtils.createImage(s, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.mao));

                imageView.setImageBitmap(mBitmap);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Uri uri = Uri.parse(result);
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(it);
                    // Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
