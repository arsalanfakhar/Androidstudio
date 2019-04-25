package com.dev_x.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int a = 0;
    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        CheckBox creambox=findViewById(R.id.cream_chk_box);
        Boolean creamboxhaschecked=creambox.isChecked();
        CheckBox chocbox=findViewById(R.id.chocolate_chk_box);
        Boolean chocboxhaschecked=chocbox.isChecked();

        int price=calculatePrice(creamboxhaschecked,chocboxhaschecked);

        EditText username=findViewById(R.id.user_name);

        String summary=createOrderSummary(price,username.getText().toString(),creamboxhaschecked,chocboxhaschecked);

//        displayMessage();//it will get the price message and all details return

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order for "+username.getText().toString());

        intent.putExtra(Intent.EXTRA_TEXT,summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /*Calculate price and join with the price message and return it
     * @param no parameter
     */
    private int calculatePrice(Boolean creamcheck, Boolean choccheck) {
        int pricepercoffe = 5;
        if (creamcheck)
            pricepercoffe += 1;
        if (choccheck)
            pricepercoffe += 2;
        return pricepercoffe * quantity;
    }

    /*
     * Create order summary method
     */
    private String createOrderSummary(int price, String username, Boolean creamcheck, Boolean choccheck) {
        String name = getString(R.string.Namkatranslations)+username;
        String priceMessage;
        if (quantity > 0) {
            priceMessage = "Total: $" + (price) + "\n"+getString(R.string.thank_you);
        } else {
            priceMessage = "Total: $" + (price);
        }

        String creamboxstate = "Add whipped cream?" + creamcheck;

        String chocboxstate = "Add chocolate?" + choccheck;

        return name + "\n" + creamboxstate + "\n" + chocboxstate + "\nQuantity: " + quantity + "\n" + priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity < 100)
            displayQuantity(++this.quantity);
        else {
            Toast toast = Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 1)
            displayQuantity(--quantity);
        else {
            Toast toast = Toast.makeText(this, "You cannot less than 1 coffee", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);

        orderSummaryTextView.setText(message);
    }

}
