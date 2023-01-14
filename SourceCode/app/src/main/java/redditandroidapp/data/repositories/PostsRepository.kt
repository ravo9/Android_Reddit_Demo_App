package redditandroidapp.data.repositories

import android.util.Log
import redditandroidapp.R
import redditandroidapp.data.models.RedditPostModel
import redditandroidapp.data.network.ApiClient
import redditandroidapp.data.network.PostsResponseGsonModel
import redditandroidapp.data.network.SinglePostDataGsonModel
import redditandroidapp.injection.RedditAndroidApp
import redditandroidapp.ui.home.PostsFetchingCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

// Data Repository - the main gate of the model (data) part of the application
class PostsRepository @Inject constructor(private val apiClient: ApiClient) {

    private val _cachedRedditPosts = ArrayList<RedditPostModel>()

    val cachedRedditPosts: List<RedditPostModel>
        get() = _cachedRedditPosts

    fun getLastPostName(): String? {
        return if (cachedRedditPosts.isNotEmpty()) cachedRedditPosts.last().id else null
    }

    fun fetchRedditPosts(
        lastPostName: String?,
        callback: PostsFetchingCallback,
        clearCache: Boolean
    ) {
        val endpoint = if (lastPostName == null) apiClient.getFreshRedditPosts()
        else apiClient.getNextPageOfRedditPosts(lastPostName)

        endpoint.enqueue(object : Callback<PostsResponseGsonModel> {

            override fun onResponse(
                call: Call<PostsResponseGsonModel>,
                response: Response<PostsResponseGsonModel>
            ) {
                if (response.isSuccessful
                    && response.body() != null
                    && response.body()?.data != null
                    && !response.body()?.data?.childrenPosts.isNullOrEmpty()
                )
                    response.body()?.data?.childrenPosts?.let {
                        val receivedList = it
                        val transformedList = transformReceivedRedditPostsList(receivedList)
                        val storedPosts =
                            saveFetchedPostsInCache(clearCache, transformedList, _cachedRedditPosts)
                        callback.postsFetchedSuccessfully(storedPosts)
                    }
                else {
                    logErrorDetails(prepareLogFriendlyErrorMessage(null))
                    callback.postsFetchingError(
                        prepareHumanFriendlyErrorMessage(null),
                        cachedRedditPosts
                    )
                }
            }

            override fun onFailure(call: Call<PostsResponseGsonModel>, t: Throwable) {
                logErrorDetails(prepareLogFriendlyErrorMessage(t))
                callback.postsFetchingError(prepareHumanFriendlyErrorMessage(t), cachedRedditPosts)
            }
        })
    }

    fun saveFetchedPostsInCache(
        clearCache: Boolean,
        postsToBeStored: List<RedditPostModel>,
        postsAlreadyCached: MutableList<RedditPostModel>
    ): List<RedditPostModel> {
        if (clearCache) postsAlreadyCached.clear()
        postsAlreadyCached.addAll(postsToBeStored)
        return postsAlreadyCached
    }

    fun getCachedPosts(): List<RedditPostModel> {
        return cachedRedditPosts
    }

    private fun prepareLogFriendlyErrorMessage(throwable: Throwable?): String {
        val genericErrorMessage =
            RedditAndroidApp.getLocalResources().getString(R.string.error_api_call_failure)
        val errorTextFromApi = throwable?.message
        return errorTextFromApi ?: genericErrorMessage
    }

    // Todo: Change into enum with problem reason.
    private fun prepareHumanFriendlyErrorMessage(throwable: Throwable?): String {
        val genericErrorMessage =
            RedditAndroidApp.getLocalResources().getString(R.string.connection_error_message)
        val errorTextFromApi = throwable?.message
        return errorTextFromApi ?: genericErrorMessage
    }

    private fun logErrorDetails(errorMessage: String) {
        val errorTag = RedditAndroidApp.getLocalResources().getString(R.string.error)
        Log.e(errorTag, errorMessage)
    }

    private fun transformReceivedRedditPostsList(list: List<SinglePostDataGsonModel>): List<RedditPostModel> {
        return list.mapNotNull {
            it.post?.let {
                RedditPostModel(
                    it.id,
                    it.permalink,
                    it.title,
                    it.thumbnail,
                    it.author,
                )
            }
        }
    }
}