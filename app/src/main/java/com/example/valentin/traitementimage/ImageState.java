package com.example.valentin.traitementimage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageState {

    static Resources res;
    static TextView pictureSize;
    static boolean visibility = false;
    static int truewidth;
    static int trueheight;

    public ImageState(Resources res){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        this.res = res;
        int id = R.mipmap.paysage;
        BitmapFactory.decodeResource(res, id, options);
        trueheight = options.outHeight;
        truewidth = options.outWidth;
    }

    public static void hideShowPicture(ImageView picture, TextView pictureSize){

        if(visibility==false){
            picture.setVisibility(View.VISIBLE);
            visibility=true;
            pictureSize.setText(Integer.toString(truewidth) + "*" + Integer.toString(trueheight));
        }

        else{
            picture.setVisibility(View.INVISIBLE);
            visibility=false;
            pictureSize.setText("-");
        }
    }

    public static Resources getRessources(){
        return res;
    }

}
