package com.example.valentin.traitementimage;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class Camera {

    public static final int REQUEST_CAPTURE = 1;
    ImageView imageView;
    Uri imageURI;
    File photofFile;

    public Camera(){

    }

    public static boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAPTURE && resultCode == Activity.RESULT_OK) {
            extraireImage();
        }
    }

    private File createTemporaryFIle(String name, String extension)throws Exception{
        File temp = Environment.getExternalStorageDirectory();
        temp = new File(temp.getAbsolutePath() +"/.temp/");
        if (!temp.exists()){
            temp.mkdirs();
        }

        return File.createTempFile(name, extension, temp);
    }

    public void capturePhoto(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try{
            photofFile = createTemporaryFIle("picture", "jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (photofFile != null){

        }
        imageURI = Uri.fromFile(photofFile);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageURI);
        startActivityForResult(cameraIntent, REQUEST_CAPTURE);

    }

    private void extraireImage(){
        this.getContentResolver().notifyChange(imageURI, null);
        ContentResolver cr = this.getContentResolver();
        Bitmap bitmap;
        try
        {
            bitmap = MediaStore.Images.Media.getBitmap(cr, imageURI);
            imageView.setImageBitmap(bitmap);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show();
            Log.d("GRAB", "Failed to load", e);
        }

    }
}


