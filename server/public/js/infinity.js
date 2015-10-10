// var URL = 'http://192.168.51.134:3000';
var URL = 'http://localhost:3000';
var minerals = [];
var layers = {};
var width;
var height;
var count;

function toggleLayer(button){
    var key = button.innerHTML;

    if(layers[key].visible){
        var toDel = document.getElementById('layer-'+key);
        toDel.parentNode.removeChild(toDel);
        layers[key].visible = false;
        button.parentNode.setAttribute('class','');
    } else {
        var div = document.createElement("div");
        div.setAttribute('id', 'layer-'+key);
        div.setAttribute('class', 'layer');
        count = 0;
        layers[key].imgs.forEach(function(imgSrc) {
            addImage(imgSrc,div)
        });

        document.getElementById('imgDiv').appendChild(div);
        layers[key].visible = true;
        button.parentNode.setAttribute('class','active');
    }
}

$( "#toggle-ruler" ).click(function() {
  $( ".rg-overlay" ).toggle();
});

function toggleRuler(){

    
    var key = button.innerHTML;

    if(layers[key].visible){
        var toDel = document.getElementById('layer-'+key);
        toDel.parentNode.removeChild(toDel);
        layers[key].visible = false;
        button.parentNode.setAttribute('class','');
    } else {
        var div = document.createElement("div");
        div.setAttribute('id', 'layer-'+key);
        div.setAttribute('class', 'layer');
        count = 0;
        layers[key].imgs.forEach(function(imgSrc) {
            addImage(imgSrc,div)
        });

        document.getElementById('imgDiv').appendChild(div);
        layers[key].visible = true;
        button.parentNode.setAttribute('class','active');
    }
}


function addImage(img, div) {
    var image = new Image();
    image.src = img;
    
    linebreak = document.createElement("br");

    var canvas = document.createElement("canvas");
    canvas.width = width;
    canvas.height = height;
    var ctx = canvas.getContext("2d");
    canvas.setAttribute('id', "can"+count+"-"+div.id);
    count++;
    $(canvas).click(function(e) {
        console.log("DUCKER");
        var pos = findPos(this);
        var x = e.pageX - pos.x;
        var y = e.pageY - pos.y;
        var coord = "x=" + x + ", y=" + y;
        var c = this.getContext('2d');
        var p = c.getImageData(x, y, 1, 1).data; 
        var hex = "#" + ("000000" + rgbToHex(p[0], p[1], p[2])).slice(-6);
        console.log(coord + "<br>" + hex);
        $('#infoDiv').html(coord + "<br>" + hex);
    });
    
    image.onload = function() {
        ctx.drawImage(image, 0, 0);
    };

    div.appendChild(canvas);
    div.appendChild(linebreak);
    
}

function setup() {
    var ajaxReq = new XMLHttpRequest();
    ajaxReq.onreadystatechange = function() {
        if (ajaxReq.readyState==4) {
            if (ajaxReq.status==200) {
                //do something with ajaxReq.responseText
                var res = JSON.parse(ajaxReq.responseText);
                minerals = res.m;
                width = res.chunkWidth;
                height = res.chunkHeight;
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


function findPos(obj) {
    var curleft = 0, curtop = 0;
    if (obj.offsetParent) {
        do {
            curleft += obj.offsetLeft;
            curtop += obj.offsetTop;
        } while (obj = obj.offsetParent);
        return { x: curleft, y: curtop };
    }
    return undefined;
}
function rgbToHex(r, g, b) {
    if (r > 255 || g > 255 || b > 255)
        throw "Invalid color component";
    return ((r << 16) | (g << 8) | b).toString(16);
}
