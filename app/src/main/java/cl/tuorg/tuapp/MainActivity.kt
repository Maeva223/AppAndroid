package cl.tuorg.tuapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cl.tuorg.tuapp.Nav.AppNavGraph
import cl.tuorg.tuapp.ui.theme.AppIotComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppIotComposeTheme {
                AppNavGraph()
            }
        }
    }
}
