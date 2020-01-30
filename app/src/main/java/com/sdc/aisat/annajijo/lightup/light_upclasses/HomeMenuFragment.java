package com.sdc.aisat.annajijo.lightup.light_upclasses;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.sdc.aisat.annajijo.lightup.R;
import com.sdc.aisat.annajijo.lightup.StaticClass;
import com.sdc.aisat.annajijo.lightup.authentication.LoginPage;
import com.sdc.aisat.annajijo.lightup.my_class.PostRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

import static android.content.ContentValues.TAG;
import static com.google.firebase.database.ServerValue.TIMESTAMP;


public class HomeMenuFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    private String mText;
    private int mColor;
    private View mContent;
    private TextView mTextView;
    int flag = 0;
    //For bluetooth
    Button btnOn, btnOff;
    TextView txtArduino;
    Handler h;

    final int RECIEVE_MESSAGE = 1;        // Status  for Handler
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder sb = new StringBuilder();

    private ConnectedThread mConnectedThread;

    // SPP UUID service
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // MAC-address of Bluetooth module (you must edit this line)
    private static String address = "98:D3:31:50:24:34";
    //Firebase
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    ///////////
    Button startButton;

    FirebaseDatabase db;
    DatabaseReference ref;

    PostRecord postRecord;

    String stringKeyPost, stringUidPost, stringBloodGroupPost, stringDistancePost, stringCaloryPost,
            stringDatePost, stringTimePost, stringAveragePost, stringMaxPost;
    EditText key, unitsRequiredET, contactNoET, instructionET;

    ArrayList<PostRecord> postRecordArrayList;
     String stringUid;

    // newInstance constructor for creating fragment with arguments
    public static HomeMenuFragment newInstance(int page, String title,int color) {
        Log.e("newInstance","1");
        HomeMenuFragment fragmentFirst = new HomeMenuFragment();
        Bundle args = new Bundle();
        args.putInt("Arg-Page", page);
        args.putString("Arg-Text", title);
        args.putInt("Arg-Color",color);
        fragmentFirst.setArguments(args);

        return fragmentFirst;
    }
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        Log.e("Oncreate","2");
//        super.onCreate(savedInstanceState);
//        page = getArguments().getInt("Arg-Page", 1);
//        title = getArguments().getString("Arg-Text");
//        mColor = getArguments().getInt("ARG_Color");
//
//    }



    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("onCreateView","3");
        android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
        if(fm.getBackStackEntryCount()>0) {
            fm.popBackStack();
        }
        View view = inflater.inflate(R.layout.home_menu_fragment, container, false);
//        TextView calLabel = (TextView) view.findViewById(R.id.calLabel);
//        calLabel.setText(page + " -- " + title);
   final ProgressBar simpleProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mTextView = (TextView)view.findViewById(R.id.CaloryText);
        startButton = (Button) view.findViewById(R.id.startButton);

        /////////
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseAuth.getCurrentUser() == null){

            startActivity(new Intent(getContext(), LoginPage.class));
        }else {
            stringUid = firebaseUser.getUid();//
        }


        // perform click event on button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //////////
                stringDistancePost = txtArduino.getText().toString().trim();
                stringCaloryPost = mTextView.getText().toString().trim();

                //////////

/////////////insert//////////String name, String email, String currentgrp, String uid, String push, int units, String blood, String urgency, String country, String state, String city, String hospital, String relation, String contact, String info,int volunteer
        //        postRecord = new PostRecord(StaticClass.personalRecord.getFirstName(),StaticClass.personalRecord.getEmail(),FirebaseAuth.getInstance().getCurrentUser().getUid(),FirebaseDatabase.getInstance().getReference().child("Post").push().getKey(),Integer.parseInt( stringDistancePost),Integer.parseInt(stringCaloryPost),stringDatePost,stringTimePost);
//                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//                Date date = new Date(System.currentTimeMillis());
//                stringDatePost = dateFormat.format(date).toString();

                //stringDatePost = String.valueOf(Timestamp.valueOf(String.valueOf(SystemClock.elapsedRealtime())));
               //stringTimePost =String.valueOf(TIMESTAMP);
                Log.d("home" , "email :" + "hiiii");
                postRecord = new PostRecord(StaticClass.personalRecord.getFirstName(),StaticClass.personalRecord.getEmail(),FirebaseAuth.getInstance().getCurrentUser().getUid(),FirebaseDatabase.getInstance().getReference().child("Post").push().getKey(),Float.valueOf( stringDistancePost),Float.valueOf(stringCaloryPost),stringDatePost,stringTimePost);

                FirebaseDatabase.getInstance().getReference().child("Post").child(postRecord.getPush()).setValue(postRecord);


                Toast.makeText(getContext(), "Posted", Toast.LENGTH_SHORT).show();

//                ref.child(stringUidPost).setValue(postRecord);
/////////////insert/////////////

            }
        });
