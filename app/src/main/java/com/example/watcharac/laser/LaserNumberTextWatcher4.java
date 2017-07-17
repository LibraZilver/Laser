package com.example.watcharac.laser;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by watchara.c on 7/17/2017.
 */

public class LaserNumberTextWatcher4 implements TextWatcher {

    //we need to know if the user is erasing or inputing some new character
    private boolean backspacingFlag = false;
    //we need to block the :afterTextChanges method to be called again after we just replaced the EditText text
    private boolean editedFlag = false;
    //we need to mark the cursor position and restore it after the edition
    private int cursorComplement;

    private EditText ET;

    public LaserNumberTextWatcher4(EditText ET) {
        this.ET = ET;
    }

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
        //what matters are the Laser format beneath the mask, so we always work with a raw string with only [0-9] and a-zA-Z
        String laser = string.replaceAll("[^\\d\\w]", "");

        //if the text was just edited, :afterTextChanged is called another time... so we need to verify the flag of edition
        //if the flag is false, this is a original user-typed entry. so we go on and do some magic
        if (!editedFlag) {

            //we start verifying the worst case, many characters mask need to be added
            //example: AA1222222233 <- 10+ character already typed
            // masked: AA1-2222222-33
            if (laser.length() >= 10 && !backspacingFlag) {
                //we will edit. next call on this textWatcher will be ignored
                editedFlag = true;
                //here is the core. we substring the raw digits and add the mask as convenient
//
                String ans = laser.substring(0, 3) + "-" + laser.substring(3, 10) + "-" + laser.substring(10);
                ET.setText(ans);
                //we deliver the cursor to its original position relative to the end of the string
                ET.setSelection(ET.getText().length() - cursorComplement);

                //we end at the most simple case, when just one character mask is needed
                //example: AA122 <- 3+ character already typed
                // masked: AA1-22
            } else if (laser.length() >= 3 && !backspacingFlag) {
                editedFlag = true;

                String ans = laser.substring(0, 3) + "-" + laser.substring(3);
                ET.setText(ans);
                ET.setSelection(ET.getText().length() - cursorComplement);
            }
            // We just edited the field, ignoring this cicle of the watcher and getting ready for the next
        } else {
            editedFlag = false;
        }
    }

}
