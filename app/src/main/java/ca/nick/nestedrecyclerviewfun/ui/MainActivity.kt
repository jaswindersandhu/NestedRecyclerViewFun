package ca.nick.nestedrecyclerviewfun.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ca.nick.nestedrecyclerviewfun.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, TheFragment.create(), TheFragment.TAG)
                .commit()
        }
    }
}
