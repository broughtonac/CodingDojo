function tick() {
    let element = React.createElement(
        'h1', null, new Date().toLocaleTimeString('en-US')
    )
    ReactDOM.render(element, document.getElementById('app'))
}
setInterval(tick, 0, 1000)