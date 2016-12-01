package com.example.valentin.traitementimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageProcessing {

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
    int pixelarraytext[];

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

    public void Isolate() {

        int chosencolor = Color.RED;
        int limit = 200;
        valred = 0.3;
        valgreen = 0.59;
        valblue = 0.11;
        int distance = 0;

        pixelarray = new int[width * height];
        bitMapOriginal.getPixels(pixelarray, 0, width, 0, 0, width, height);

        for(int i=0; i<pixelarray.length; i++){
            distance = (int)( Math.sqrt(Math.pow( (Color.red(chosencolor) - Color.red(pixelarray[i])),2 ) + Math.pow( (Color.green(chosencolor) - Color.green(pixelarray[i])),2 ) +  Math.pow( (Color.blue(chosencolor) - Color.blue(pixelarray[i])),2 )));
            if(distance >= limit) {
                canalgrey = (int) ((Color.red(pixelarray[i]) * valred) + (Color.green(pixelarray[i]) * valgreen) + (Color.blue(pixelarray[i]) * valblue));
                pixelarray[i] = Color.rgb(canalgrey, canalgrey, canalgrey);
            }

        }

        bitmapModifie.setPixels(pixelarray, 0, width, 0, 0, width, height);
        picture.setImageBitmap(bitmapModifie);
    }

    public void Increasecontrastgrey (){

        valred = 0.3;
        valgreen = 0.59;
        valblue = 0.11;
        int max = 0;
        int min = 255;
        int temp;

        pixelarray = new int[width * height];
        bitMapOriginal.getPixels(pixelarray, 0, width, 0, 0, width, height);

        for(int i=0; i<pixelarray.length; i++){
            canalgrey = (int) ((Color.red(pixelarray[i]) * valred) + (Color.green(pixelarray[i]) * valgreen) + (Color.blue(pixelarray[i]) * valblue));
            pixelarray[i] = Color.rgb(canalgrey, canalgrey, canalgrey);
        }

        for(int i=0; i<pixelarray.length; i++){
            temp = Color.red(pixelarray[i]) ;
            if(temp < min){
                min = temp;
            }
            if(temp > max){
                max = temp;
            }
        }

        for(int i=0; i<pixelarray.length; i++){
            temp = ((255*(Color.red(pixelarray[i])-min))/(max-min));
            pixelarray[i] = Color.rgb(temp, temp, temp);
        }

        bitmapModifie.setPixels(pixelarray, 0, width, 0, 0, width, height);
        picture.setImageBitmap(bitmapModifie);
    }

    public void Decreasecontrastegrey(){

        valred = 0.3;
        valgreen = 0.59;
        valblue = 0.11;
        int max = 0;
        int min = 255;
        int temp;
        int intervallimit = 180;

        pixelarray = new int[width * height];
        bitMapOriginal.getPixels(pixelarray, 0, width, 0, 0, width, height);

        for(int i=0; i<pixelarray.length; i++){
            canalgrey = (int) ((Color.red(pixelarray[i]) * valred) + (Color.green(pixelarray[i]) * valgreen) + (Color.blue(pixelarray[i]) * valblue));
            pixelarray[i] = Color.rgb(canalgrey, canalgrey, canalgrey);
        }

        for(int i=0; i<pixelarray.length; i++){
            temp = Color.red(pixelarray[i]) ;
            if(temp < min){
                min = temp;
            }
            if(temp > max){
                max = temp;
            }
        }

        for(int i=0; i<pixelarray.length; i++){
            temp = ((intervallimit*(Color.red(pixelarray[i])-min))/(max-min));
            pixelarray[i] = Color.rgb(temp, temp, temp);
        }

        bitmapModifie.setPixels(pixelarray, 0, width, 0, 0, width, height);
        picture.setImageBitmap(bitmapModifie);
    }

    public void Increasecontrastcolor (){

        int maxred = 0;
        int minred = 255;
        int maxgreen = 0;
        int mingreen = 255;
        int maxblue = 255;
        int minblue = 0;
        int tempred;
        int tempgreen;
        int tempblue;

        pixelarray = new int[width * height];
        bitMapOriginal.getPixels(pixelarray, 0, width, 0, 0, width, height);

        for(int i=0; i<pixelarray.length; i++){
            tempred = Color.red(pixelarray[i]) ;
            if(tempred < minred){
                minred = tempred;
            }
            if(tempred > maxred){
                maxred = tempred;
            }

            tempgreen = Color.green(pixelarray[i]) ;
            if(tempgreen < mingreen){
                mingreen = tempgreen;
            }
            if(tempgreen > maxgreen){
                maxgreen = tempgreen;
            }

            tempblue = Color.blue(pixelarray[i]) ;
            if(tempblue < minblue){
                minblue = tempblue;
            }
            if(tempblue > maxblue){
                maxblue = tempblue;
            }
        }

        for(int i=0; i<pixelarray.length; i++){
            tempred = ((255*(Color.red(pixelarray[i])-minred))/(maxred-minred));
            tempgreen = ((255*(Color.green(pixelarray[i])-mingreen))/(maxgreen-mingreen));
            tempblue = ((255*(Color.blue(pixelarray[i])-minblue))/(maxblue-minblue));
            pixelarray[i] = Color.rgb(tempred, tempgreen, tempblue);
        }

        bitmapModifie.setPixels(pixelarray, 0, width, 0, 0, width, height);
        picture.setImageBitmap(bitmapModifie);

    }

    public void Overexposure () {

        float hsv[] = new float[3];

        pixelarray = new int[width * height];
        bitMapOriginal.getPixels(pixelarray, 0, width, 0, 0, width, height);

        for(int i=0; i<pixelarray.length; i++){

            Color.colorToHSV(pixelarray[i], hsv);
            hsv[2] = (float) (hsv[2] + 0.20);
            pixelarray[i] = Color.HSVToColor(hsv);

        }

        bitmapModifie.setPixels(pixelarray, 0, width, 0, 0, width, height);
        picture.setImageBitmap(bitmapModifie);

    }

    public void Histogramgrey (){

        int hist[] = new int[256];
        int histcumul[] = new int [256];
        int cumul=0;

        pixelarray = new int[width * height];
        bitMapOriginal.getPixels(pixelarray, 0, width, 0, 0, width, height);

        for(int i=0; i<pixelarray.length; i++){
            canalgrey = (int) ((Color.red(pixelarray[i]) * valred) + (Color.green(pixelarray[i]) * valgreen) + (Color.blue(pixelarray[i]) * valblue));
            pixelarray[i] = Color.rgb(canalgrey, canalgrey, canalgrey);
        }

        for(int i=0; i<pixelarray.length; i++){
            hist[Color.red(pixelarray[i])] = hist[Color.red(pixelarray[i])] + 1 ;
        }

        for(int i=0; i<hist.length; i++){
            cumul=cumul+hist[i];
            histcumul[i]=histcumul[i]+cumul;
        }

        for(int i=0; i<pixelarray.length; i++){
            canalgrey =(histcumul[Color.red(pixelarray[i])]*255)/(width*height);
            pixelarray[i] = Color.rgb(canalgrey, canalgrey, canalgrey);
        }

        bitmapModifie.setPixels(pixelarray, 0, width, 0, 0, width, height);
        picture.setImageBitmap(bitmapModifie);
    }

    public void Histogramcolor (){

        int histred[] = new int[256];
        int histgreen[] = new int[256];
        int histblue[] = new int[256];
        int histcumulred[] = new int [256];
        int histcumulgreen[] = new int [256];
        int histcumulblue[] = new int [256];
        int cumulred=0;
        int cumulgreen=0;
        int cumulblue=0;


        pixelarray = new int[width * height];
        bitMapOriginal.getPixels(pixelarray, 0, width, 0, 0, width, height);

        for(int i=0; i<pixelarray.length; i++){
            histred[Color.red(pixelarray[i])] = histred[Color.red(pixelarray[i])] + 1 ;
            histgreen[Color.red(pixelarray[i])] = histgreen[Color.red(pixelarray[i])] + 1 ;
            histblue[Color.red(pixelarray[i])] = histblue[Color.red(pixelarray[i])] + 1 ;
        }

        for(int i=0; i<histred.length; i++){
            cumulred=cumulred+histred[i];
            histcumulred[i]=histcumulred[i]+cumulred;
            cumulgreen=cumulgreen+histgreen[i];
            histcumulgreen[i]=histcumulgreen[i]+cumulgreen;
            cumulblue=cumulblue+histblue[i];
            histcumulblue[i]=histcumulblue[i]+cumulblue;
        }

        for(int i=0; i<pixelarray.length; i++){
            canalred = (histcumulred[Color.red(pixelarray[i])]*255)/(width*height);
            canalgreen = (histcumulgreen[Color.green(pixelarray[i])]*255)/(width*height);
            canalblue = (histcumulblue[Color.blue(pixelarray[i])]*255)/(width*height);
            pixelarray[i] = Color.rgb(canalred, canalgreen, canalblue);
        }

        bitmapModifie.setPixels(pixelarray, 0, width, 0, 0, width, height);
        picture.setImageBitmap(bitmapModifie);
        }

    public void integrertext (){

        Bitmap bitmapText = BitmapFactory.decodeResource(ImageState.getRessources(), R.mipmap.texte);
        int widthtext = bitmapText.getWidth();
        int heighttext = bitmapText.getHeight();

        int moyred, moygreen, moyblue;
        pixelarray = new int[width * height];
        bitMapOriginal.getPixels(pixelarray, 0, width, 0, 0, width, height);
        pixelarraytext = new int[widthtext * heighttext];

        for(int i=0; i<pixelarray.length; i++){
            for(int j=0; j<pixelarraytext.length; j++){
                if(pixelarraytext[i]!=Color.WHITE){
                    moyred=(Color.red(pixelarray[i])+Color.red(pixelarraytext[j]))/2;
                    moygreen=(Color.green(pixelarray[i])+Color.green(pixelarraytext[j]))/2;
                    moyblue=(Color.blue(pixelarray[i])+Color.blue(pixelarraytext[j]))/2;
                    pixelarray[i]=Color.rgb(moyred, moygreen, moyblue);
                }
            }
        }

        bitmapModifie.setPixels(pixelarray, 0, width, 0, 0, width, height);
        picture.setImageBitmap(bitmapModifie);
    }
}
