package cn.uhei.hw72openlocalimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * sd卡操作权限
 * <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 * 1 把raw/图片复制到SD卡，然后把图片路径传递给ImageActivity打开
 * 2 ImageActivity自动响应图片类型的文件
 *
 * Androidmanifest配置
 *  <activity android:name=".ImageActivity">
 *      <intent-filter>
 *              <action android:name="android.intent.action.VIEW"/> *
 *              <category android:name="android.intent.category.DEFAULT"/>
 *               <!--打开文件类型-->
 *              <data android:mimeType="image/*"/>
 *
 *      </intent-filter> *
 *</activity>
 *
 *
 *
 *
 *
 *
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private ImageView img;
    //sdcard目录
    File sdcard = Environment.getExternalStorageDirectory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File sddir = new File(sdcard,"ab.jpg");
               //入流
                InputStream is = getResources().openRawResource(R.raw.images);
                //读取出流里的数据，并构造成位图对象
//                Bitmap bm = BitmapFactory.decodeStream(is);

                try {

                    //出流: 到sd卡

                    FileOutputStream fos = new FileOutputStream(sddir);
                    //每次读取一个字节
                    byte[] b = new byte[1024];

                    int len = 0;
                    //读取is 流，每次读1个字节，然后赋值给len, 流读完是-1，
                    while ((len = is.read(b)) != -1){
                        //用字字节方式写写到缓存目录，从0开始，到len结束
                        fos.write(b, 0, len);
                    }
                    //关闭流
                    fos.close();

                    //系统默认动作
                    Intent intent = new Intent(Intent.ACTION_VIEW);

                    //arg : 路径 | 类型 （对应androidmanifest配置）
                    intent.setDataAndType(Uri.parse(sddir.getAbsolutePath()),"image/*");

                    startActivity(intent);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
