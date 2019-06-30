package com.example.httpparam

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import okio.Buffer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            val buffer = Buffer()
            val builder = StringBuilder()
            Log.d("test", "log params start")
            Log.d("test", "${Test().params}")
            Log.d("test", "log params end\n")
            Log.d("test", "log parts start")
            Test().parts.forEach {
                builder.appendln().append(it.headers())
                buffer.clear()
                it.body().writeTo(buffer)
                builder.append(buffer.readUtf8())
            }
            Log.d("test", builder.toString())
            Log.d("test", "log parts end")
            Log.d("test", "log body start")
            buffer.clear()
            Test().body.build().writeTo(buffer)
            Log.d("test", buffer.readUtf8())
            Log.d("test", "log body end")
            Log.d("test", "${KotlinTest().getParams()}")

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
