$(document).ready(function() {
    var uri = "https://newsapi.org/v2/top-headlines"
    var src = "?sources=google-news"
    var key = "&apiKey=cfe5ed976ee14e1d9c9db8f066c4d88e"
    $.get(uri + src + key, function(data) {
        console.log(data)
        for(var i = 0; i < data.articles.length; i++) {
            console.log(data.articles[i])
            $("#container").append(
                `<h3>${data.articles[i].title}</h3>
                <img src=${data.articles[i].urlToImage} alt="image">
                <p>
                    <a href="${data.articles[i].url}">
                        <button>show article></button>
                    </a>
                </p>`
            )
        }
    })
})