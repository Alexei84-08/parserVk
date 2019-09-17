package com.company;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField {

    @Override
    public void replaceText(int start, int end, String text) {
        String oldValue = getText();

        if (validate(text)){
            super.replaceText(start, end, text);
            String nawText = super.getText();
            if (!validate(nawText)){
                super.setText(oldValue);
            }
        }
    }

    private boolean validate(String text){
        String numberRegEx = "[0-9].*";
        return ("".equals(text)||text.matches(numberRegEx));
    }
}
