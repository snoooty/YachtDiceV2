package com.example.yachtdicev2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.yachtdicev2.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Mypage_Activity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount currentUser;
    String TAG = "Mypage_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setBackgroundDrawable(new PaintDrawable(Color.TRANSPARENT));

        // 타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mypage);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
//                requestIdToken(getString(R.string.default_web_client_id)).
        requestEmail().
                build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        int pointWidth = point.x; // 가로
        int pointHeight = point.y; // 세로

        int width = (int) (pointWidth * 0.6); // Display 가로 사이즈
        int height = (int) (pointHeight * 1.0);  // Display 높이 사이즈

        getWindow().getAttributes().width = width; // 가로 크기
        getWindow().getAttributes().height = height; // 세로 크기
        getWindow().getAttributes().gravity = Gravity.LEFT; // 위치 설정


        Button close_bt = (Button) findViewById(R.id.Mypage_Close);
        Button logout_bt = (Button) findViewById(R.id.Logout);


        logout_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getCurrentUserProfile();

                if(currentUser != null){
                    signOut();
                }else {
                    logOut();
                }
            }
        });

        close_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void getCurrentUserProfile(){
            currentUser = GoogleSignIn.getLastSignedInAccount(Mypage_Activity.this);
            String email = currentUser.getEmail();
            String familyName = currentUser.getFamilyName();
            String givenName = currentUser.getGivenName();
            String displayName = currentUser.getDisplayName();
            Uri photoUrl = currentUser.getPhotoUrl();

        Log.e(TAG,"current email : " + email);
        Log.e(TAG,"current familyName : " + familyName);
        Log.e(TAG,"current givenName : " + givenName);
        Log.e(TAG,"current displayName : " + displayName);
        Log.e(TAG,"current photoUrl : " + photoUrl);

    }

    private void signOut() {

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Log.e(TAG,"구글 로그아웃 작동하나?");
                        revokeAccess();

                            Intent intent = new Intent(Mypage_Activity.this, Login_Activity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                    }
                });

    }

    private void logOut(){
        Log.e(TAG,"로그아웃 기능하나?");
        Intent intent = new Intent(Mypage_Activity.this, Login_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void revokeAccess(){
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.e(TAG,"구글 로그인 연동 해제 되나?");
                    }
                });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}