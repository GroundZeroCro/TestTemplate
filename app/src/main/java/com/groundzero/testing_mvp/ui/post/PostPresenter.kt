package com.groundzero.testing_mvp.ui.post

import com.groundzero.testing_mvp.data.post.Post
import com.groundzero.testing_mvp.data.post.PostRepository
import com.groundzero.testing_mvp.utils.POST_DATA_LOADED
import io.reactivex.disposables.Disposable

class PostPresenter(private val view: PostContract.PostView, private val postRepository: PostRepository) :
    PostContract.PostPresenter {

    private lateinit var disposable: Disposable

    override fun showPosts() {
        disposable = postRepository.getPostsSubject()
            .subscribe { posts -> view.showPosts(posts) }
        view.showToastMessage(POST_DATA_LOADED)
    }

    override fun onAddPost(title: String, text: String) {
        postRepository.addPost(Post(title, text))
    }

    override fun onDestroy() {
        disposable.dispose()
    }
}