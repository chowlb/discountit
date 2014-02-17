package com.chowlb.discountit;

import android.os.Bundle;
import android.app.Activity;
//import android.util.Log;
//import android.text.Editable;
import android.view.View;
//import android.view.Menu;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.ads.*;

public class MainActivity extends Activity {
	EditText item;
	EditText discount;
	EditText tax;
	TextView saved;
	TextView finalPrice;
	double discountAmount = 0;
	double taxAmount = 0;
	double itemAmount = 0;
	double savedAmount = 0;
	double finalAmount = 0;
	
	private AdView adView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        adView = new AdView(this);
	    adView.setAdUnitId("ca-app-pub-8858215261311943/4723162710");
	    adView.setAdSize(AdSize.BANNER);
		
	    
	    RelativeLayout layout = (RelativeLayout)findViewById(R.id.RelativeLayout1);
	    RelativeLayout.LayoutParams rLParam = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	    rLParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
	    layout.addView(adView, rLParam);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);
	    
	    
	    
		item = (EditText) findViewById(R.id.itemPriceET);
		discount = (EditText) findViewById(R.id.discountET);
		tax = (EditText) findViewById(R.id.taxET);
		saved = (TextView) findViewById(R.id.savedPriceTV);
		finalPrice = (TextView) findViewById(R.id.finalPriceTV);
		
	}
	
	public double calcDiscount(double item, double discount) {
		//Log.e("chowlb", "Discount Item amount: " + item + "  Discount amt: " + discount);
		double totalPrice = item - (item * ((double)discount/100));
		
		if(tax.getText().length() > 0) {
			double taxAmt = Double.parseDouble(tax.getText().toString());
			totalPrice = totalPrice + (totalPrice * (taxAmt/100));
		}
		
		//Log.e("chowlb", "Total price: " + totalPrice);
		return totalPrice;
	}
	
	public double calcSaved(double item, double discount) {
		//Log.e("chowlb", "Saved Item amount: " + item + "  Discount amt: " + discount);
		double disc1 = (double)discount/100;
		//Log.e("chowlb", "Disc1" + disc1);
		double disc = item * disc1;
		//Log.e("chowlb", "Saved: " + disc);
		return disc;
	}
	
	public void discount(View v) {
		if(discount.getText().length() == 0) {
			discountAmount = 0;
		}else {
			discountAmount = Double.parseDouble(discount.getText().toString());
		}
		if(item.getText().length() == 0) {
			itemAmount = 0;
		}else {
			itemAmount = Double.parseDouble(item.getText().toString());
		}
		saved.setText("");
		finalPrice.setText("");
		saved.setText("$" + String.format("%.2f", calcSaved(itemAmount, discountAmount)));
		finalPrice.setText("$" + String.format("%.2f", calcDiscount(itemAmount, discountAmount)));
	}
	
	//@Override
	//public boolean onCreateOptionsMenu(Menu menu) {
	//	// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.main, menu);
	//	return true;
	//}

}
