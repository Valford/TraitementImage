package com.example.valentin.traitementimage;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
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
    boolean visibility = true;
    int width = 0;
    int height = 0;


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
                NiveauDeGris();
            }
            if (view.getId() == R.id.button3){
                Sepia();
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

    public void NiveauDeGris(){

    }

    public void Sepia(){

    }

}
