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


import android.content.Intent;
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

    int quantity = 99;

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
        String summary = createOrderSummary(price, addWhippedCream, addChocolate, addCustomerName);
        composeEmail(summary, addCustomerName);
    }

    /**
     * @param addWhippedCream
     * @param addChocolate
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if (addWhippedCream == true)
            basePrice += 1;
        if (addChocolate == true)
            basePrice += 2;

        return basePrice * quantity;
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
        if (quantity == 100) {
            Toast.makeText(this, "You cannot order more than 100 cups of coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            return;
        }
        quantity--;
        display(quantity);
    }


    /**
     * @param summary
     * @param customerName
     */
    public void composeEmail(String summary, String customerName) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + customerName);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
