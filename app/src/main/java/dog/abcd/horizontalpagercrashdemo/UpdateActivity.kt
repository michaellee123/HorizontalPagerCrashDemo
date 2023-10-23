package dog.abcd.horizontalpagercrashdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jeremyliao.liveeventbus.LiveEventBus

class UpdateActivity : ComponentActivity() {
    var index = mutableStateOf(0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(Modifier.fillMaxSize()) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Button(onClick = {
                        LiveEventBus.get<Int>("test").post(index.value++)
                    }) {
                        Text(text = "Update Index=${index.value}")
                    }
                    Button(onClick = {
                        finish()
                    }) {
                        Text(text = "Close")
                    }
                }
            }
        }
    }
}