//        startButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // visible the progress bar
//
//                if(flag == 0) {
//                    simpleProgressBar.setVisibility(View.VISIBLE);
//                    startButton.setText("STOP");
//                    flag = 1;
//                }    else{
//                    simpleProgressBar.setVisibility(View.INVISIBLE);
//                    startButton.setText("START");
//                    flag = 0;
//                }
//            }
//        });

        btnOn = (Button) view.findViewById(R.id.btnOn);                // button LED ON
        btnOff = (Button) view.findViewById(R.id.btnOff);                // button LED OFF
        txtArduino = (TextView) view.findViewById(R.id.txtArduino);      // for display the received data from the Arduino

        h = new Handler() {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case RECIEVE_MESSAGE:                                                   // if receive massage
                        byte[] readBuf = (byte[]) msg.obj;
                        String strIncom = new String(readBuf, 0, msg.arg1);                 // create string from bytes array
                        sb.append(strIncom);
                        String sbprint =  sb.toString();            // extract string
                        // sb.delete(0, sb.length());                                      // and clear
                        sbprint = sb.toString();
                        txtArduino.setText(sbprint);            // update TextView
                        btnOff.setEnabled(true);
                        btnOn.setEnabled(true);
                        // append string
//                        int endOfLineIndex = sb.indexOf("\r\n");                            // determine the end-of-line
//                        if (endOfLineIndex > 0) {                                            // if end-of-line,
//
//                        }
                        Log.d(TAG, "...String:"+ sb.toString() +  "Byte:" + msg.arg1 +".....");
                        break;
                }
            };
        };
        //to remove...
        txtArduino.setText("20");
        ///to
        btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter
        checkBTState();

        btnOn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnOn.setEnabled(false);
                btnOff.setEnabled(true);
                mConnectedThread.write("1");    // Send "1" via Bluetooth
                //Toast.makeText(getBaseContext(), "Turn on LED", Toast.LENGTH_SHORT).show();
            }
        });

        btnOff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnOff.setEnabled(false);
                btnOn.setEnabled(true);
                mConnectedThread.write("0");    // Send "0" via Bluetooth
                //Toast.makeText(getBaseContext(), "Turn off LED", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


   @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("onViewCreated","4");
        // retrieve text and color from bundle or savedInstanceState
        if (savedInstanceState == null) {
            Bundle args = getArguments();
            mText = args.getString("Arg-Text");
            mColor = args.getInt("Arg-Color");
        } else {
            mText = savedInstanceState.getString("Arg-Text");
            mColor = savedInstanceState.getInt("Arg-Color");
        }

        // initialize views
        mContent = view.findViewById(R.id.home_menu_fragment);
        mTextView = (TextView) view.findViewById(R.id.CaloryText);

        // set text and background color
       // mTextView.setText(mText);
      mContent.setBackground(getResources().getDrawable(R.drawable.b3));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("Arg-Text", mText);
        outState.putInt("Arg-Color", mColor);
        super.onSaveInstanceState(outState);
    }



    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        if(Build.VERSION.SDK_INT >= 10){
            try {
                final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[] { UUID.class });
                return (BluetoothSocket) m.invoke(device, MY_UUID);
            } catch (Exception e) {
                Log.e(TAG, "Could not create Insecure RFComm Connection",e);
            }
        }
        return  device.createRfcommSocketToServiceRecord(MY_UUID);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "...onResume - try connect...");

        // Set up a pointer to the remote node using it's address.
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        // Two things are needed to make a connection:
        //   A MAC address, which we got above.
        //   A Service ID or UUID.  In this case we are using the
        //     UUID for SPP.

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
        }

        // Discovery is resource intensive.  Make sure it isn't going on
        // when you attempt to connect and pass your message.
        btAdapter.cancelDiscovery();

        // Establish the connection.  This will block until it connects.
        Log.d(TAG, "...Connecting...");
        try {
            btSocket.connect();
            Log.d(TAG, "....Connection ok...");
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
                errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
            }
        }

        // Create a data stream so we can talk to server.
        Log.d(TAG, "...Create Socket...");

        mConnectedThread = new ConnectedThread(btSocket);
        mConnectedThread.start();
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(TAG, "...In onPause()...");

        try     {
            btSocket.close();
        } catch (IOException e2) {
            errorExit("Fatal Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
        }
    }

    private void checkBTState() {
        // Check for Bluetooth support and then check to make sure it is turned on
        // Emulator doesn't support Bluetooth and will return null
        if(btAdapter==null) {
            errorExit("Fatal Error", "Bluetooth not support");
        } else {
            if (btAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth ON...");
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    private void errorExit(String title, String message){
        Toast.makeText(this.getContext(), title + " - " + message, Toast.LENGTH_LONG).show();
        getActivity().finish();
    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);        // Get number of bytes and message in "buffer"
                    h.obtainMessage(RECIEVE_MESSAGE, bytes, -1, buffer).sendToTarget();     // Send to message queue Handler
                } catch (IOException e) {
                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(String message) {
            Log.d(TAG, "...Data to send: " + message + "...");
            byte[] msgBuffer = message.getBytes();
            try {
                mmOutStream.write(msgBuffer);
            } catch (IOException e) {
                Log.d(TAG, "...Error data send: " + e.getMessage() + "...");
            }
        }
    }
}
