package huntervertner.example.youtubeplayer

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.nfc.Tag
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeApiServiceUtil
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeIntents
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_standalone.*
import java.lang.Exception

class StandaloneActivity : AppCompatActivity(), View.OnClickListener{
    private val TAG = "StandaloneActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

        btnPlayVideo.setOnClickListener(this)
        btnPlaylist.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent = when (view.id) {
            R.id.btnPlayVideo -> YouTubeStandalonePlayer.createVideoIntent(
                    this, getString(R.string.GOOGLE_API_KEY), YOUTUBE_VIDEO_ID, 0, true, true
            )
            R.id.btnPlaylist -> YouTubeStandalonePlayer.createPlaylistIntent(
                    this, getString(R.string.GOOGLE_API_KEY), YOUTUBE_PLAYLIST, 0, 0, true, true
            )
          else -> throw IllegalArgumentException("Undefined button clicked")
        }

        try {
            startActivity(intent)
        } catch (e : Exception) {
            if (YouTubeIntents.isYouTubeInstalled(this)){

                val settingIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                settingIntent.data = Uri.parse("package:com.google.youtube")

                startActivity(settingIntent)
            }
        }
    }
}