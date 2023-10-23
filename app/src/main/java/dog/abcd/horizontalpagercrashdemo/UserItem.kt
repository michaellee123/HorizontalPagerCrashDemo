package dog.abcd.horizontalpagercrashdemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    user: User = User(
        0,
        "name",
        0,
        ""
    )
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = user.avatar,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Text(text = "UserID:" + user.id.toString(), color = Color.Gray)
        Text(text = "UserName:" + user.name)
        Text(
            text = "UserAge:" + user.age.toString(),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}