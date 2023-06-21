package com.example.yachtdicev2.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yachtdicev2.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SignUp_Activity extends AppCompatActivity {

    String TAG = "SignUp_Activity";
    String inputId, inputNickname,inputPassword, inputPasswordCheck;
    String idCheck, nicknameCheck, signupCheck;
    boolean idCheckValue, nicknameCheckValue,pwdCheckValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText inputSignupId = (EditText) findViewById(R.id.INPUT_signup_id);
        EditText inputSignupNickname = (EditText) findViewById(R.id.INPUT_signup_nickname);
        EditText inputSignupPassword = (EditText) findViewById(R.id.INPUT_signup_pwd);
        EditText inputSignupPasswordCheck = (EditText) findViewById(R.id.INPUT_signup_pwd_check);
        Button idCheck_bt = (Button) findViewById(R.id.ID_check);
        Button nickname_bt = (Button) findViewById(R.id.NICKNAME_check);
        Button passwordCheck_bt = (Button) findViewById(R.id.PASSWORD_check);
        Button signupEnd_bt = (Button) findViewById(R.id.SIGN_UP_END);


        // 아이디 중복체크
        idCheck_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputId = inputSignupId.getText().toString();
                Log.e(TAG,"아이디 입력 받아지나? " + inputId);

                new Thread(){
                    public void run(){

                        try {
                            Log.e(TAG,"전송되나?");

                            String postData = "inputId=" + inputId;

                            URL url = new URL("http://3.35.167.82/Id_check.php/");
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

                            SignUp_Activity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (idCheck.equals("0")){
                                        Log.e(TAG,"id체크 값 : " + idCheck);
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Activity.this);
                                        builder.setMessage("사용가능한 아이디입니다.");
                                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        }).setCancelable(false);
                                        builder.show();
                                        idCheckValue = true;

                                    }else{
                                        Log.e(TAG,"id체크 값 : " + idCheck);
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Activity.this);
                                        builder.setMessage("아이디가 중복되었습니다.");
                                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        }).setCancelable(false);
                                        builder.show();
                                    }
                                }
                            });


                        } catch (MalformedURLException e) {
                            Log.e(TAG,"URL Error", e);
                            e.printStackTrace();
                        } catch (IOException e) {
                            Log.e(TAG,"InsertData: Error ",e);
                            e.printStackTrace();
                        }
                    }
                }.start();


            }
        });

        // 아이디입력 변경 확인
        inputSignupId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                idCheckValue = false;
                Log.e(TAG,"idCheckValue : " + idCheckValue);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // 닉네임 중복체크
        nickname_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputNickname = inputSignupNickname.getText().toString();
                Log.e(TAG,"닉네임 입력 받아지나? " + inputNickname);

                new Thread(){
                    public void run(){

                        Log.e(TAG,"전송되나?");

                        String postData = "inputNickname=" + inputNickname;

                        try {
                            URL url = new URL("http://3.35.167.82/nickname_check.php/");
                            HttpURLConnection http = (HttpURLConnection) url.openConnection();

                            http.setRequestMethod("POST");
                            http.setReadTimeout(5000);
                            http.setConnectTimeout(5000);

                            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                            http.connect();

                            OutputStream outputStream = http.getOutputStream();
                            outputStream.write(postData.getBytes("UTF-8"));
                            Log.e(TAG,"http 전송할 데이타 : " + postData);
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

                            nicknameCheck = sb.toString();

                            SignUp_Activity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (nicknameCheck.equals("0")){
                                        Log.e(TAG,"nickname체크 값 : " + nicknameCheck);
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Activity.this);
                                        builder.setMessage("사용가능한 닉네임입니다.");
                                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        }).setCancelable(false);
                                        builder.show();
                                        nicknameCheckValue = true;

                                    }else{
                                        Log.e(TAG,"nickname체크 값 : " + nicknameCheck);
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Activity.this);
                                        builder.setMessage("닉네임이 중복되었습니다.");
                                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        }).setCancelable(false);
                                        builder.show();
                                    }
                                }
                            });

                        } catch (MalformedURLException e) {
                            Log.e(TAG,"URL Error", e);
                            e.printStackTrace();
                        } catch (IOException e) {
                            Log.e(TAG,"InsertData: Error ",e);
                            e.printStackTrace();
                        }

                    }
                }.start();
            }
        });

        // 닉네임 입력 확인

        inputSignupNickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nicknameCheckValue = false;
                Log.e(TAG,"nickNameCheckValue : " + nicknameCheckValue);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // 비밀번호 일치 체크
        passwordCheck_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputPassword = inputSignupPassword.getText().toString();
                inputPasswordCheck = inputSignupPasswordCheck.getText().toString();
                Log.e(TAG,"패스워드 입력 받아지나? " + inputPassword);
                Log.e(TAG,"패스워드 체크 입력 받아지나? " + inputPasswordCheck);

                if(inputPassword != null && inputPasswordCheck != null
                        && inputPassword.length() >=4 && inputPasswordCheck.length() >= 4){

                    if(inputPassword.equals(inputPasswordCheck)){

                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Activity.this);
                        builder.setMessage("사용가능한 비밀번호입니다.");
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).setCancelable(false);
                        builder.show();
                        pwdCheckValue = true;

                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Activity.this);
                        builder.setMessage("비밀번호가 일치하지 않습니다.");
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).setCancelable(false);
                        builder.show();
                    }

                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Activity.this);
                    builder.setMessage("비밀번호에 빈값이 있거나 네글자 미만입니다.");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false);
                    builder.show();
                }
            }
        });

        inputSignupPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    pwdCheckValue = false;
                    Log.e(TAG,"pwdCheckValue : " + pwdCheckValue);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // 회원가입 완료
        signupEnd_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"입력받은 아이디 : " + inputId);
                Log.e(TAG,"입력받은 닉네임 : " + inputNickname);
                Log.e(TAG,"입력받은 패스워드 : " + inputPassword);
                Log.e(TAG,"입력받은 패스워드 체크 : " + inputPasswordCheck);




                new Thread(){

                    public void run(){

                        String postData = "inputId=" + inputId + "&" + "inputNickname=" + inputNickname + "&" + "inputPassword=" + inputPassword;


                        try {
                            Log.e(TAG, "시작되나?");
                            URL url = new URL("http://3.35.167.82/signup_end.php/");
                            HttpURLConnection http = (HttpURLConnection) url.openConnection();

                            http.setRequestMethod("POST");
                            http.setReadTimeout(5000);
                            http.setConnectTimeout(5000);

                            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                            http.connect();

                            OutputStream outputStream = http.getOutputStream();
                            outputStream.write(postData.getBytes("UTF-8"));
                            Log.e(TAG,"http 전송할 데이타 : " + postData);
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

                            signupCheck = sb.toString();

                            SignUp_Activity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    if (inputId != null && inputId.length() >= 3
                                            && inputNickname != null && inputNickname.length() >= 2
                                            && inputPassword != null && inputPassword.length() >= 4
                                            && inputPasswordCheck != null && inputPasswordCheck.length() >= 4
                                            && idCheckValue && nicknameCheckValue && pwdCheckValue){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Activity.this);
                                        builder.setMessage("회원가입이 완료되었습니다.");
                                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                                Intent intent = new Intent(SignUp_Activity.this, Login_Activity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }).setCancelable(false);
                                        builder.show();

                                    }else if(inputId == null || inputId.length() < 3){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Activity.this);
                                        builder.setMessage("아이디 중복체크를 해주십시오.");
                                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        }).setCancelable(false);
                                        builder.show();

                                    }else if(inputNickname == null || inputNickname.length() < 2){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Activity.this);
                                        builder.setMessage("닉네임 중복체크를 해주십시오.");
                                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        }).setCancelable(false);
                                        builder.show();

                                    }else if(inputPassword == null || inputPasswordCheck ==null ||
                                            inputPassword.length() < 4 || inputPasswordCheck.length() < 4){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Activity.this);
                                        builder.setMessage("패스워드 일치 체크를 해주십시오.");
                                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        }).setCancelable(false);
                                        builder.show();

                                    }else if(signupCheck.equals("0")){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Activity.this);
                                        builder.setMessage("회원가입에 실패했습니다.");
                                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        }).setCancelable(false);
                                        builder.show();

                                    }else if(!idCheckValue){

                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Activity.this);
                                        builder.setMessage("아이디 중복확인을 해주십시오.")
                                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.dismiss();
                                                    }
                                                }).setCancelable(false);
                                        builder.show();

                                    }else if(!nicknameCheckValue){

                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Activity.this);
                                        builder.setMessage("닉네임 중복확인을 해주십시오.")
                                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.dismiss();
                                                    }
                                                }).setCancelable(false);
                                        builder.show();

                                    }else if(!pwdCheckValue){

                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Activity.this);
                                        builder.setMessage("패스워드 일치확인을 해주십시오.")
                                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.dismiss();
                                                    }
                                                }).setCancelable(false);
                                        builder.show();

                                    }

                                }
                            });

                        } catch (MalformedURLException e) {
                            Log.e(TAG,"URL Error", e);
                            e.printStackTrace();
                        } catch (IOException e) {
                            Log.e(TAG,"InsertData: Error ",e);
                            e.printStackTrace();
                        }
                    }

                }.start();

            }
        });
    }
}