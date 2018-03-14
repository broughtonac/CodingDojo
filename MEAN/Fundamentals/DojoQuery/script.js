$(document).ready(function() {
    function myQuery(id) {
        let element = document.getElementById(id)
        element.click = function(click_callback) {
            element.addEventListener("click", click_callback)
        }
        element.hover = function(mouseover_callback, mouseout_callback) {
            element.addEventListener("mouseover", mouseover_callback)
            element.addEventListener("mouseout", mouseout_callback)
        }
        return element
    }
    myQuery("button1").click(function() {console.log("button1 clicked")})
    myQuery("button1").hover(
        function() {console.log("button1 moused over")},
        function() {console.log("button1 moused off")}
    )
    myQuery("button2").click(function() {console.log("button2 clicked")})
    myQuery("button3").hover(
        function() {console.log("button3 moused over")},
        function() {console.log("button3 moused off")}
    )
})