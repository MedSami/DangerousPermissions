package dangerouspermissions.medsamimejri.net.dangerouspermissions;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA=125;
    private static final int REQUEST_CONTACTS=225;
    private static final int REQUEST_STORAGE=325;

    private static final int TXT_CAMERA=1;
    private static final int TXT_CONTACTS=2;
    private static final int TXT_STORAGE=3;


    private PermissionUtil permissionUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionUtil=new PermissionUtil(this);
    }


    //CHECK FOR PERMISSION
    private int checkPermission(int permission){
        int status = PackageManager.PERMISSION_DENIED;

        switch(permission)
        {
            case TXT_CAMERA:
                status=ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                break;

            case TXT_STORAGE:
                status=ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;

            case TXT_CONTACTS:
                status=ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS);
                break;
        }

        return status;


    }
    //REQUEST NEW PERMISSION
    private void requestPermission(int permission)
    {
        switch (permission)
        {
            case TXT_CAMERA :
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);
                break;

            case TXT_STORAGE :
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_STORAGE);
                break;

            case TXT_CONTACTS :
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        REQUEST_CONTACTS);
                break;
        }



    }

    //Display Permission Explanation
    private void showPermissionExplanation(final int permission){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        if(permission==TXT_CAMERA){
            builder.setMessage("This app need to access your device Camera. Please Allow ");
            builder.setTitle("Camera Permission Needed..");
        }
        else if (permission ==TXT_CONTACTS)
        {
            builder.setMessage("This app need to access your device Contacts. Please Allow ");
            builder.setTitle("Camera Permission Needed..");

        }
        else if (permission ==TXT_STORAGE)
        {
            builder.setMessage("This app need to access your device Contacts. Please Allow ");
            builder.setTitle("Camera Permission Needed..");

        }
        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (permission==TXT_CAMERA)
                    requestPermission(TXT_CAMERA);
                else if (permission==TXT_STORAGE)
                    requestPermission(TXT_STORAGE);
                else if (permission==TXT_CONTACTS)
                    requestPermission(TXT_CONTACTS);
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
                   });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();


    }



    public void ContactPermission(View view) {

        if (checkPermission(TXT_CONTACTS)!=PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    MainActivity.this,Manifest.permission.READ_CONTACTS))
            {
                showPermissionExplanation(TXT_CONTACTS);
            }
            else if(!permissionUtil.checkPermissionPreference("contacts"))
            {
                requestPermission(TXT_CAMERA);
                permissionUtil.updatePermissionPreferences("contacts");
            }
            else
            {
                Toast.makeText(this,"Please allow contacts permission in your settings",Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri=Uri.fromParts("package",this.getPackageName(),null);
                intent.setData(uri);
                this.startActivity(intent);

            }
        }
        else {
            Toast.makeText(this,"You have contacts permission",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this,ResultActivity.class);
            intent.putExtra("message","You can now access to contacts..");
            startActivity(intent);

        }
    }

    public void StoragePermission(View view) {

        if (checkPermission(TXT_STORAGE)!=PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE))
            {
                showPermissionExplanation(TXT_STORAGE);
            }
            else if(!permissionUtil.checkPermissionPreference("storage"))
            {
                requestPermission(TXT_CAMERA);
                permissionUtil.updatePermissionPreferences("storage");
            }
            else
            {
                Toast.makeText(this,"Please allow Storage permission in your settings",Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri=Uri.fromParts("package",this.getPackageName(),null);
                intent.setData(uri);
                this.startActivity(intent);

            }
        }
        else {
            Toast.makeText(this,"You have Storage permission",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this,ResultActivity.class);
            intent.putExtra("message","You can now Write Storage..");
            startActivity(intent);

        }
    }

    public void CameraPermission(View view) {

        if (checkPermission(TXT_CAMERA)!=PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    MainActivity.this,Manifest.permission.CAMERA))
            {
                showPermissionExplanation(TXT_CAMERA);
            }
            else if(!permissionUtil.checkPermissionPreference("camera"))
            {
                requestPermission(TXT_CAMERA);
                permissionUtil.updatePermissionPreferences("camera");
            }
            else
            {
                Toast.makeText(this,"Please allow camera permission in your settings",Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri=Uri.fromParts("package",this.getPackageName(),null);
                intent.setData(uri);
                this.startActivity(intent);

            }
        }
        else {
            Toast.makeText(this,"You have camera permission",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this,ResultActivity.class);
            intent.putExtra("message","You can now take a picture and record video..");
            startActivity(intent);

        }


    }
}
