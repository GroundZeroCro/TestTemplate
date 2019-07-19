package com.groundzero.testing_mvp.ui.post

import com.groundzero.testing_mvp.base.BaseView
import com.groundzero.testing_mvp.data.post.Post

interface PostContract {
    interface PostPresenter {
        fun showPosts()
        fun onAddPost(title: String, text: String)
        fun onDestroy()
    }

    interface PostView : BaseView {
        fun showPosts(posts: List<Post>)
        fun onAddNewPost()
    }
}