package com.example.valentin.traitementimage;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageProcessing extends AppCompatActivity {

    ImageView picture;
    Bitmap bitMapOriginal;
    Bitmap bitmapModifie;
    int width;
    int height;
    double valred;
    double valgreen;
    double valblue;
    int canalred;
    int canalgreen;
    int canalblue;
    int canalgrey;
    int pixelarray[];

    public ImageProcessing(ImageView picture){

        this.picture = picture;

        BitmapDrawable drawable = (BitmapDrawable) picture.getDrawable();
        bitMapOriginal = drawable.getBitmap();
        bitmapModifie = bitMapOriginal.copy(Bitmap.Config.ARGB_8888, true);

        width = bitMapOriginal.getWidth();
        height = bitMapOriginal.getHeight();

    }

    public void Greylevel(){

        valred = 0.3;
        valgreen = 0.59;
        valblue = 0.11;

        pixelarray = new int[width * height];
        bitMapOriginal.getPixels(pixelarray, 0, width, 0, 0, width, height);

        for(int i=0; i<pixelarray.length; i++){
            canalgrey = (int) ((Color.red(pixelarray[i]) * valred) + (Color.green(pixelarray[i]) * valgreen) + (Color.blue(pixelarray[i]) * valblue));
            pixelarray[i] = Color.rgb(canalgrey, canalgrey, canalgrey);
        }

        bitmapModifie.setPixels(pixelarray, 0, width, 0, 0, width, height);
        picture.setImageBitmap(this.bitmapModifie);
    }

    public void Sepia(){

        pixelarray = new int[width * height];
        bitMapOriginal.getPixels(pixelarray, 0, width, 0, 0, width, height);

        for(int i=0; i<pixelarray.length; i++){

            valred = 0.393;
            valgreen = 0.769;
            valblue = 0.189;
            canalred = (int) Math.min(255, ((Color.red(pixelarray[i])*valred)+(Color.green(pixelarray[i])*valgreen)+(Color.blue(pixelarray[i])*valblue)));

            valred = 0.349;
            valgreen = 0.686;
            valblue = 0.168;
            canalgreen = (int) Math.min(255, ((Color.red(pixelarray[i])*valred)+(Color.green(pixelarray[i])*valgreen)+(Color.blue(pixelarray[i])*valblue)));

            valred = 0.272;
            valgreen = 0.534;
            valblue = 0.131;
            canalblue = (int) Math.min(255, ((Color.red(pixelarray[i])*valred)+(Color.green(pixelarray[i])*valgreen)+(Color.blue(pixelarray[i])*valblue)));

            pixelarray[i] = Color.rgb(canalred, canalgreen, canalblue);

        }

        bitmapModifie.setPixels(pixelarray, 0, width, 0, 0, width, height);
        picture.setImageBitmap(bitmapModifie);
    }

    public void Colorize() {

        float hsv[] = new float[3];

        pixelarray = new int[width * height];
        bitMapOriginal.getPixels(pixelarray, 0, width, 0, 0, width, height);

        for(int i=0; i<pixelarray.length; i++){

            Color.colorToHSV(pixelarray[i], hsv);
            hsv[0] = 120;
            pixelarray[i] = Color.HSVToColor(hsv);

        }

        bitmapModifie.setPixels(pixelarray, 0, width, 0, 0, width, height);
        picture.setImageBitmap(bitmapModifie);
    }

}
