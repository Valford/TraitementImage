package com.example.valentin.traitementimage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageState extends AppCompatActivity {

    static TextView pictureSize;
    static boolean visibility = true;
    static int truewidth;
    static int trueheight;

    public ImageState(Context context){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Resources res = context.getResources();
        int id = R.mipmap.emma;
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

}
