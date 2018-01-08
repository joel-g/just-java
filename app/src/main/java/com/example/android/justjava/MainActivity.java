package com.example.android.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    boolean hasWhippedCream = false;
    boolean hasChocolate = false;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity > 0) {
            quantity = quantity - 1;
        }
        displayQuantity(quantity);
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
        displayMessage(priceMessage);
    }

    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    private void displayPrice(int price) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(price));
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
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
        String message = "Name: " + name + "\n" +
                "Add whipped cream? " + hasWhippedCream + "\n" +
                "Add chocolate? " + hasChocolate + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total: $" + price + "\n" +
                "Thank you!";
        return message;
    }
}
