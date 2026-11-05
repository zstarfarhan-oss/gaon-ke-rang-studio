package com.gaonkerang.studio;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.*;
import android.text.InputType;

public class MainActivity extends Activity {
    int bg = Color.rgb(18, 22, 28);
    int panel = Color.rgb(30, 36, 44);
    int gold = Color.rgb(245, 184, 65);
    int white = Color.WHITE;
    LinearLayout page;
    EditText story;
    TextView status;

    public void onCreate(Bundle b) {
        super.onCreate(b);
        getWindow().setStatusBarColor(bg);
        getWindow().setNavigationBarColor(bg);

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(bg);
        page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(20, 24, 20, 30);
        scroll.addView(page);

        addText("GAON KE RANG STUDIO", 25, gold, true);
        addText("اپنی کہانی سے اپنا کارٹون بنائیں", 17, white, true);
        addSpace(22);

        cardTitle("1. کرداروں کی لائبریری");
        addText("Golu  •  Billu  •  Shani  •  Mithu", 16, white, true);
        addText("اپنے کرداروں کی تصاویر منتخب اور محفوظ کریں۔", 13, Color.LTGRAY, false);
        Button importBtn = button("کردار کی تصویر منتخب کریں");
        importBtn.setOnClickListener(v -> {
            android.content.Intent i = new android.content.Intent(android.content.Intent.ACTION_OPEN_DOCUMENT);
            i.setType("image/*");
            i.addCategory(android.content.Intent.CATEGORY_OPENABLE);
            startActivityForResult(i, 101);
        });

        addSpace(16);
        cardTitle("2. اردو کہانی");
        story = new EditText(this);
        story.setTextColor(white);
        story.setHintTextColor(Color.LTGRAY);
        story.setHint("یہاں اپنی کہانی یا منظر کی تفصیل لکھیں...");
        story.setGravity(Gravity.TOP | Gravity.RIGHT);
        story.setTextSize(16);
        story.setMinLines(5);
        story.setPadding(14, 14, 14, 14);
        story.setBackground(round(panel));
        story.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        page.addView(story, params());

        addSpace(16);
        cardTitle("3. کرداروں کی آواز");
        addText("ہر کردار کی الگ اردو آواز اور Lip-sync", 14, Color.LTGRAY, false);
        addSpace(12);
        cardTitle("4. ویڈیو جنریشن");
        addText("حرکت، چہرے کے تاثرات، پس منظر اور موسیقی", 14, Color.LTGRAY, false);

        Button generate = button("ویڈیو بنائیں");
        generate.setOnClickListener(v -> {
            String s = story.getText().toString().trim();
            if (s.isEmpty()) {
                status.setText("پہلے اپنی کہانی لکھیں۔");
            } else {
                status.setText("کہانی محفوظ کرنے اور AI ویڈیو انجن سے جوڑنے کا مرحلہ ابھی تیار ہو رہا ہے۔");
            }
        });

        addSpace(16);
        status = addText("ابھی ویڈیو جنریشن انجن منسلک نہیں ہے۔", 14, gold, true);
        addSpace(18);
        addText("تیار ویڈیوز  •  MP4 Export", 15, white, true);
        addText("یہ ابتدائی ایپ اسکرین ہے؛ مکمل AI حرکت اور آواز اگلے مراحل میں جوڑی جائیں گی۔", 12, Color.LTGRAY, false);

        setContentView(scroll);
    }

    TextView addText(String s, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.RIGHT);
        t.setTypeface(Typeface.DEFAULT, bold ? Typeface.BOLD : Typeface.NORMAL);
        t.setPadding(0, 5, 0, 5);
        page.addView(t, params());
        return t;
    }

    void cardTitle(String s) {
        TextView t = addText(s, 18, gold, true);
        t.setPadding(14, 12, 14, 12);
        t.setBackground(round(panel));
        addSpace(8);
    }

    Button button(String s) {
        Button b = new Button(this);
        b.setText(s);
        b.setTextColor(Color.BLACK);
        b.setTextSize(15);
        b.setAllCaps(false);
        b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        b.setBackground(round(gold));
        LinearLayout.LayoutParams p = params();
        p.topMargin = 10;
        page.addView(b, p);
        return b;
    }

    GradientDrawable round(int color) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(18);
        return d;
    }

    LinearLayout.LayoutParams params() {
        return new LinearLayout.LayoutParams(-1, -2);
    }

    void addSpace(int h) {
        TextView t = new TextView(this);
        page.addView(t, new LinearLayout.LayoutParams(1, h));
    }
}
