package stream.customalertapp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import stream.customalert.CustomAlertDialogue;
import stream.customalert.DialogListAdapter;
import stream.customalert.ItemInfo;
import stream.custombutton.CustomButton;

public class MainActivity extends AppCompatActivity{

    ImageView mBackground;
    CardView mCardView;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();

        mBackground = findViewById(R.id.background);
        mCardView = findViewById(R.id.cardview);

        //Simple Alert - a simple popup message.
        CustomButton btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomAlertDialogue.Builder alert = new CustomAlertDialogue.Builder(MainActivity.this)
                        .setStyle(CustomAlertDialogue.Style.DIALOGUE)
                        .setTitle("Custom Alert")
                        .setMessage("This is a long description to test the dialogue's text wrapping functionality")
                        .setNegativeText("OK")
                        .setNegativeColor(R.color.negative)
                        .setNegativeTypeface(Typeface.DEFAULT_BOLD)
                        .setOnNegativeClicked(new CustomAlertDialogue.OnNegativeClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                dialog.dismiss();
                            }
                        })
                        .setDecorView(getWindow().getDecorView())
                        .build();
                alert.show();
            }
        });

        //Confirmation Alert - a popup dialogue with two customizable choices.
        CustomButton btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomAlertDialogue.Builder alert = new CustomAlertDialogue.Builder(MainActivity.this)
                        .setStyle(CustomAlertDialogue.Style.DIALOGUE)
                        .setCancelable(false)
                        .setTitle("Delete Items")
                        .setMessage("Delete all completed items?")
                        .setPositiveText("Confirm")
                        .setPositiveColor(R.color.negative)
                        .setPositiveTypeface(Typeface.DEFAULT_BOLD)
                        .setOnPositiveClicked(new CustomAlertDialogue.OnPositiveClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                dialog.dismiss();
                                Toast.makeText(mContext, "Items Deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeText("Cancel")
                        .setNegativeColor(R.color.positive)
                        .setOnNegativeClicked(new CustomAlertDialogue.OnNegativeClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                dialog.dismiss();
                            }
                        })
                        .setDecorView(getWindow().getDecorView())
                        .build();
                alert.show();
            }
        });

        //Selector - a scrollable list of options.
        CustomButton btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<ItemInfo> list1 = new ArrayList<>();
                list1.add(new ItemInfo("Choice 2","2"));
                list1.add(new ItemInfo("Choice 3","3"));
                list1.add(new ItemInfo("Choice 4","4"));
                list1.add(new ItemInfo("Choice 5","5"));
                list1.add(new ItemInfo("Choice 6","6"));
                list1.add(new ItemInfo("Choice 7","7"));
                list1.add(new ItemInfo("Choice 8","8"));
                list1.add(new ItemInfo("Choice 9","9"));
                list1.add(new ItemInfo("Choice 10","10"));
                list1.add(new ItemInfo("Choice 11","11"));
                list1.add(new ItemInfo("Choice 12","12"));
                ArrayList<ItemInfo> list2 = new ArrayList<>();
                list2.add(new ItemInfo("Choice 13","13"));
                list2.add(new ItemInfo("Choice 14","14"));
                list2.add(new ItemInfo("Choice 15","15"));
                list2.add(new ItemInfo("Choice 16","16"));

                CustomAlertDialogue.Builder alert = new CustomAlertDialogue.Builder(MainActivity.this)
                        .setStyle(CustomAlertDialogue.Style.SELECTOR)
                        .setData(list1)
                        .setOnItemClickListener(new DialogListAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(CustomAlertDialogue dialog, ItemInfo itemInfo) {
                                Toast.makeText(mContext, "Selected " + itemInfo.getValue(), Toast.LENGTH_SHORT).show();

                                dialog.dismiss();
                            }

                        })
                        .setDecorView(getWindow().getDecorView())
                        .build();
                alert.show();
                Vibrator vibe = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(30);
            }
        });

        //Action Sheet - a highly customizable bottom menu.
        CustomButton btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> other = new ArrayList<>();
                other.add("Copy");
                other.add("Forward");

                CustomAlertDialogue.Builder alert = new CustomAlertDialogue.Builder(MainActivity.this)
                        .setStyle(CustomAlertDialogue.Style.ACTIONSHEET)
                        .setTitle("Action Sheet")
                        .setTitleColor(R.color.text_default)
                        .setCancelText("More...")
                        .setOnCancelClicked(new CustomAlertDialogue.OnCancelClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                Vibrator vibe = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                                vibe.vibrate(10);
                                dialog.dismiss();
                                Handler handler = new Handler();
                                Runnable r = new Runnable() {
                                    public void run() {
                                        MoreSelector();
                                    }
                                };
                                handler.postDelayed(r, 50);
                            }
                        })
