package dog.abcd.horizontalpagercrashdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dog.abcd.horizontalpagercrashdemo.ui.theme.HorizontalPagerCrashDemoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    lateinit var userPager1: UserPager
    lateinit var userPager2: UserPager1
    lateinit var userPager3: UserPager2
    lateinit var userPager4: UserPager3

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userPager1 = UserPager(createVM {
            UserPagerViewModel(1, this@MainActivity)
        })
        userPager2 = UserPager1(createVM {
            UserPagerViewModel1(2, this@MainActivity)
        })
        userPager3 = UserPager2(createVM {
            UserPagerViewModel2(3, this@MainActivity)
        })
        userPager4 = UserPager3(createVM {
            UserPagerViewModel3(4, this@MainActivity)
        })

        setContent {
            val state by remember {
                mutableStateOf(
                    object : PagerState() {
                        override val pageCount: Int
                            get() = 4
                    }
                )
            }
            HorizontalPagerCrashDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Page(
                            modifier = Modifier.weight(1f),
                            state = state,
                            userPager1 = userPager1,
                            userPager2 = userPager2,
                            userPager3 = userPager3,
                            userPager4 = userPager4
                        )
                        Bottom(state = state)
                    }
                }
            }
        }
    }
}

inline fun <reified VM : ViewModel> ComponentActivity.createVM(
    noinline initializer: CreationExtras.() -> VM
): VM {
    val viewModelFactory = viewModelFactory {
        initializer {
            initializer()
        }
    }
    val viewModel: VM by viewModels { viewModelFactory }
    return viewModel
}

@ExperimentalFoundationApi
@Composable
fun Page(
    modifier: Modifier,
    state: PagerState,
    userPager1: UserPager,
    userPager2: UserPager1,
    userPager3: UserPager2,
    userPager4: UserPager3
) {
    HorizontalPager(modifier = modifier, state = state, userScrollEnabled = false) {
        when (it) {
            0 -> {
                userPager1.UserPagerView()
            }

            1 -> {
                userPager2.UserPagerView()
            }

            2 -> {
                userPager3.UserPagerView()
            }

            3 -> {
                userPager4.UserPagerView()
            }
        }
    }
}

@Composable
@ExperimentalFoundationApi
fun Bottom(state: PagerState) {
    val scope = rememberCoroutineScope()
    Row {
        Text(
            text = "Page 1",
            color = if (state.currentPage == 0) Color.Black else MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .clickable {
                    scope.launch {
                        state.scrollToPage(0)
                    }
                }
        )
        Text(
            text = "Page 2",
            color = if (state.currentPage == 1) Color.Black else MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .clickable {
                    scope.launch {
                        state.scrollToPage(1)
                    }
                }
        )
        Text(
            text = "Page 3",
            color = if (state.currentPage == 2) Color.Black else MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .clickable {
                    scope.launch {
                        state.scrollToPage(2)
                    }
                }
        )
        Text(
            text = "Page 4",
            color = if (state.currentPage == 3) Color.Black else MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .clickable {
                    scope.launch {
                        state.scrollToPage(3)
                    }
                }
        )
    }
}