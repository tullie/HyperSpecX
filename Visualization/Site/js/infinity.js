var images = ["6.jpg", "7.jpg"];
var offset = 0;

function addImage(img) {
    images.push(img);
    document.getElementById('imgDiv').appendChild(img);
}

function loadImages(){
    for(var i = 0; i < images.length; i++) {
        var img = new Image();
            img.src = images[i];
        document.getElementById('imgDiv').appendChild(img);
        linebreak = document.createElement("br");
        document.getElementById('imgDiv').appendChild(linebreak);
    }
}

loadImages();
