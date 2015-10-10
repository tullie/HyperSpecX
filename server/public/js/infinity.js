// var URL = 'http://192.168.51.134:3000';
var URL = 'http://localhost:3000';
var minerals = [];
var layers = {};
var chunks_per_mineral = [];
var displayed_images = [];

function toggleLayer(button){
    var key = button.innerHTML;

    if(layers[key].visible){
        var toDel = document.getElementById('layer-'+key);
        toDel.parentNode.removeChild(toDel);
        layers[key].visible = false;
        button.parentNode.setAttribute('class','');
    } else {
        var div = document.createElement("div");
        layers[key].imgs.forEach(function(imgSrc) {
            addImage(imgSrc,div)
        });
        div.setAttribute('id', 'layer-'+key);
        div.setAttribute('class', 'layer');

        document.getElementById('imgDiv').appendChild(div);
        layers[key].visible = true;
        button.parentNode.setAttribute('class','active');
    }
}

function addImage(img, div) {
    var image = new Image();
    image.src = img;
    displayed_images.push(image);
    
    linebreak = document.createElement("br");
    div.appendChild(image);
    div.appendChild(linebreak);
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
                addButtons();
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

function addButtons(){
    minerals.forEach(function(key) {
        addNavButton(key);
    });
    toggleLayer(document.getElementById('button-base'));
}

function addNavButton(mineralName){
    var list = document.getElementById('list-minerals');
    var li = document.createElement("li");
    var a = document.createElement("a");
    a.setAttribute('href', 'javaScript:void(0);');
    a.setAttribute('onclick', 'toggleLayer(this)');
    a.setAttribute('id', 'button-'+mineralName);
    a.innerHTML = mineralName;

    li.appendChild(a);
    list.appendChild(li);
}

function getLayers(){
    minerals.forEach(function(key) {
        var inside = [];
        for (j = 0; j < numberOfChunks; ++j) {
            inside.push(URL + '/data/chunks/' + j + '/' + key);
        }
        layers[key] = new Object();
        layers[key].imgs = inside;
        layers[key].visible = false;
    });
}

setup();