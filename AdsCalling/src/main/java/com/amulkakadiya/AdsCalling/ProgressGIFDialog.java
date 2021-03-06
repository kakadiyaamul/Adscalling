package com.amulkakadiya.AdsCalling;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;

import pl.droidsonroids.gif.GifImageView;


public class ProgressGIFDialog {
    private String titleLoading, titleDone;
    private Activity activity;
    private int titleColor;
    private boolean cancelable;

    public ProgressGIFDialog(Builder builder) {
        this.titleLoading = builder.titleLoading;
        this.activity = builder.activity;
        this.titleColor = builder.titleColor;
        this.cancelable = builder.cancelable;
    }

    public static class Builder {
        private String titleLoading, doneTitle;
        private Activity activity;
        private Dialog dialog;
        private GifImageView gifImageView;
        private int titleColor, loadingGifID, doneGifID;
        private TextView tvTitle;

        public Builder setDoneGif(int doneGifID) {
            this.doneGifID = doneGifID;
            return this;
        }

        public Builder setLoadingGif(int loadingGifID) {
            this.loadingGifID = loadingGifID;
            return this;
        }

        private boolean cancelable = true;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder setLoadingTitle(String titleLoading) {
            this.titleLoading = titleLoading;
            return this;
        }

        public Builder setTitleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public boolean isLoading() {

            return dialog.isShowing();

        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setDoneTitle(String doneTitle) {
            this.doneTitle = doneTitle;
            return this;
        }

        public void clear() {
            if (doneGifID != 0) {
                //     gifImageView.setImageResource(doneGifID);
            } else {
                //      gifImageView.setImageResource(R.drawable.done);
            }
            if (doneTitle != null) {
                if (!doneTitle.equals("")) {
                    tvTitle.setText(doneTitle);
                }
            } else {
                tvTitle.setText("Done");
            }

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        @SuppressLint("RestrictedApi")
        public ProgressGIFDialog build() {

            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(cancelable);
            dialog.setContentView(R.layout.loading_dialog);

            tvTitle = dialog.findViewById(R.id.title);
            gifImageView = dialog.findViewById(R.id.gifImageView);

            if (loadingGifID != 0) {
                gifImageView.setImageResource(loadingGifID);
            } else {
                gifImageView.setImageResource(R.drawable.loader_file);
            }


            if (titleLoading != null) {

                if (!titleLoading.equals("")) {

                    tvTitle.setVisibility(View.VISIBLE);
                    tvTitle.setText(titleLoading);
                    if (titleColor != 0) {
                        tvTitle.setTextColor(AppCompatResources.getColorStateList(activity, titleColor));
                    }
                } else {

                    tvTitle.setVisibility(View.GONE);
                }
            } else {
                tvTitle.setVisibility(View.GONE);
            }

            if (!dialog.isShowing()) {

                dialog.show();
            }

            return new ProgressGIFDialog(this);

        }

    }

}
