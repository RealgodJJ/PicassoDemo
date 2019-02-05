package reagodjj.example.com.picassodemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView ivShow;
    private static final String PATH_BASE_URI = "http://img1.3lian.com/img2011/w1/106/85/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivShow = findViewById(R.id.iv_show);

        Picasso picasso = Picasso.get();
//        RequestCreator requestLocal = picasso.load(R.mipmap.ic_launcher);
        RequestCreator requestNet = picasso.load(getDataUris().get(2));

        requestNet.resize(300, 300);
        requestNet.centerCrop();

        requestNet.placeholder(R.mipmap.ic_launcher);

        requestNet.rotate(90);
        requestNet.error(R.mipmap.ic_launcher);

        requestNet.transform(new DefineTransformation());
        requestNet.into(ivShow);
    }

    public List<String> getDataUris() {
        List<String> photoList = new ArrayList<>();

        photoList.add(PATH_BASE_URI + "11.jpg");
        photoList.add(PATH_BASE_URI + "34.jpg");
        photoList.add(PATH_BASE_URI + "37.jpg");
        return photoList;
    }

    public class DefineTransformation implements Transformation {
        @Override
        public String key() {
            return "icon1";
        }

        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }
    }
}