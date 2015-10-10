// var URL = 'http://192.168.51.134:3000';
var URL = 'http://localhost:3000';
var minerals = [];
var layers = {};
var chunks_per_mineral = [];
var displayed_images = [];

function toggleLayer(key){
    if(layers[key].visible){
        var toDel = document.getElementById('layer-'+key);
        toDel.parentNode.removeChild(toDel);
        layers[key].visible = false;
    } else {
        var div = document.createElement("div");
        layers[key].imgs.forEach(function(imgSrc) {
            addImage(imgSrc,div)
        });
        div.setAttribute('id', 'layer-'+key);
        div.setAttribute('class', 'layer');

        document.getElementById('imgDiv').appendChild(div);
        layers[key].visible = true;
    }
}

function addImage(img, div) {
    var image = new Image();
    image.src = img;
    // image.setAttribute('class', 'layer-'+key+' layer');
    displayed_images.push(image);
    
    linebreak = document.createElement("br");
    div.appendChild(image);
    div.appendChild(linebreak);
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
                var res = JSON.parse(ajaxReq.responseText);
                minerals = res.minerals;
                numberOfChunks = res.numberOfChunks;
                minerals.push("base");
                getLayers();
                // populateChunks();
            }
        }
    }
    ajaxReq.onerror = function(err) {
        console.log(err);
    }
    ajaxReq.open('GET',
            URL + '/data/chunks/', true);
    ajaxReq.setRequestHeader('Content-Type', 'application/json');
    ajaxReq.send();
}

function addNavButton(){
    document.getElementById('imgDiv').appendChild(div);
    // <li><a href="javaScript:void(0);" onclick="loadMineralChunks('base')">Base</a>
    //                     </li>
}

function getLayers(){
    minerals.forEach(function(key) {
        var inside = [];
        for (j = 0; j < numberOfChunks; ++j) {
            inside.push(URL + '/data/chunks/' + j + '/' + key);
        }
        layers[key].imgs = inside;
        layers[key].visible = false;
    });
    toggleLayer("base");

}

function populateChunks() {
    getLayers();
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
