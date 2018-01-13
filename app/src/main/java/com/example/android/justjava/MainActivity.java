package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    boolean hasWhippedCream = false;
    boolean hasChocolate = false;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if (quantity < 100) {
            quantity += 1;
            displayQuantity(quantity);
        } else {
            Toast.makeText(this, "Maximum order is 100 cups of coffee.",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        } else {
            Toast.makeText(this, "Must order at least 1 coffee.",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        hasChocolate = chocolateCheckBox.isChecked();
        int price = calculatePrice();
        TextView nameField = (EditText) findViewById(R.id.name_field);
        name = nameField.getText().toString();
        String priceMessage = createOrderSummary(price);
        sendEmail(priceMessage);
    }

    public void sendEmail(String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, "example@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    private int calculatePrice() {
        int price = 5;
        if (hasChocolate) {
            price += 2;
        }
        if (hasWhippedCream) {
            price += 1;
        }
        return quantity * price;
    }

    private String createOrderSummary(int price) {
        String message = getString(R.string.name) + ": " + name + "\n" +
                "Add whipped cream? " + hasWhippedCream + "\n" +
                "Add chocolate? " + hasChocolate + "\n" +
                getString(R.string.quantity) + ": " + quantity + "\n" +
                "Total: $" + price + "\n" +
                getString(R.string.thankYou);
        return message;
    }
}
