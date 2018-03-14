$(document).ready(function() {
    var uri = "http://api.openweathermap.org/data/2.5/weather"
    var city_prefix = "?q="
    var key = "&appid=61a3b5806dee0b48c980f295ce512a40"
    $("form").submit(function() {
        city = $("input").val()
        console.log(city)
        $.get(uri + city_prefix + city + key, function(data) {
            console.log(data)
            var degf = 9 / 5 * (data.main.temp - 273) + 32 // kelvin to fahrenheit
            $(".wrapper").append(
                `<fieldset>
                    <h1>${data.name}, ${data.sys.country}</h1>
                    <h3>temperature: ${degf} deg F</h3>
                </fieldset>`
            )
        }, "json")
        return false
    })
})