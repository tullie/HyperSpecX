// var URL = 'http://192.168.51.134:3000';
var URL = 'http://localhost:3000';
var minerals = [];
var layers = [];
var chunks_per_mineral = [];
var displayed_images = [];

function addLayer(){

}

function addImage(img) {
    var image = new Image();
    image.src = img;
    displayed_images.push(image);
    document.getElementById('imgDiv').appendChild(image);
    linebreak = document.createElement("br");
    document.getElementById('imgDiv').appendChild(linebreak);
}

function loadMineralChunks(index){

    if (index === 'base') {
        index = minerals.length-1;
    }
    for(var i = 0; i < displayed_images.length; ++i) {
        displayed_images[i].src = chunks_per_mineral[index][i];
    }
}

function setup() {
    var ajaxReq = new XMLHttpRequest();
    ajaxReq.onreadystatechange = function() {
        if (ajaxReq.readyState==4) {
            if (ajaxReq.status==200) {
                //do something with ajaxReq.responseText
                minerals = JSON.parse(ajaxReq.responseText).names;
                console.log(minerals);
                populateChunks();
            }
        }
    }
    ajaxReq.onerror = function(err) {
        console.log(err);
    }
    ajaxReq.open('GET',
            URL + '/data/minerals/', true);
    ajaxReq.setRequestHeader('Content-Type', 'application/json');
    ajaxReq.send();
}

function populateChunks() {
    minerals.forEach(function(key) {
        var inside = [];
        for (j = 0; j < 3; ++j) {
            inside.push(URL + '/data/chunks/' + j + '/' + key);
        }
        chunks_per_mineral.push(inside);
    });
    minerals.push('base');
    var inside = [];
    for (j = 0; j < 3; ++j) {
        var img = URL + '/data/chunks/' + j + '/base';
        inside.push(img);
        addImage(img);
    }
    chunks_per_mineral.push(inside);
}

setup();
console.log(chunks_per_mineral);
