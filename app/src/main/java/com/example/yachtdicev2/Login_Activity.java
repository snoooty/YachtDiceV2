package com.example.yachtdicev2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Login_Activity extends AppCompatActivity {

    String TAG = "Login_Activity";
    String idCheck,loginUserNickname;
//    GoogleSignInAccount currentUser;
    boolean login;



    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        EditText inputLogin = (EditText) findViewById(R.id.INPUT_login_id);
        EditText inputPassword = (EditText) findViewById(R.id.INPUT_login_pwd);
        Button login_bt = (Button) findViewById(R.id.move_LOGIN);
        Button signupActivity_bt = (Button) findViewById(R.id.move_SIGN_UP);
//        Button kakaoLogin_bt = (Button) findViewById(R.id.kakao_login);
        SignInButton googleLogin_bt = (SignInButton) findViewById(R.id.google_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                requestEmail().
                build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        // 아이디 입력시 세글자 미만이면 알림창 띄우기
        inputLogin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    if(inputLogin.length() < 3){
                        AlertDialog.Builder builder = new AlertDialog.Builder(Login_Activity.this);
                        builder.setMessage("ID는 세글자 이상이어야 합니다.");
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                    }
                }
            }


        });

        // 회원가입 액티비티로 이동
        signupActivity_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e(TAG,"회원가입 클릭되나?");
                Intent intent = new Intent(Login_Activity.this, SignUp_Activity.class);
                startActivity(intent);
            }
        });

        // 아이디가 세글자 이상이고 패스워드가 존재하면 select 액티비티로 이동
        login_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.e(TAG,"login 클릭되나?");

                new Thread(){

                    public void run(){

                        URL url = null;
                        try {
                            Log.e(TAG,"Thread 들어가나?");
                            String postData = "inputId=" + inputLogin.getText().toString() + "&" + "inputPassword=" + inputPassword.getText().toString();

                            url = new URL("http://3.36.58.151/login.php/");
                            HttpURLConnection http = (HttpURLConnection) url.openConnection();


                            http.setRequestMethod("POST");      // 전송방식은 POST
                            http.setReadTimeout(5000);
                            http.setConnectTimeout(5000);

                            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                            http.connect();

                            OutputStream outputStream = http.getOutputStream();
                            outputStream.write(postData.getBytes("UTF-8"));
                            Log.e(TAG,"http 전송할 데이터 : " + postData);
                            outputStream.flush();
                            outputStream.close();

                            int responseStatusCode = http.getResponseCode();
                            Log.e(TAG,"POST response code- " + responseStatusCode);

                            InputStream inputStream;

                            if (responseStatusCode == http.HTTP_OK){
                                inputStream = http.getInputStream();
                                Log.e(TAG,"php정상");
                            }else {
                                inputStream = http.getErrorStream();
                                Log.e(TAG,"php비정상");
                            }

                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                            StringBuilder sb = new StringBuilder();
                            String line = null;

                            while((line = bufferedReader.readLine()) != null){
                                sb.append(line);
                            }

                            bufferedReader.close();

                            Log.e(TAG,"php 값 : " + sb.toString());

                            idCheck = sb.toString();

                            Login_Activity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    if (inputLogin.length() >= 3 && inputPassword.length() != 0 && idCheck.equals("0")){
                                        Log.e(TAG,"아이디 세글자이상, 비밀번호 존재하나?");


                                        new Thread(){
                                            public void run(){

                                                try {


                                                    String postData = "inputId=" + inputLogin.getText().toString();

                                                    URL url = new URL("http://3.36.58.151/nickname_get.php/");
                                                    HttpURLConnection http = (HttpURLConnection) url.openConnection();

                                                    http.setRequestMethod("POST");      // 전송방식은 POST
                                                    http.setReadTimeout(5000);
                                                    http.setConnectTimeout(5000);

                                                    http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                                                    http.connect();

                                                    OutputStream outputStream = http.getOutputStream();
                                                    outputStream.write(postData.getBytes("UTF-8"));
                                                    Log.e(TAG,"http 전송할 데이터 : " + postData);
                                                    outputStream.flush();
                                                    outputStream.close();

                                                    int responseStatusCode = http.getResponseCode();
                                                    Log.e(TAG,"POST response code- " + responseStatusCode);

                                                    InputStream inputStream;

                                                    if (responseStatusCode == http.HTTP_OK){
                                                        inputStream = http.getInputStream();
                                                        Log.e(TAG,"php정상");
                                                    }else {
                                                        inputStream = http.getErrorStream();
                                                        Log.e(TAG,"php비정상");
                                                    }

                                                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                                                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                                                    StringBuilder sb = new StringBuilder();
                                                    String line = null;

                                                    while((line = bufferedReader.readLine()) != null){
                                                        sb.append(line);
                                                    }

                                                    bufferedReader.close();

                                                    Log.e(TAG,"php 값 : " + sb.toString());

                                                    loginUserNickname = sb.toString();

                                                    Intent intent = new Intent(Login_Activity.this, Select_Activity.class);
                                                    intent.putExtra("loginNickname", loginUserNickname);
                                                    Log.e(TAG,"put 값 : " + loginUserNickname);
                                                    startActivity(intent);
                                                    finish();

                                                } catch (MalformedURLException e) {
                                                    e.printStackTrace();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }

                                            }
                                        }.start();


                                    }else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Login_Activity.this);
                                        builder.setMessage("ID나 PASSWORD를 확인해주십시오.");
                                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        });
                                        builder.show();
                                    }

                                }
                            });

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }.start();

            }
        });

        // 구글 로그인 버튼
        googleLogin_bt.setOnClickListener(view -> {
            onClick(view);
        });



    }


    public void onClick(View v){
        if (v.getId() == R.id.google_login) {
            signIn();
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        login = true;
    }

    private void updateUI(GoogleSignInAccount account) {

        String userName = account.getDisplayName();
        String userGivenName = account.getGivenName();
        String userFamilyName = account.getFamilyName();
        String userEmail = account.getEmail();
        String userId = account.getId();
        Uri userPhoto = account.getPhotoUrl();

        Log.e(TAG,"handleSignInResult:userName , " + userName);
        Log.e(TAG,"handleSignInResult:userGivenName , " + userGivenName);
        Log.e(TAG,"handleSignInResult:userFamilyName , " + userFamilyName);
        Log.e(TAG,"handleSignInResult:userEmail , " + userEmail);
        Log.e(TAG,"handleSignInResult:userId , " + userId);
        Log.e(TAG,"handleSignInResult:userPhoto , " + userPhoto);

    }

    @Override
    public void onActivityResult(int reqestCode, int resultCode, Intent data){
        super.onActivityResult(reqestCode, resultCode, data);

        if (reqestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignResult(task);
        }
    }

    private void handleSignResult(Task<GoogleSignInAccount> completedTask){
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            updateUI(account);

            Intent intent = new Intent(Login_Activity.this, Select_Activity.class);
            intent.putExtra("loginNickname",account.getDisplayName());
            startActivity(intent);
            finish();

        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}