package com.example.watcharac.laser;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.InputType;
import android.text.Layout;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText ET = (EditText) findViewById(R.id.editText_phone);
        ET.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        ET.addTextChangedListener(new PhoneNumberFormattingTextWatcher() {
            //we need to know if the user is erasing or inputing some new character
            private boolean backspacingFlag = false;
            //we need to block the :afterTextChanges method to be called again after we just replaced the EditText text
            private boolean editedFlag = false;
            //we need to mark the cursor position and restore it after the edition
            private int cursorComplement;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //we store the cursor local relative to the end of the string in the EditText before the edition
                cursorComplement = s.length() - ET.getSelectionStart();
                //we check if the user ir inputing or erasing a character
                if (count > after) {
                    backspacingFlag = true;
                } else {
                    backspacingFlag = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // nothing to do here =D
            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                //what matters are the phone digits beneath the mask, so we always work with a raw string with only digits
                string = " ss 1-111 1x";
//                String phone = string.replaceAll("[^\\d\\w]", "");
//                String phone = string.replaceAll("[^([a-zA-Z]{2}+[0-9]-[0-9]{7}-[0-9]{2})]", "");

//                String phone = string.replaceAll("[^(^(\\a{2})+([0-9-])*)]", "");
                String phone = string.replaceAll("[A-Z]{4}\\d+", "");

//                int cursorPosition = ET.getSelectionStart();
//                if (cursorPosition > 1) {
//                    Toast.makeText(MainActivity.this, "Number", Toast.LENGTH_SHORT).show();
//                    ET.setInputType(InputType.TYPE_CLASS_NUMBER);
//                    phone = string.replaceAll("(^[a-zA-Z]{2})[0-9-]*", "");
//                } else {
//                    Toast.makeText(MainActivity.this, "Text", Toast.LENGTH_SHORT).show();
//                    ET.setInputType(InputType.TYPE_CLASS_TEXT);
//
//                }

                //if the text was just edited, :afterTextChanged is called another time... so we need to verify the flag of edition
                //if the flag is false, this is a original user-typed entry. so we go on and do some magic
                if (!editedFlag) {

                    //we start verifying the worst case, many characters mask need to be added
                    //example: 999999999 <- 6+ digits already typed
                    // masked: (999) 999-999
                    if (phone.length() >= 6 && !backspacingFlag) {
                        //we will edit. next call on this textWatcher will be ignored
                        editedFlag = true;
                        //here is the core. we substring the raw digits and add the mask as convenient
                        String ans = "(" + phone.substring(0, 3) + ") " + phone.substring(3, 6) + "-" + phone.substring(6);
                        ET.setText(ans);
                        //we deliver the cursor to its original position relative to the end of the string
                        ET.setSelection(ET.getText().length() - cursorComplement);

                        //we end at the most simple case, when just one character mask is needed
                        //example: 99999 <- 3+ digits already typed
                        // masked: (999) 99
                    } else if (phone.length() >= 3 && !backspacingFlag) {
                        editedFlag = true;
                        String ans = "(" + phone.substring(0, 3) + ") " + phone.substring(3);
                        ET.setText(ans);
                        ET.setSelection(ET.getText().length() - cursorComplement);
                    }
                    // We just edited the field, ignoring this cicle of the watcher and getting ready for the next
                } else {
                    editedFlag = false;
                }
            }
        });
//
//        ET.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                int cursorPosition = ET.getSelectionStart();
//                if (cursorPosition > 1) {
//                    Toast.makeText(MainActivity.this, "Number", Toast.LENGTH_SHORT).show();
////                    ET.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
//                    ET.setInputType(InputType.TYPE_CLASS_NUMBER);
//                } else {
//                    Toast.makeText(MainActivity.this, "Text", Toast.LENGTH_SHORT).show();
////                    ET.setKeyListener(DigitsKeyListener.getInstance("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
//
//                    ET.setInputType(InputType.TYPE_CLASS_TEXT);
//                }
//            }
//        });


    }
}
