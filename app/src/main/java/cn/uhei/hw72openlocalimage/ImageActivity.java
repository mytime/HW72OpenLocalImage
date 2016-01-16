package cn.uhei.hw72openlocalimage;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {
    public static final String TAG = "ImageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Intent i = getIntent();
        Uri uri = i.getData();

        Log.e(TAG,uri.toString());

        ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
        ivImage.setImageURI(uri);
    }
}
