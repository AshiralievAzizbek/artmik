package com.example.artmik.view.utils

import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.LifecycleCoroutineScope
import com.bumptech.glide.Glide
import com.example.artmik.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun <T> Flow<T>.whenCreated(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenCreated {
        this@whenCreated.collect()
    }
}
fun <T> Flow<T>.whenResumed(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenResumed {
        this@whenResumed.collect()
    }
}

fun <T> Flow<T>.whenStarted(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenStarted {
        this@whenStarted.collect()
    }
}

fun ImageView.load(url: String) {
    Glide.with(this).load(url).into(this)
}

fun Fragment.addFragment(fragment: Fragment) {
    requireActivity().supportFragmentManager.commit {
        addToBackStack(null)
        add(R.id.container, fragment, null)
    }
}

fun AppCompatActivity.showSnackBar(view: View, message: String, isError: Boolean) {
    val snackBar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
    snackBar.setText(message)
    snackBar.view.setBackgroundColor(
        ContextCompat.getColor(
            this,
            if (isError) R.color.red else R.color.teal_200
        )
    )
    snackBar.show()
}