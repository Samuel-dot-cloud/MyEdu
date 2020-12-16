package com.studiofive.myedu.activities.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.effect.EffectFactory;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.studiofive.myedu.R;
import com.studiofive.myedu.classes.Common;

import butterknife.BindView;
import butterknife.ButterKnife;
import ja.burhanrashid52.photoeditor.CustomEffect;
import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;
import ja.burhanrashid52.photoeditor.PhotoFilter;

public class PhotoEditorActivity extends AppCompatActivity {
    @BindView(R.id.photoEditorView)
    PhotoEditorView mPhotoEditorView;

    private Bitmap bitmap;
    private PhotoEditor mPhotoEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_editor);
        ButterKnife.bind(this);

            mPhotoEditorView.getSource().setImageBitmap(Common.IMAGE_BITMAP);

        //Use custom font using latest support library
        Typeface mTextRobotoTf = ResourcesCompat.getFont(this, R.font.muli_black);


        mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
                .setPinchTextScalable(true)
                .setDefaultTextTypeface(mTextRobotoTf)
//                .setDefaultEmojiTypeface(mEmojiTypeFace)
                .build();

//        mPhotoEditor.setBrushDrawingMode(true);
//        mPhotoEditor.setBrushSize(30);
//        mPhotoEditor.setOpacity(100);
//        mPhotoEditor.brushEraser();
        CustomEffect customEffect = new CustomEffect.Builder(EffectFactory.EFFECT_BRIGHTNESS)
                .setParameter("brightness", 0.5f)
                .build();
        mPhotoEditor.setFilterEffect(customEffect);
//        mPhotoEditor.undo();
//        mPhotoEditor.redo();

    }

//    private void editPhotoFunction() {
////Use custom font using latest support library
//        Typeface mTextRobotoTf = ResourcesCompat.getFont(this, R.font.muli_black);
//
////loading font from assest
//        Typeface mEmojiTypeFace = Typeface.createFromAsset(getAssets(), "emojione-android.ttf");
//
//        mPhotoEditorView = new PhotoEditor.Builder(this, mPhotoEditorView)
//                .setPinchTextScalable(true)
//                .setDefaultTextTypeface(mTextRobotoTf)
//                .setDefaultEmojiTypeface(mEmojiTypeFace)
//                .build();
//    }
}