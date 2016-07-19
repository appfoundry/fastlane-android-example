package be.vergauwen.simon.androidretaindata.core.data

import be.vergauwen.simon.androidretaindata.core.model.GithubRepo
import be.vergauwen.simon.androidretaindata.core.service.MockGithubAPI
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import rx.observers.TestSubscriber

class DataRepositoryTest {

  lateinit var githubAPI: MockGithubAPI
  lateinit var dataRepo: RxDataRepository

  @Before
  fun setUp() {
    githubAPI = MockGithubAPI()
    dataRepo = RxDataRepository(githubAPI)
  }

  @Test
  fun preConditions() {
    assertNotNull(githubAPI)
    assertNotNull(dataRepo)
  }

  @Test
  fun testLoad() {
    val sub = TestSubscriber<List<GithubRepo>>()
    dataRepo.getData(true).toBlocking().subscribe(sub)
    sub.onNextEvents // emulate getting onNext to test onComplete()
    sub.assertNoErrors()
    sub.assertCompleted()
  }

  @Test
  fun testGetRepos() {
    val result = getRepos(true)
    assertEquals(result, githubAPI.githubRepo)
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

  private fun getRepos(reload: Boolean): List<GithubRepo> {
    val sub = TestSubscriber<List<GithubRepo>>()
    dataRepo.getData(reload).toBlocking().subscribe(sub)
    val result = sub.onNextEvents
    return if (result.size < 1) throw IndexOutOfBoundsException("Result from observable was empty")
    else result[0]
  }
}