//                        .setData(other)
                        .setOnItemClickListener(new DialogListAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(CustomAlertDialogue dialog, ItemInfo itemInfo) {

                            }

//                            @Override
//                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                String selection = adapterView.getItemAtPosition(i).toString();
//                                Vibrator vibe = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
//                                vibe.vibrate(10);
//                                switch (selection)
//                                {
//                                    case "Copy":
////                                        CustomAlertDialogue.getInstance().dismiss();
//                                        Toast.makeText(mContext, "Copied", Toast.LENGTH_SHORT).show();
//                                        break;
//                                    case "Forward":
////                                        CustomAlertDialogue.getInstance().dismiss();
//                                        Toast.makeText(mContext, "Forwarded", Toast.LENGTH_SHORT).show();
//                                        break;
//                                }
//                            }
                        })
                        .setDecorView(getWindow().getDecorView())
                        .build();
                alert.show();
                Vibrator vibe = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(30);
            }
        });

        //Input Box - helps collect user input. Can be used as a contact/feedback form.
        CustomButton btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> lineHint = new ArrayList<>();
                lineHint.add("Username");
                lineHint.add("Email Address");
                lineHint.add("Name");
                lineHint.add("Zip Code");

                ArrayList<String> lineText = new ArrayList<>();
                lineText.add("sampleuser");
                lineText.add(null);
                lineText.add("Sample User");

                ArrayList<String> boxHint = new ArrayList<>();
                boxHint.add("Message");

                ArrayList<String> boxText = new ArrayList<>();
                boxText.add("BoxText");

                CustomAlertDialogue.Builder alert = new CustomAlertDialogue.Builder(MainActivity.this)
                        .setStyle(CustomAlertDialogue.Style.INPUT)
                        .setTitle("Submit Feedback")
                        .setMessage("We love to hear feedback! Please share your thoughts and comments:")
                        .setPositiveText("Submit")
                        .setPositiveColor(R.color.positive)
                        .setPositiveTypeface(Typeface.DEFAULT_BOLD)
                        .setOnInputClicked(new CustomAlertDialogue.OnInputClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog, ArrayList<String> inputList) {
                                Toast.makeText(mContext, "Sent", Toast.LENGTH_SHORT).show();
                                for (String input : inputList)
                                {
                                    Log.d("Input", input);
                                }
                                dialog.dismiss();
                            }
                        })
                        .setNegativeText("Cancel")
                        .setNegativeColor(R.color.negative)
                        .setOnNegativeClicked(new CustomAlertDialogue.OnNegativeClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                dialog.dismiss();
                            }
                        })
                        .setLineInputHint(lineHint)
                        .setLineInputText(lineText)
                        .setBoxInputHint(boxHint)
                        .setBoxInputText(boxText)
                        .setDecorView(getWindow().getDecorView())
                        .build();
                alert.show();
            }
        });
    }

    public void MoreSelector()
    {
        ArrayList<String> destructive = new ArrayList<>();
        destructive.add("Delete");
        ArrayList<ItemInfo> other = new ArrayList<>();
        other.add(new ItemInfo("Details","1Details"));

        CustomAlertDialogue.Builder alert = new CustomAlertDialogue.Builder(MainActivity.this)
                .setStyle(CustomAlertDialogue.Style.SELECTOR)
//                .setDestructive(destructive)
                .setData(other)
                .setOnItemClickListener(new DialogListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(CustomAlertDialogue dialog, ItemInfo itemInfo) {
                        String selection = itemInfo.getName().toString();
                        Vibrator vibe = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                        vibe.vibrate(10);
                        switch (selection)
                        {
                            case "Delete":
                                dialog.dismiss();
                                Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                                break;
                            case "Details":
                                dialog.dismiss();
                                Toast.makeText(mContext, "Details", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                })
                .setDecorView(getWindow().getDecorView())
                .build();
        alert.show();
    }

    //Hide background for screenshots.
    @SuppressWarnings("unused")
    public void HideBackground(boolean hide)
    {
        if (hide)
        {
            mBackground.setVisibility(View.GONE);
            mCardView.setVisibility(View.GONE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    HideBackground(false);
                }
            }, 5000);
        }
        else
        {
            mBackground.setVisibility(View.VISIBLE);
            mCardView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPause() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            //Removes flickering from setting window fullscreen
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                }
            });
        }
        super.onPause();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            if (hasFocus) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
        super.onWindowFocusChanged(hasFocus);
    }
}
