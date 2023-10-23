package dog.abcd.horizontalpagercrashdemo

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.jeremyliao.liveeventbus.LiveEventBus

class UserPagerViewModel3(val index: Int, val activity: MainActivity) : ViewModel() {
    val users = mutableStateListOf<User>()
    val observer: Observer<Int> = Observer {
        users.getOrNull(it)?.let { _ ->
            users[it] = users[it].copy(age = users[it].age + 1)
        }
    }

    init {
        for (i in 0..100) {
            users.add(
                User(
                    i.toLong(), "${index}->$i", 0,
                    if (i and 1 == 1) "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202004%2F11%2F20200411231645_tX8Be.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1700657246&t=a4c22c4409608990c5ede462b43e1edc"
                    else "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202008%2F05%2F20200805151634_tvuzf.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1700657246&t=78198cdf19fd41473b9922133c38f330"
                )
            )
        }
        LiveEventBus.get<Int>("test").observeForever(observer)
    }

    override fun onCleared() {
        super.onCleared()
        LiveEventBus.get<Int>("test").removeObserver(observer)
    }
}