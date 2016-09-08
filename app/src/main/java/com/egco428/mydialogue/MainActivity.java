package com.egco428.mydialogue;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private static final String[] CLUBS = {"Brownies", "Blueberry", "Cinnamoroll", "Chocolate", "Pancakes"};
    String msgSelected;
    ArrayList<Integer> msgMultSelected;
    Button buttonSimple;
    Button buttonList;
    Button buttonCustom;
    Button buttonMulti;
    Button buttonSingle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSimple = (Button) findViewById(R.id.btnSimple);
        buttonCustom = (Button) findViewById(R.id.btnCustom);
        buttonSingle = (Button) findViewById(R.id.btnSingle);
        buttonList = (Button) findViewById(R.id.btnLIst);
        buttonMulti = (Button) findViewById(R.id.btnMulti);


        buttonSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to have pizza?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", null);
                builder.create();
                builder.show();
            }
        });

        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select your favourite?");
                builder.setItems(CLUBS, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String selected = CLUBS[i];
                        Toast.makeText(getApplicationContext(), "You like" + selected, Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", null);
                builder.create();
                builder.show();
            }
        });

        buttonSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select your favourite?");
                builder.setSingleChoiceItems(CLUBS, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String selected = CLUBS[i];
                        Toast.makeText(getApplicationContext(), "You like" + selected, Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                });

                builder.setNegativeButton("No", null);
                builder.create();
                builder.show();
            }
        });

        buttonMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgMultSelected = new ArrayList<Integer>();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select your favourite?");
                builder.setMultiChoiceItems(CLUBS, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {

                        if (isChecked) {
                            msgMultSelected.add(i);
                        } else if (msgMultSelected.contains(i)) {
                            msgMultSelected.remove(Integer.valueOf(i));
                        }

                    }
                });
                        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                StringBuffer buffer = new StringBuffer();
                                for (Integer team : msgMultSelected) {
                                    buffer.append(" ");
                                    buffer.append(CLUBS[team]);
                                }
                                Toast.makeText(getApplicationContext(), "You like" + buffer.toString(), Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        });
                builder.setNegativeButton("No", null);
                builder.create();
                builder.show();
            }
        });
    }
}