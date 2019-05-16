/**
 * package com.example.android.storydemo;
 * <p>
 * import android.support.v7.app.AppCompatActivity;
 * import android.os.Bundle;
 * <p>
 * public class MainActivity extends AppCompatActivity {
 *
 * @Override protected void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState);
 * setContentView(R.layout.activity_main);
 * }
 * }
 * <p>
 * /**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.storydemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox checkWhippedCream = findViewById(R.id.checkWhippedCream);
        boolean addWhippedCream = checkWhippedCream.isChecked();
        CheckBox checkChocolate = findViewById(R.id.checkChocolate);
        boolean addChocolate = checkChocolate.isChecked();
        EditText customerName = findViewById(R.id.text_name);
        String addCustomerName = customerName.getText().toString();

        int price = calculatePrice(addWhippedCream, addChocolate);

        String priceMessage = createOrderSummary(price, addWhippedCream, addChocolate, addCustomerName);
        displayMessage(priceMessage);
    }

    /**
     * @param addWhippedCream
     * @param addChocolate
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if(addWhippedCream == true)
            basePrice += 1;
        if(addChocolate == true)
            basePrice += 2;

        return basePrice*quantity;
    }

    /**
     * @param price
     * @param addWhippedCream
     * @param addChocolate
     * @return
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String customerName) {
        String summary;
        summary = "Name : " + customerName;
        summary += "\nAdd whipped cream? " + addWhippedCream;
        summary += "\nAdd Chocolate? " + addChocolate;
        summary += "\nQuantity : " + quantity;
        summary += "\nTotal : $" + price;
        summary += "\nThank You!";
        return summary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    public void increment(View view) {
        quantity++;
        display(quantity);
        // displayPrice(quantity*price);
    }

    public void decrement(View view) {
        quantity--;
        display(quantity);
        // displayPrice(quantity*price);
    }

    public void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
