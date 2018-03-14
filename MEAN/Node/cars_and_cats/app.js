var http = require('http');
var fs = require('fs');

var server = http.createServer(function(request, response ){
    console.log('client request URL: ', request.url);
    if(request.url === '/') {
        fs.readFile('views/index.html', 'utf8', function(errors, contents) {
            response.writeHead(200, {'Content-Type': 'text/html'});
            response.write(contents); 
            response.end();
        });
    }
    else if (request.url === "/stylesheets/style.css") {
        console.log('sytle')
        fs.readFile('./stylesheets/style.css', 'utf8', function(errors, contents) {
            response.writeHead(200, {'Content-type': 'text/css'});
            response.write(contents); 
            response.end();
        });
    }
    else if (request.url === "/cars") {
         fs.readFile('views/cars.html', 'utf8', function(errors, contents) {
             response.writeHead(200, {'Content-type': 'text/html'});
             response.write(contents);
             response.end();
         });
    }
    else if(request.url === '/images/cars/1'){
        fs.readFile('./images/delorean.jpg', function(errors, contents){
            response.writeHead(200, {'Content-type': 'image/jpg'});
            response.write(contents);
            response.end();
        })
    }
    else if(request.url === '/images/cars/2'){
        fs.readFile('./images/modelt.jpg', function(errors, contents){
            response.writeHead(200, {'Content-type': 'image/jpg'});
            response.write(contents);
            response.end();
        })
    }
    else if (request.url === "/cars/new") {
        fs.readFile('views/new.html', 'utf8', function(errors, contents) {
            response.writeHead(200, {'Content-type': 'text/html'});
            response.write(contents); 
            response.end();
        });
    }
    else if (request.url === "/cats") {
        fs.readFile('views/cats.html', 'utf8', function(errors, contents) {
            response.writeHead(200, {'Content-type': 'text/html'});
            response.write(contents);
            response.end();
        });
    }
    else if(request.url === '/images/cats/1'){
        fs.readFile('./images/fatcat.jpg', function(errors, contents){
            response.writeHead(200, {'Content-type': 'image/jpg'});
            response.write(contents);
            response.end();
        })
    }
    else if(request.url === '/images/cats/2'){
        fs.readFile('./images/jerry.jpg', function(errors, contents){
            response.writeHead(200, {'Content-type': 'image/jpg'});
            response.write(contents);
            response.end();
        })
    }
    else {
        response.end('file not found');
    }
});

server.listen(7077);

console.log("running in localhost at port 7077");