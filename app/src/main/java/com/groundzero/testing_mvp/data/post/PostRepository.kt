package com.groundzero.testing_mvp.data.post

import io.reactivex.subjects.BehaviorSubject

open class PostRepository {


    private val posts = mutableListOf<Post>()
    private var postsSubject: BehaviorSubject<MutableList<Post>>

    init {
        posts.add(
            Post(
                "Guava", "Guavas are plants in the genus Psidium of the family Myrtaceae. " +
                        "There are about 100 species of tropical shrubs and small trees in the genus."
            )
        )
        posts.add(
            Post(
                "Lime", "Lime juice is used in cooking and in drinks. " +
                        "Lime oils are often used in perfumes, used for cleaning, and used for aromatherapy."
            )
        )
        postsSubject = BehaviorSubject.createDefault(posts)
    }

    open fun getPostsSubject(): BehaviorSubject<MutableList<Post>> {
        return postsSubject
    }

    fun addPost(post: Post) {
        posts.add(post)
        updateSubject()
    }

    fun updateSubject() {
        postsSubject.onNext(posts)
    }
}