package com.svcodelabs.loadingwait

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.Window
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat

class LoadingDialog(private val context: Context) {

    private fun buildDialogView(@LayoutRes layout: Int): Dialog {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layout)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    fun progressDialog(): Dialog {
        val dialog = buildDialogView(R.layout.dialog_layout)

        val avd = AnimatedVectorDrawableCompat.create(context, R.drawable.avd_dialog_img)
        val iv = (dialog.findViewById<ImageView>(R.id.img)).apply {
            setImageDrawable(avd)
        }
        avd?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                iv.post { avd.start() }
            }
        })
        avd?.start()

        return dialog
    }


}