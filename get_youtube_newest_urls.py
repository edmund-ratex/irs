from googleapiclient.discovery import build

#api_key = "AIzaSyCRySS7J_KWdtUL4DlKysra6mpREKYom6w"
api_key = "AIzaSyBAHxudHMUvykRJNp5qjz5-l_E-XWQWv78"
#api_key = "AIzaSyA6pmXtZS2XsCXyayes0fPmGs0P_-arocs"
# api_key = "AIzaSyBFYR0F2xPBFSKW-e6mxY2QKqesOTGFFJk"
# api_key = "AIzaSyCuJHcWrQfJ3gVQvC9UbnoeMwIDADIMars"
#api_key = "AIzaSyDUY01Rrj4OgfKOmEGYWnQykQ6NQcdFMoQ"
# api_key = "AIzaSyA3XJvv47_yRkWLve-_Xz4LRtOPeKECe_c"
youtube = build("youtube", "v3", developerKey=api_key)

def get_youtuber_channel_id(video_url):
    username  = get_user_name(video_url)
    if username == "":
        return ""
    
    search_response = youtube.search().list(
        q=username,
        part="id,snippet",
        maxResults=1,
        type='channel'
    ).execute()

    if search_response and search_response.get("items"):
        channel = search_response["items"][0]
        return channel['id']['channelId']        
    else:
        return ""
    
    
def get_user_name(video_url):
    splitted_url = video_url.split('@')

    if len(splitted_url) == 2:
        substring = splitted_url[1].split('/')[0]
        return substring
    else:
        return ""


def get_youtuber_newest_urls(youtuber_url, publishedBefore, publishedAfter):
    video_urls = []
    channel_id = get_youtuber_channel_id(youtuber_url)
    if channel_id:
        print(f'Channel ID: {channel_id}')
    else:
        print('Channel ID not found')
        return video_urls

    video_urls += get_video_urls("long", channel_id, publishedBefore, publishedAfter)
    video_urls += get_video_urls("medium", channel_id, publishedBefore, publishedAfter)
    return video_urls


def get_video_urls(videoDurationStr, channel_id, publishedBefore, publishedAfter):
    # api说明: https://developers.google.com/youtube/v3/docs/search/list
    search_request = youtube.search().list(
        part="id,snippet", videoDuration=videoDurationStr, channelId=channel_id, type="video", order="date", maxResults=50,publishedBefore=publishedBefore, publishedAfter=publishedAfter
    )
    search_result = search_request.execute()
    video_urls = []
    for item in search_result["items"]:
        video_id = item["id"]["videoId"]        
        video_url = f"https://www.youtube.com/watch?v={video_id}"
        video_urls.append(video_url)
    return video_urls