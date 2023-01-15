package redditandroidapp.ui.home

import redditandroidapp.data.models.RedditPostModel

interface PostsFetchingCallback {
    fun cachedPostsReadyForDisplay(cachedRedditPosts: List<RedditPostModel>)
    fun postsFetchedSuccessfully(list: List<RedditPostModel>)
    fun postsFetchingError(errorMessage: String)
}