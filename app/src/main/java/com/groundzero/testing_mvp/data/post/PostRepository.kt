package com.groundzero.testing_mvp.data.post

import io.reactivex.subjects.BehaviorSubject

open class PostRepository {

    private val posts = mutableListOf<Post>()
    private var postsSubject = BehaviorSubject.createDefault(posts)

    open fun getPostsSubject(): BehaviorSubject<MutableList<Post>> {
        return postsSubject
    }

    open fun addPost(title: String, text: String) {
        posts.add(Post(title, text))
    }

    open fun updateSubject() {
        postsSubject.onNext(posts)
    }
}