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

public class CardViewVertical extends MaterialCardView {

    private ImageView arrowBtn;
    private ShapeableImageView cardImg;
    private LinearLayout contentLayout;
    private TextView titleTextView;
    private TextView descriptionTextView;

    private ShapeableImageView cardImgExpanded;
    private LinearLayout contentLayoutExpanded;
    private TextView titleTextViewExpanded;
    private TextView descriptionTextViewExpanded;
    private boolean setToBeExpanded = false;
    private boolean isExpanded = false;

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

    private CardShape cardShape;
    private CardFont cardFont;
    private CardImgShape cardImgShape;

    public CardViewVertical(Context context) {
        super(context);
        init(context, null);
    }

    public CardViewVertical(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CardViewVertical(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public TextView getDescriptionTextView() {
        return descriptionTextView;
    }

    private void init(Context context, AttributeSet attrs) {
        //LayoutInflater inflater = LayoutInflater.from(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomCardView);
        cardShape = CardShape.values()[a.getInt(R.styleable.CustomCardView_cardShape, 0)];
        setElevation(15);
        LinearLayout base = new LinearLayout(context);
        base.setOrientation(LinearLayout.VERTICAL);
        LayoutParams layoutParams5 = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
        );
        base.setLayoutParams(layoutParams5);

        FrameLayout imageContainer = new FrameLayout(context);

        // Create the ImageButton and set its image resource
        arrowBtn = new ImageView(context);
        arrowBtn.setScaleType(ImageView.ScaleType.CENTER_CROP);
        arrowBtn.setImageResource(R.drawable.ic_arrow);
        // Set the size of the ImageButton
        int size = 80;
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(size, size);

        // Set the gravity of the FrameLayout to bottom-end
        layoutParams2.gravity = Gravity.BOTTOM | Gravity.END;

        // Add the ImageButton to the FrameLayout and set the layout parameters
        imageContainer.addView(arrowBtn, layoutParams2);
        imageContainer.setPadding(0, 0, 20, 5);


        // Create a new ShapeableImageView with the circular shape appearance
        cardImg = new ShapeableImageView(context);

        LayoutParams layoutParams1 = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        cardImg.setLayoutParams(layoutParams1);
        titleTextView = new TextView(context);
        titleTextView.setText("Title");
        descriptionTextView = new TextView(context);
        descriptionTextView.setText("Description Text");
        contentLayout = new LinearLayout(context);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        // Set the layout parameters for the TextView
        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );

        titleTextView.setTextSize(20);
        titleTextView.setPadding(25, 5, 0, 0);
        descriptionTextView.setTextSize(14);
        descriptionTextView.setPadding(25, 10, 0, 0);
        layoutParams.gravity = Gravity.CENTER;
        contentLayout.setLayoutParams(layoutParams);
        contentLayout.addView(cardImg);
        contentLayout.addView(titleTextView);
        contentLayout.addView(descriptionTextView);
        cardImg.setPadding(150, 15, 0, 0);
        base.addView(contentLayout);
        base.addView(imageContainer);
        addView(base);


        View divider = new View(context);
        divider.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 4)); // 2 is the height of the divider
        divider.setBackgroundColor(Color.BLACK);

        LayoutParams layoutParams3 = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );

        contentLayoutExpanded = new LinearLayout(context);
        titleTextViewExpanded = new TextView(context);
        descriptionTextViewExpanded = new TextView(context);
        cardImgExpanded = new ShapeableImageView(context);
        titleTextViewExpanded.setTextSize(16);
        descriptionTextViewExpanded.setTextSize(12);
        titleTextViewExpanded.setText("Title Expanded");
        descriptionTextViewExpanded.setText("Description Expanded");
        titleTextViewExpanded.setPadding(50, 5, 0, 0);
        descriptionTextViewExpanded.setPadding(50, 30, 0, 0);
        contentLayoutExpanded.setLayoutParams(layoutParams3);
        contentLayoutExpanded.setOrientation(LinearLayout.VERTICAL);
        contentLayoutExpanded.addView(divider);
        contentLayoutExpanded.addView(cardImgExpanded);
        contentLayoutExpanded.addView(titleTextViewExpanded);
        contentLayoutExpanded.addView(descriptionTextViewExpanded);

        base.addView(contentLayoutExpanded);
        contentLayoutExpanded.setVisibility(View.GONE);
        arrowBtnClicked();
        a.recycle();


    }

    private void arrowBtnClicked() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setExpanded(!isExpanded);
            }
        });
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
        // Create a new ShapeableImageView with the circular shape appearance
        cardImg.setShapeAppearanceModel(shapeAppearanceModel);
        cardImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    public void setExpanded(boolean isExpanded) {
        this.isExpanded = isExpanded;
        if (setToBeExpanded) {
            if (isExpanded) {
                contentLayoutExpanded.setVisibility(View.VISIBLE);
                arrowBtn.setRotation(45f);
            } else {
                contentLayoutExpanded.setVisibility(View.GONE);
                arrowBtn.setRotation(45f);
            }
        }
    }

    public void setSetToBeExpanded(boolean bool) {
        this.setToBeExpanded = true;
    }
}
