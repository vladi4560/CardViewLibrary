package com.example.customcardview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;

public class CardViewHorizontal extends MaterialCardView {


    private ShapeableImageView cardImg;
    private LinearLayout contentLayout;
    private TextView titleTextView;
    private TextView descriptionTextView;

    public enum CardShape {
        ROUNDED,
        RECTANGLE,
        OVAL,
        CIRCLE
    }

    public enum CardFont {
        ASSASSIN,
        BASKERVILLE,
        ROBOTO,
        UBUNTU,
        MONTSERRAT
    }

    public enum CardImgShape {
        ROUNDED,
        RECTANGLE,
        CIRCLE
    }
    private CardShape  cardShape;
    private CardFont cardFont;
    private CardImgShape cardImgShape;

    public CardViewHorizontal(Context context) {
        super(context);
        init(context, null);
    }

    public CardViewHorizontal(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CardViewHorizontal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomCardView);

        cardShape = CardViewHorizontal.CardShape.values()[a.getInt(R.styleable.CustomCardView_cardShape, 0)];setElevation(15);
        LinearLayout base = new LinearLayout(context);
        base.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
        );
        base.setLayoutParams(layoutParams);

        View divider = new View(context);
        divider.setLayoutParams(new LinearLayout.LayoutParams(
                4,  LinearLayout.LayoutParams.MATCH_PARENT));
        divider.setBackgroundColor(Color.BLACK);


        cardImg = new ShapeableImageView(context);
        cardImg.setPadding(10,0,0,0);
        LayoutParams layoutParams1 = new LayoutParams(
                400,
                400
        );
        LayoutParams layoutParams2 = new LayoutParams(
                400,
                400
        );
        LinearLayout verticalForText = new LinearLayout(context);
        verticalForText.setLayoutParams(layoutParams2);
        verticalForText.setOrientation(LinearLayout.VERTICAL);
        cardImg.setLayoutParams(layoutParams1);
        titleTextView = new TextView(context);
        titleTextView.setText("Title");
        descriptionTextView = new TextView(context);
        descriptionTextView.setText("Description Text");
        contentLayout = new LinearLayout(context);
        contentLayout.setOrientation(LinearLayout.HORIZONTAL);
        cardImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        titleTextView.setTextSize(20);
        titleTextView.setPadding( 18,8,0,0);
        descriptionTextView.setTextSize(14);
        descriptionTextView.setPadding( 18,20,0,0);
        verticalForText.addView(titleTextView);
        verticalForText.addView(descriptionTextView);
        contentLayout.setLayoutParams(layoutParams);
        contentLayout.addView(cardImg);
        contentLayout.addView(divider);
        contentLayout.addView(verticalForText);

        base.addView(contentLayout);
        addView(base);
    }
    public void setCardShape(CardShape shape) {
        cardShape = shape;
        applyCardShape();
    }

    private void applyCardShape() {
        switch (cardShape) {
            case ROUNDED:
                setRadius(100);
                break;
            case CIRCLE:
                setRadius(360);
                setStrokeWidth(2);
                break;
            case RECTANGLE:
                setRadius(0);
                break;
            case OVAL:
                setRadius(25);
                break;
        }
    }

    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    public void setDescription(String description) {
        descriptionTextView.setText(description);
    }

    public void setCardFont(CardFont font) {
        cardFont = font;
        applyCardFont();
    }

    private void applyCardFont() {
        Typeface customFont;
        switch (cardFont) {
            case ROBOTO:
                customFont = getResources().getFont(R.font.roboto);
                titleTextView.setTypeface(customFont);
                descriptionTextView.setTypeface(customFont);
                break;
            case BASKERVILLE:
                customFont = getResources().getFont(R.font.baskerville);
                titleTextView.setTypeface(customFont);
                descriptionTextView.setTypeface(customFont);
                break;
            case MONTSERRAT:
                customFont = getResources().getFont(R.font.montserrat);
                titleTextView.setTypeface(customFont);
                descriptionTextView.setTypeface(customFont);
                break;
            case UBUNTU:
                customFont = getResources().getFont(R.font.ubuntu);
                titleTextView.setTypeface(customFont);
                descriptionTextView.setTypeface(customFont);
                break;
            case ASSASSIN:
                customFont = getResources().getFont(R.font.assassin);
                titleTextView.setTypeface(customFont);
                descriptionTextView.setTypeface(customFont);
                break;
        }
    }

    public void setCardImg(int id) {
        cardImg.setImageResource(id);
    }

    public void setImgShape(CardImgShape ImgShape) {
        this.cardImgShape = ImgShape;
        applyCardImgShape();
    }

    private void applyCardImgShape() {
        int shape = 0;
        switch (cardImgShape) {
            case ROUNDED:
                shape = 100;
                break;
            case CIRCLE:
                shape = 300;
                break;
            case RECTANGLE:
                shape = 0;
                break;

        }
        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, shape)
                .build();
        cardImg.setShapeAppearanceModel(shapeAppearanceModel);
        cardImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }



}
