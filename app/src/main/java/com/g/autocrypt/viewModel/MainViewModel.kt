package com.g.autocrypt.viewModel

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.g.autocrypt.NaverMapActivity
import com.g.autocrypt.database.CenterEntity
import com.g.autocrypt.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(application: Application) : AndroidViewModel(application)  {

    private val context = getApplication<Application>().applicationContext

    private val repository = Repository(application)
    private val centers = repository.getAll()

    fun getAllCenter() : LiveData<List<CenterEntity>> {
        return this.centers
    }

    // 코루틴을 사용하여 모든 데이터가 DB에 저장이 끝나면 네이버 지도 화면으로 넘어가게 구현 했습니다.
    fun getCenterInfo() = runBlocking {
        val job = CoroutineScope(Dispatchers.IO).launch {
            repository.getCenterInfo()
        }
        job.join()
        Toast.makeText(context,"저장이 완료되었습니다.",Toast.LENGTH_SHORT).show()
        val intent = Intent(context,NaverMapActivity::class.java)
        context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}