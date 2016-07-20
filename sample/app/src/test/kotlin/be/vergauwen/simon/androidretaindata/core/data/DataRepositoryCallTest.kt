package be.vergauwen.simon.androidretaindata.core.data

import be.vergauwen.simon.androidretaindata.core.model.GithubRepo
import be.vergauwen.simon.androidretaindata.core.service.MockGithubAPI
import be.vergauwen.simon.androidretaindata.core.service.RetrofitCallFailedException
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CountDownLatch

class DataRepositoryCallTest {

  lateinit var githubAPI: MockGithubAPI
  lateinit var dataRepo: DataRepository

  @Before
  fun setUp() {
    githubAPI = MockGithubAPI()
    dataRepo = DataRepository(githubAPI)
  }

  @Test
  fun preConditions() {
    assertNotNull(githubAPI)
    assertNotNull(dataRepo)
  }

  @Test
  fun testGetRepos() {
    var result : List<GithubRepo>? = getRepos(true)
    assertEquals(result, githubAPI.githubRepo)
  }

  @Test(expected = RetrofitCallFailedException::class)
  fun testGetReposFailed(){
    githubAPI.callSuccess = false
    getRepos(true)
  }

  @Test
  fun testReplay() {
    val result = getRepos(true)
    githubAPI.githubRepo = listOf(GithubRepo("different", "to_test", "replay"))
    val result2 = getRepos(false)
    assertEquals(result, result2)
  }

  @Test
  fun testReload() {
    val result = getRepos(true)
    githubAPI.githubRepo = listOf(GithubRepo("different", "to_test", "replay"))
    val result2 = getRepos(true)
    assertNotEquals(result, result2)
  }

  private fun getRepos(reload: Boolean): List<GithubRepo>? {
    var result : List<GithubRepo>? = null
    val latch = CountDownLatch(1);

    dataRepo.getData(reload, object : Callback<List<GithubRepo>> {
      override fun onResponse(call: Call<List<GithubRepo>>?,
          response: Response<List<GithubRepo>>?) {
        result = response?.body()
        latch.countDown()
      }

      override fun onFailure(call: Call<List<GithubRepo>>?, t: Throwable) {
        throw t
      }
    })

    latch.await()
    return result
  }
}