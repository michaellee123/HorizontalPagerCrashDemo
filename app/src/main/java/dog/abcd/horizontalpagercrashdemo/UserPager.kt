package dog.abcd.horizontalpagercrashdemo

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.jeremyliao.liveeventbus.LiveEventBus
import com.jeremyliao.liveeventbus.core.LiveEvent
import com.jeremyliao.liveeventbus.core.LiveEventBusCore

class UserPager(val viewModel: UserPagerViewModel) {

    @Composable
    fun UserPagerView() {
        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2)) {
            items(viewModel.users, { item: User ->
                item.id
            }) { user ->
                UserItem(modifier = Modifier.clickable {
                    val intent = Intent(viewModel.activity, UpdateActivity::class.java)
                    intent.putExtra("index", user.id)
                    viewModel.activity.startActivity(intent)
                }, user)
            }
        }
    }
}