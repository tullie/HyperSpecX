var images = [];

function addImage() {

}

function loadImages(){
    for(var i=0; i< images.length; i++) {
        var img = new Image();
            img.src = images[i];
        document.getElementById('imgDiv').appendChild(img);
    }
}

loadImages(images);
