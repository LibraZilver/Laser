package com.example.watcharac.laser;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by watchara.c on 7/17/2017.
 */

public class LaserNumberTextWatcher implements TextWatcher {

    private boolean editedFlag;

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if(editedFlag) return;

        editedFlag = true;
        // removing old dashes
        StringBuilder lasertextshow = new StringBuilder();
        lasertextshow.append(s.toString().replace("-", ""));


        if (lasertextshow.length() > 3){
            lasertextshow.insert(3, "-");
        }
        if (lasertextshow.length() > 11){
            lasertextshow.insert(11, "-");
        }

        s.replace(0, s.length(), lasertextshow.toString());
        editedFlag = false;

    }
}
