package com.example.valentin.traitementimage;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Point;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Main extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView sizePicture;
    ImageView picture;
    ImageState pictureState;
    ImageProcessing traitement;
    Spinner spinner;

    //Camera camera;
    //Uri imageURI;
    //File photofFile;
    //public static final int REQUEST_CAPTURE = 1;

    //int screenHeight = getResources().getDisplayMetrics().heightPixels;
    //int screenWidth = getResources().getDisplayMetrics().widthPixels;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traitement_image);

        this.sizePicture = (TextView) findViewById(R.id.textView);
        this.picture = (ImageView) findViewById(R.id.imageView);

        this.traitement = new ImageProcessing(this.picture);
        this.pictureState = new ImageState(getResources());
        ImageState.hideShowPicture(picture, sizePicture);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        Button Buttonaff = (Button) findViewById(R.id.button);
        Buttonaff.setOnClickListener((View.OnClickListener) this);
        Button Buttonphoto = (Button) findViewById(R.id.buttonphoto);
        Buttonaff.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View view) {

        if (view.getId() == R.id.button) { //affiche l'image si on clique sur le bouton
            ImageState.hideShowPicture(picture, sizePicture);
        }

       /* if (view.getId() == R.id.buttonphoto) { //utiliser caméra
            if (hasCamera())
                capturePhoto();
            else
                Toast.makeText(getApplicationContext(), "Pas d'appareil photo détecté", Toast.LENGTH_SHORT).show();
            ;
        }*/
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            switch (pos) {
                case 0:
                    traitement.picture.setImageBitmap(traitement.bitMapOriginal);
                    traitement.picture.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    traitement.Greylevel();
                    break;
                case 2:
                    traitement.Sepia();
                    break;
                case 3:
                    traitement.Colorize();
                    break;
                case 4:
                    traitement.Isolate();
                    break;
                case 5:
                    traitement.Increasecontrastgrey();
                    break;
                case 6:
                    traitement.Decreasecontrastegrey();
                    break;
                case 7:
                    traitement.Increasecontrastcolor();
                    break;
                case 8:
                    traitement.Overexposure();
                    break;
                case 9:
                    traitement.Histogramgrey();
                    break;
                case 10:
                    traitement.Histogramcolor();
                    break;
                case 11:
                    traitement.integrertext();
            }
        }

    public void onNothingSelected(AdapterView<?> parent) {
        traitement.picture.setImageBitmap(traitement.bitMapOriginal);
    }

    /*public boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAPTURE && resultCode == Activity.RESULT_OK) {
            extraireImage();
        }
    }

    private File createTemporaryFIle(String name, String extension) throws Exception {
        File temp = Environment.getExternalStorageDirectory();
        temp = new File(temp.getAbsolutePath() + "/.temp/");
        if (!temp.exists()) {
            temp.mkdirs();
        }

        return File.createTempFile(name, extension, temp);
    }

    public void capturePhoto() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            photofFile = createTemporaryFIle("picture", "jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (photofFile != null) {

        }
        imageURI = Uri.fromFile(photofFile);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageURI);
        startActivityForResult(cameraIntent, REQUEST_CAPTURE);

    }

    private void extraireImage() {
        this.getContentResolver().notifyChange(imageURI, null);
        ContentResolver cr = this.getContentResolver();
        Bitmap bitmap;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(cr, imageURI);
            picture.setImageBitmap(bitmap);
        } catch (Exception e) {
            Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show();
            Log.d("GRAB", "Failed to load", e);
        }
    }
*/
/*
    // set maximum scroll amount (based on center of image)
    int maxX = (int) ((pictureState.truewidth / 2) - (screenWidth / 2));
    int maxY = (int) ((pictureState.trueheight / 2) - (screenHeight / 2));

    // set scroll limits
    final int maxLeft = (maxX * -1);
    final int maxRight = maxX;
    final int maxTop = (maxY * -1);
    final int maxBottom = maxY;

    // set touchlistener
    picture.setOnTouchListener(new View.OnTouchListener()

    {
        float downX, downY;
        int totalX, totalY;
        int scrollByX, scrollByY;

    public boolean onTouch(View view, MotionEvent event) {
        float currentX, currentY;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                currentX = event.getX();
                currentY = event.getY();
                scrollByX = (int) (downX - currentX);
                scrollByY = (int) (downY - currentY);

                // scrolling to left side of image (pic moving to the right)
                if (currentX > downX) {
                    if (totalX == maxLeft) {
                        scrollByX = 0;
                    }
                    if (totalX > maxLeft) {
                        totalX = totalX + scrollByX;
                    }
                    if (totalX < maxLeft) {
                        scrollByX = maxLeft - (totalX - scrollByX);
                        totalX = maxLeft;
                    }
                }

                // scrolling to right side of image (pic moving to the left)
                if (currentX < downX) {
                    if (totalX == maxRight) {
                        scrollByX = 0;
                    }
                    if (totalX < maxRight) {
                        totalX = totalX + scrollByX;
                    }
                    if (totalX > maxRight) {
                        scrollByX = maxRight - (totalX - scrollByX);
                        totalX = maxRight;
                    }
                }

                // scrolling to top of image (pic moving to the bottom)
                if (currentY > downY) {
                    if (totalY == maxTop) {
                        scrollByY = 0;
                    }
                    if (totalY > maxTop) {
                        totalY = totalY + scrollByY;
                    }
                    if (totalY < maxTop) {
                        scrollByY = maxTop - (totalY - scrollByY);
                        totalY = maxTop;
                    }
                }

                // scrolling to bottom of image (pic moving to the top)
                if (currentY < downY) {
                    if (totalY == maxBottom) {
                        scrollByY = 0;
                    }
                    if (totalY < maxBottom) {
                        totalY = totalY + scrollByY;
                    }
                    if (totalY > maxBottom) {
                        scrollByY = maxBottom - (totalY - scrollByY);
                        totalY = maxBottom;
                    }
                }

                ImageView_BitmapView.scrollBy(scrollByX, scrollByY);
                downX = currentX;
                downY = currentY;
                break;

        }

        return true;
    }
});*/
}

