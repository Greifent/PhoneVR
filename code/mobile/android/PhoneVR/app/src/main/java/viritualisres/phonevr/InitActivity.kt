/* (C)2023 */
package viritualisres.phonevr

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.text.HtmlCompat

class InitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // switch the layout based on flavour

        setContentView(R.layout.activity_init_nogvr)

        val tvVersion: TextView = findViewById<TextView>(R.id.head)
        tvVersion.text = "PhoneVR v" + BuildConfig.VERSION_NAME

        val result =
            HtmlCompat.fromHtml(
                "<a href=\"https://github.com/PhoneVR-Developers/PhoneVR#readme\">Readme.md</a>",
                HtmlCompat.FROM_HTML_MODE_LEGACY)
        val tvBody = findViewById<TextView>(R.id.body)
        Log.d(
            "PVR-JAVA",
            "res: '${ resources.getString(R.string.phonevr_support_common) }', html: '${result.toString()}'")

        tvBody.text = String.format(resources.getString(R.string.phonevr_support_common), result)
        tvBody.movementMethod = LinkMovementMethod.getInstance()
        Linkify.addLinks(tvBody, Linkify.WEB_URLS)

        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        val swVRemember = findViewById<SwitchCompat>(R.id.remeber_server)
        swVRemember.isChecked = prefs.getBoolean("remember", false)

        // check if alvr_server setting exists == first run
        // Not kept in Resume() to prevent looping when coming back from AlvrActivity, or others
        if (prefs.contains("alvr_server") && prefs.getBoolean("remember", false)) {
            val intentClass = ALVRActivity::class.java

            startActivity(Intent(this, intentClass))
        }

        swVRemember.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("remember", isChecked).apply()
            if (!isChecked) {
                prefs.edit().remove("alvr_server").apply()
            }
        }
    }

    fun btOnClickALVRStreamer(view: View) {
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        // Save choice after first open. alvr_server as boolean
        if (prefs.getBoolean("remember", false))
            prefs.edit().putBoolean("alvr_server", true).apply()

        val intent = Intent(this, ALVRActivity::class.java)
        startActivity(intent)
    }
}
