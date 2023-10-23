package dog.abcd.horizontalpagercrashdemo

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.jeremyliao.liveeventbus.LiveEventBus

class UserPagerViewModel2(val index: Int, val activity: MainActivity) : ViewModel() {
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
                    if (i and 1 == 1) "https://img2.baidu.com/it/u=3692884020,2773519070&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500"
                    else "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202004%2F25%2F20200425170750_rWkxu.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1700657246&t=73c7048637bb081a651b0e5ff62c0fd3"
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