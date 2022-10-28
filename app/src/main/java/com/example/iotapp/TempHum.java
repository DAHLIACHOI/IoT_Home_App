package com.example.iotapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TempHum extends AppCompatActivity {

    EditText send_editText;
    Button socketButton;
    TextView send_textView;
    TextView read_textView;
    private Socket client;
    private DataOutputStream dataOutput;
    private DataInputStream dataInput;
    private static String SERVER_IP = "192.168.0.3";
    private static String CONNECT_MSG = "connect";
    private static String STOP_MSG = "stop";

    private static int BUF_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_hum);
        send_textView = findViewById(R.id.tempText);
        read_textView = findViewById(R.id.humText);
        socketButton = findViewById(R.id.socketbutton);

        Connect connect = new Connect();
        connect.execute(CONNECT_MSG);

//        socketButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Connect connect = new Connect();
//                connect.execute(CONNECT_MSG);
//            }
//        });
    }

    private class Connect extends AsyncTask<String, String, Void>{
        private String output_message;
        private String input_message;
        private String input_message2;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                client = new Socket(SERVER_IP, 8088);
                dataOutput = new DataOutputStream(client.getOutputStream());
                dataInput = new DataInputStream(client.getInputStream());
                output_message = strings[0];
                dataOutput.writeUTF(output_message);
            } catch (UnknownHostException e) {
                String str = e.getMessage().toString();
                Log.w("discnt", str + " 1");
            } catch (IOException e) {
                String str = e.getMessage().toString();
                Log.w("discnt", str + "2");
            }

            while (true){
                try {
                    byte[] buf = new byte[BUF_SIZE];
                    int read_Byte  = dataInput.read(buf);
                    int read_Byte2  = dataInput.read(buf);
                    input_message = new String(buf, 0, read_Byte);
                    input_message2 = new String(buf, 0, read_Byte2);
                    if (!input_message.equals(STOP_MSG)){
                        publishProgress(input_message, input_message2);
                    }
                    else{
                        break;
                    }
                    Thread.sleep(5);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
            }
        @Override
        protected void onProgressUpdate(String... params){
            send_textView.setText(""); // Clear the chat box
            send_textView.append(params[0] +"%");
            read_textView.setText(""); // Clear the chat box
            read_textView.append(params[1] );
        }
    }


    public void goToHome(View view) {   //뒤로가기 버튼 클릭 시
        finish();   //현재 액티비티 없애기
    }
}