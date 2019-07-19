package com.groundzero.testing_mvp.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.groundzero.testing_mvp.R
import com.groundzero.testing_mvp.data.post.Post
import com.groundzero.testing_mvp.data.post.PostRepository

class PostFragment : Fragment(), PostContract.PostView {

    private lateinit var postRecyclerView: RecyclerView
    private lateinit var addPostButton: Button
    private lateinit var newPostTitle: EditText
    private lateinit var newPostText: EditText
    private lateinit var postAdapter: PostAdapter
    private lateinit var presenter: PostContract.PostPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setViews()
        setRecyclerViewLayout()
        onAddNewPost()
        presenter = PostPresenter(this, PostRepository())
        presenter.showPosts()
    }

    private fun setViews() {
        postRecyclerView = view!!.findViewById(R.id.post_recycler_view)
        addPostButton = view!!.findViewById(R.id.add_post_button)
        newPostTitle = view!!.findViewById(R.id.new_post_title)
        newPostText = view!!.findViewById(R.id.new_post_text)
    }

    private fun setRecyclerViewLayout() {
        postRecyclerView.setHasFixedSize(true)
        postRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun showPosts(posts: List<Post>) {
        postAdapter = PostAdapter(context!!, posts)
        postRecyclerView.adapter = postAdapter
    }

    override fun onAddNewPost() {
        addPostButton.setOnClickListener {
            presenter.onAddPost(
                newPostTitle.text.toString(),
                newPostText.text.toString()
            )
        }
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
