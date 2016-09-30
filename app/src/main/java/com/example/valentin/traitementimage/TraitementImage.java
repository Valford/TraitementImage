package com.example.valentin.traitementimage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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
    Bitmap imaget;
    boolean visibility = true;
    int width;
    int height;
    double valred;
    double valgreen;
    double valblue;
    int canalred;
    int canalgreen;
    int canalblue;
    int canalgrey;
    int maxwidth;
    int maxheight;
    int pixel;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traitement_image);

        photo = (ImageView) findViewById(R.id.imageView);

        photodimension = (TextView) findViewById(R.id.textView);

        hideShowPicture(photo);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Resources res = getResources();
        int id = R.mipmap.emma;
        BitmapFactory.decodeResource(res, id, options);
        height = options.outHeight;
        width = options.outWidth;

        BitmapDrawable drawable = (BitmapDrawable) photo.getDrawable();
        bitmaphoto = drawable.getBitmap();
        imaget = bitmaphoto.copy(Bitmap.Config.ARGB_8888, true);

        maxwidth = bitmaphoto.getWidth();
        maxheight = bitmaphoto.getHeight();

        Button Buttonaff = (Button)findViewById(R.id.button);
        Buttonaff.setOnClickListener((View.OnClickListener)this);

        Button Buttongris = (Button)findViewById(R.id.button2);
        Buttongris.setOnClickListener((View.OnClickListener)this);

        Button Buttonsepia = (Button)findViewById(R.id.button3);
        Buttonsepia.setOnClickListener((View.OnClickListener)this);

        Button Buttoncolorize = (Button)findViewById(R.id.button4);
        Buttoncolorize.setOnClickListener((View.OnClickListener)this);

        Button Buttonrestart = (Button)findViewById(R.id.button5);
        Buttonrestart.setOnClickListener((View.OnClickListener)this);

    }

    public void onClick(View view) {

        if (view.getId() == R.id.button) {
            photo = (ImageView)findViewById(R.id.imageView);
            hideShowPicture(photo);
        }
        if (view.getId() == R.id.button5) {
            photo.setImageBitmap(this.bitmaphoto);
            photo.setVisibility(View.VISIBLE);
        }
        if(visibility==true){
            if (view.getId() == R.id.button2) {
                Greylevel();
            }
            if (view.getId() == R.id.button3){
                Sepia();
            }
            if (view.getId() == R.id.button4){
                Colorize();
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

    public void Greylevel(){

        valred = 0.3;
        valgreen = 0.59;
        valblue = 0.11;

        for(int y=0; y<maxheight; y++) {
            for (int x = 0; x < maxwidth; x++) {

                pixel = bitmaphoto.getPixel(x, y);
                canalgrey = (int) ((Color.red(pixel) * valred) + (Color.green(pixel) * valgreen) + (Color.blue(pixel) * valblue));
                imaget.setPixel(x, y, Color.rgb(canalgrey, canalgrey, canalgrey));

            }
        }

        photo.setImageBitmap(this.imaget);
        photo.setVisibility(View.VISIBLE);
    }

    public void Sepia(){

        for(int y=0; y<maxheight; y++) {
            for (int x = 0; x < maxwidth; x++) {

                pixel = imaget.getPixel(x, y);

                valred = 0.393;
                valgreen = 0.769;
                valblue = 0.189;
                canalred = (int) Math.min(255, ((Color.red(pixel)*valred)+(Color.green(pixel)*valgreen)+(Color.blue(pixel)*valblue)));

                valred = 0.349;
                valgreen = 0.686;
                valblue = 0.168;
                canalgreen = (int) Math.min(255, ((Color.red(pixel)*valred)+(Color.green(pixel)*valgreen)+(Color.blue(pixel)*valblue)));

                valred = 0.272;
                valgreen = 0.534;
                valblue = 0.131;
                canalblue = (int) Math.min(255, ((Color.red(pixel)*valred)+(Color.green(pixel)*valgreen)+(Color.blue(pixel)*valblue)));

                imaget.setPixel(x, y, Color.rgb(canalred, canalgreen, canalblue));

            }
        }

        photo.setImageBitmap(imaget);
        photo.setVisibility(View.VISIBLE);

    }

    public void Colorize() {

        float hsv[] = new float[3];

        for (int y = 0; y < maxheight; y++) {
            for (int x = 0; x < maxwidth; x++) {

                pixel = bitmaphoto.getPixel(x, y);
                Color.RGBToHSV(Color.red(pixel), Color.green(pixel), Color.blue(pixel), hsv);
                hsv[0] = 120;
                pixel = Color.HSVToColor(hsv);
                imaget.setPixel(x, y, pixel);
            }
        }

        photo.setImageBitmap(imaget);
        photo.setVisibility(View.VISIBLE);

    }
}
