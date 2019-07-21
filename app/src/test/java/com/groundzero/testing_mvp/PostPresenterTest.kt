package com.groundzero.testing_mvp

import com.groundzero.testing_mvp.data.post.Post
import com.groundzero.testing_mvp.data.post.PostRepository
import com.groundzero.testing_mvp.ui.post.PostContract
import com.groundzero.testing_mvp.ui.post.PostPresenter
import com.groundzero.testing_mvp.utils.POST_DATA_LOADED
import io.reactivex.functions.Consumer
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subjects.BehaviorSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class PostPresenterTest {

    @Mock
    private lateinit var view: PostContract.PostView
    @Mock
    private lateinit var repository: PostRepository

    private lateinit var presenter: PostPresenter
    private var testScheduler = TestScheduler()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        presenter = PostPresenter(view, repository)
    }

    @Test
    fun test_adding_new_post() {
        presenter.onAddPost("Title", "Text")
        verify(repository).addPost("Title", "Text")
        verify(repository).updateSubject()
    }

    @Test
    fun test_subject_called() {
        `when`(repository.getPostsSubject()).thenReturn(customObservable())
        presenter.showPosts()
        verify(view).showToastMessage(POST_DATA_LOADED)
    }

    private fun customObservable(): BehaviorSubject<MutableList<Post>> {
        return BehaviorSubject.create()
    }
}