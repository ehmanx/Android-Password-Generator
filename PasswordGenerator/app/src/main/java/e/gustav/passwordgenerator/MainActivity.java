package e.gustav.passwordgenerator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
    String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String allNumbers = "0123456789";
    String specialCharacters = "!#%&.,-_:;'<>|";
    String result = "";

    Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int pixelwidth = 40;
        
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView maxCharLable = new TextView(this);
        maxCharLable.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        maxCharLable.setText("How many characters?");
        maxCharLable.setTextSize(18);
        layout.addView(maxCharLable);

        final Spinner maxCharSpinner = new Spinner(this);
        maxCharSpinner.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Integer[] items = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
        maxCharSpinner.setAdapter(adapter);
        layout.addView(maxCharSpinner);

        final CheckBox upperBox = new CheckBox(this);
        upperBox.setText("Uppercase characters");
        upperBox.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(upperBox);
        final CheckBox numBox = new CheckBox(this);
        numBox.setText("Numbers");
        numBox.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(numBox);
        final CheckBox specialBox = new CheckBox(this);
        specialBox.setText("Special charecters");
        specialBox.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(specialBox);

        final TextView resultText = new TextView(this);
        resultText.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        resultText.setPadding(pixelwidth,0,0,0);
        resultText.setText("");
        resultText.setTextSize(18);
        layout.addView(resultText);

        Button genPassButton = new Button(this);
        genPassButton.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        genPassButton.setText("Generate Password");
        genPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int numberLenght = ((int) maxCharSpinner.getSelectedItem());
                result = generatePassword(numberLenght,upperBox.isChecked(), numBox.isChecked(), specialBox.isChecked());

                resultText.setText(result);
            }
        });
        layout.addView(genPassButton);
        final Button copyBtn = new Button(this);
        copyBtn.setText("Copy password to clipboard");
        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("result", result);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(c, "Password copied to clipboard", Toast.LENGTH_LONG).show();
                System.out.println(result);
            }
        });
        layout.addView(copyBtn);
        setContentView(layout);
        
    }
    public String generatePassword(int length, boolean upper, boolean num, boolean special){

        String res = "";

        String passwordBuilder = lowerAlphabet;

        if(upper)
            passwordBuilder += upperAlphabet;
        if(num)
            passwordBuilder +=allNumbers;
        if(special)
            passwordBuilder += specialCharacters;

        for(int i = 0; i < length; i++){
            int index = new Random().nextInt(passwordBuilder.length());
            res += passwordBuilder.charAt(index);
        }

        return res;

    }
}
