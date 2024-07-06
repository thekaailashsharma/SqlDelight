package temp.learn.sqldelight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import temp.learn.sqldelight.ui.theme.SqlDelightTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SqlDelightTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val mainViewModel by viewModels<MainViewModel>()
                    Button(onClick = {
                        for (i in 0..10) {
                            mainViewModel.onInsertPersonClick(
                                "First $i",
                                "Last $i",
                                age = (i+18).toLong()
                            )
                        }
                    }) {
                        Text(text = "Hello, World!", modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}
