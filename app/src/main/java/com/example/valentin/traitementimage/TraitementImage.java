package com.example.valentin.traitementimage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TraitementImage extends AppCompatActivity implements View.OnClickListener {

    TextView photodimension;
    ImageView photo;
    Bitmap bitmaphoto;
    boolean visibility = true;
    int width;
    int height;
    double valred;
    double valgreen;
    double valblue;
    int canalred;
    int canalgreen;
    int canalblue;
    int canalalpha;
    int maxwidth;
    int maxheight;
    int pixel;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traitement_image);

        photo = (ImageView) findViewById(R.id.imageView);
        hideShowPicture(photo);

        photodimension = (TextView) findViewById(R.id.textView);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Resources res = getResources();
        int id = R.mipmap.emma;
        BitmapFactory.decodeResource(res, id, options);
        height = options.outHeight;
        width = options.outWidth;
        photo.setImageBitmap(bitmaphoto);

        Button Buttonaff = (Button)findViewById(R.id.button);
        Buttonaff.setOnClickListener((View.OnClickListener)this);

        Button Buttongris = (Button)findViewById(R.id.button2);
        Buttongris.setOnClickListener((View.OnClickListener)this);

        Button Buttonsepia = (Button)findViewById(R.id.button3);
        Buttonsepia.setOnClickListener((View.OnClickListener)this);
    }

    public void onClick(View view) {

        if (view.getId() == R.id.button) {
            photo = (ImageView)findViewById(R.id.imageView);
            hideShowPicture(photo);
        }

        if(visibility==true){
            if (view.getId() == R.id.button2) {
                Greylevel(bitmaphoto);
            }
            if (view.getId() == R.id.button3){
                Sepia(bitmaphoto);
            }
        }

    }

    public void hideShowPicture(ImageView photo){

        if(visibility==false){
            photo.setVisibility(View.VISIBLE);
            visibility=true;
            photodimension.setText(Integer.toString(width) + "*" + Integer.toString(height));
        }
        else{
            photo.setVisibility(View.INVISIBLE);
            visibility=false;
            photodimension.setText("-");
        }
    }

    public void Greylevel(Bitmap bitmaphoto){

        valred = 0.3;
        valgreen = 0.59;
        valblue = 0.11;

        for(int x=0; x<maxwidth; x++) {
            for (int y = 0; y < maxheight; y++) {

                pixel = bitmaphoto.getPixel(x, y);
                canalalpha = Color.alpha(pixel);
                canalred = (int) (Color.red(pixel) * valred);
                canalgreen = (int) (Color.green(pixel) * valgreen);
                canalblue = (int) (Color.blue(pixel) * valblue);
                bitmaphoto.setPixel(x, y, Color.argb(canalalpha, canalred, canalgreen, canalblue));

            }
        }

    }

    public void Sepia(Bitmap bitmaphoto){

        for(int x=0; x<maxwidth; x++) {
            for (int y = 0; y < maxheight; y++) {

                pixel = bitmaphoto.getPixel(x, y);
                canalalpha = Color.alpha(pixel);

                valred = 0.393;
                valgreen = 0.769;
                valblue = 0.189;
                canalred = (int) Math.min(255, ((Color.red(pixel)*valred)+(Color.green(pixel)*valgreen)+(Color.blue(pixel)*valblue)));

                valred = 0.349;
                valgreen = 0.686;
                valblue = 0.168;
                canalred = (int) Math.min(255, ((Color.red(pixel)*valred)+(Color.green(pixel)*valgreen)+(Color.blue(pixel)*valblue)));

                valred = 0.272;
                valgreen = 0.534;
                valblue = 0.131;
                canalred = (int) Math.min(255, ((Color.red(pixel)*valred)+(Color.green(pixel)*valgreen)+(Color.blue(pixel)*valblue)));

                canalgreen = (int) (Color.green(pixel) * valgreen);
                canalblue = (int) (Color.blue(pixel) * valblue);
                bitmaphoto.setPixel(x, y, Color.argb(canalalpha, canalred, canalgreen, canalblue));

            }
        }

    }



}
