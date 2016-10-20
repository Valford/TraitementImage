package com.example.valentin.traitementimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView sizePicture;
    ImageView picture;
    ImageState pictureState;
    ImageProcessing traitement;
    Spinner spinner;
    Camera camera;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traitement_image);


        this.sizePicture = (TextView) findViewById(R.id.textView);
        this.picture = (ImageView) findViewById(R.id.imageView);
        this.traitement = new ImageProcessing(this.picture);
        this.pictureState = new ImageState(this);
        ImageState.hideShowPicture(picture, sizePicture);
        spinner = (Spinner) findViewById(R.id.spinner);
        this.camera= new Camera(this.picture);

        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        Button Buttonaff = (Button)findViewById(R.id.button);
        Buttonaff.setOnClickListener((View.OnClickListener)this);
        Button Buttonphoto = (Button)findViewById(R.id.buttonphoto);
        Buttonaff.setOnClickListener((View.OnClickListener)this);
    }

    public void onClick(View view) {

        if (view.getId() == R.id.button) {
            ImageState.hideShowPicture(picture, sizePicture);
        }

      /*  if(view.getId() == R.id.buttonphoto){
            if ( !Camera.hasCamera())
                Camera.capturePhoto();
            else
                return;
        }*/
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        if(ImageState.visibility==true){
            switch (pos) {
                case 0:
                    if(ImageState.visibility==true) {
                        traitement.picture.setImageBitmap(traitement.bitMapOriginal);
                        traitement.picture.setVisibility(View.VISIBLE);
                    }
                    break;
                case 1:
                    if(ImageState.visibility==true){
                        traitement.Greylevel();
                    }
                    break;
                case 2:
                    if(ImageState.visibility==true) {
                        traitement.Sepia();
                    }
                    break;
                case 3:
                    if(ImageState.visibility==true) {
                        traitement.Colorize();
                    }
                    break;
                case 4:
                    if(ImageState.visibility==true) {
                        traitement.Isolate();
                    }
                    break;
                case 5:
                    if(ImageState.visibility==true) {
                        traitement.Increasecontrastgrey();
                    }
                    break;
                case 6:
                    if(ImageState.visibility==true) {
                        traitement.Decreasecontrastegrey();
                    }
                    break;
                case 7:
                    if(ImageState.visibility==true) {
                        traitement.Increasecontrastcolor();
                    }
                    break;
                case 8:
                    if(ImageState.visibility==true) {
                        traitement.Overexposure();
                    }
                    break;
                case 9:
                    if(ImageState.visibility==true) {
                        traitement.Histogramgrey();
                    }
                    break;
                case 10:
                    if(ImageState.visibility==true) {
                        traitement.Histogramcolor();
                    }
                    break;
            }
        }

        else{
            Toast.makeText(getApplicationContext(), "Image non affichée", Toast.LENGTH_SHORT).show();
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        traitement.picture.setImageBitmap(traitement.bitMapOriginal);
        traitement.picture.setVisibility(View.VISIBLE);
    }
}

