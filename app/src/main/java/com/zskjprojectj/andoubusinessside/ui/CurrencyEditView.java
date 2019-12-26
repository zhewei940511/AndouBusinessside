package com.zskjprojectj.andoubusinessside.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import java.text.NumberFormat;

public class CurrencyEditView extends AppCompatEditText {

    private String current = "";

    public CurrencyEditView(Context context) {
        super(context);
        init();
    }

    public CurrencyEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CurrencyEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().equals(current)) {
                    removeTextChangedListener(this);
                    String cleanString = s.toString()
                            .replaceAll("[$,.]", "")
                            .replaceAll("￥", "")
                            .replaceAll("\\s+", "");
                    if (cleanString.length() != 0) {
                        try {
                            String currencyFormat = "￥ ";
                            double parsed;
                            int parsedInt;
                            String formatted;
                            parsed = Double.parseDouble(cleanString);
                            formatted = NumberFormat.getCurrencyInstance().format((parsed / 100))
                                    .replace(NumberFormat.getCurrencyInstance().getCurrency().getSymbol(), currencyFormat);
                            current = formatted;
                            setText(formatted.replaceAll(",", ""));
                            setSelection(getText().length());
                        } catch (NumberFormatException e) {

                        }
                    }

                    addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
