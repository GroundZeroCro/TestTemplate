package com.groundzero.testing_mvp

import com.groundzero.testing_mvp.data.post.Post
import com.groundzero.testing_mvp.data.post.PostRepository
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class PostRepositoryTest {
    private var postRepository = PostRepository()
    private val posts = mutableListOf<Post>()
    private var testScheduler = TestScheduler()

    @Before
    fun setUp() {
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
    }

    @Test
    fun test_empty_subject() {
        val testObservable = postRepository.getPostsSubject().test()
        testObservable.assertValue(posts)
        testObservable.assertOf { posts -> posts.hasSubscription() }
    }

    @Test
    fun test_populated_subject() {
        val testObservable = postRepository.getPostsSubject().test()
        val post = Post("Title", "Text")
        postRepository.addPost(post.title, post.text)
        testScheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS)
        testObservable.assertValue(mutableListOf(post))
    }
}