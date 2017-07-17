package com.example.watcharac.laser;

import android.text.InputType;
import android.text.Spanned;
import android.text.method.NumberKeyListener;

/**
 * Created by watchara.c on 7/17/2017.
 */

public class LaserNumberFilter extends NumberKeyListener {
    public static final char[] ACCEPTEDCHARS = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r'
            , 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'
            , 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
            , '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-'};

    @Override
    public int getInputType() {
        return InputType.TYPE_CLASS_TEXT;
    }

    @Override
    protected char[] getAcceptedChars() {
        return ACCEPTEDCHARS;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {


        if (dend == dstart) {


            String destTxt = dest.toString();
            String resultingTxt = destTxt.substring(0, dstart) + source.subSequence(start, end) + destTxt.substring(dend);

            // Laser number must match xxx-xxxxxxx-xx
//            if (!resultingTxt.matches("^[a-zA-Z]{1,1}([a-zA-Z]{1,1}(\\d{1,1}(\\-(\\d{1,1}(\\d{1,1}(\\d{1,1}(\\d{1,1}(\\d{1,1}(\\d{1,1}(\\d{1,1}(\\-(\\d{1,1}(\\d{1,1}?)?)?)?)?)?)?)?)?)?)?)?)?)?")) {
//            if (!resultingTxt.matches("^[a-zA-Z]{1,1}([a-zA-Z]{1,1}([0-9-]{1,12}?)?)?")) {

            if (!resultingTxt.matches("^[a-zA-Z]{1,1}([a-zA-Z]{1,1}([0-9-]{1,12}?)?)?")) {
//            if (!resultingTxt.matches("^[a-zA-Z]{1,2}([0-9-]{1,12}?)?")) {
                if(destTxt.matches("[0-9-]{1,12}")){
                    if(!source.equals("")&&source.toString().matches("[a-zA-Z]")){
                    }
                    else
                        return "";
                }
                else
                    return "";
//                if (!(destTxt.matches("\\d-") && Character.isLetter(source.charAt(0))))
//                    return "";
            }


        }

        return null;
    }
}