package com.example.valentin.traitementimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main extends AppCompatActivity implements View.OnClickListener {

    TextView sizePicture;
    ImageView picture;
    ImageState pictureState;
    ImageProcessing traitement;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traitement_image);


        this.sizePicture = (TextView) findViewById(R.id.textView);
        this.picture = (ImageView) findViewById(R.id.imageView);
        this.traitement = new ImageProcessing(this.picture);
        this.pictureState = new ImageState(this);
        ImageState.hideShowPicture(picture, sizePicture);


        Button Buttonaff = (Button)findViewById(R.id.button);
        Buttonaff.setOnClickListener((View.OnClickListener)this);

        Button Buttongrey = (Button)findViewById(R.id.button2);
        Buttongrey.setOnClickListener((View.OnClickListener)this);

        Button Buttonsepia = (Button)findViewById(R.id.button3);
        Buttonsepia.setOnClickListener((View.OnClickListener)this);

        Button Buttoncolorize = (Button)findViewById(R.id.button4);
        Buttoncolorize.setOnClickListener((View.OnClickListener)this);

        Button Buttonrestart = (Button)findViewById(R.id.button5);
        Buttonrestart.setOnClickListener((View.OnClickListener)this);

    }

    public void onClick(View view) {

        if (view.getId() == R.id.button) {
            ImageState.hideShowPicture(picture, sizePicture);
        }


        if(ImageState.visibility==true){
            if (view.getId() == R.id.button2) {
                traitement.Greylevel();
            }
            if (view.getId() == R.id.button3){
                traitement.Sepia();
            }
            if (view.getId() == R.id.button4){
                traitement.Colorize();
            }

            if (view.getId() == R.id.button5) {
                traitement.picture.setImageBitmap(traitement.bitMapOriginal);
                traitement.picture.setVisibility(View.VISIBLE);
            }

        }

    }

    protected void onStart() {
        super.onStart();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onResume();
    }

}
