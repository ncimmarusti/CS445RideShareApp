package com.example.cs445rideshare;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.EditText;

public class PhoneNumberFormatting {

    // This method adds hyphens after the third and sixth characters
    public static void addPhoneNumberFormatting(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if (input.length() == 3 || input.length() == 7) {
                    // Add hyphen at appropriate positions
                    input += "-";
                    editText.setText(input);
                    editText.setSelection(input.length());
                }
            }
        });

        // Add input filter to limit length to 12 characters (including hyphens)
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
    }
}
