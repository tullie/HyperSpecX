var base = process.env.PWD;

var fs = require('fs');
var PNG = require('node-png').PNG;
var path = require('path');
var config = require(path.join(base, "data", "config.json"));
var async = require("async");
var colours = [];

function makeColours(callback){
	
	async.each(config.minerals, function(mat, callback){
		var col = {};
		col.r = Math.round(Math.random()*255);
		col.g = Math.round(Math.random()*255);
		col.b = Math.round(Math.random()*255);
		colours[mat] = col;
	});

	console.log(colours);
	callback();
}

function init(){
	var numChunks = config.numberOfChunks;
	var numMinerals = config.minerals.length;
	var mins = config.minerals;

	makeColours(function(){
		var list = [];
		for (var i = 0; i < numChunks; i++) {
		    list.push(i);
		}

		async.each(list, function(number, callback){
			getImage(number, 'base', function(err,stream){
				console.log("BUILT: ChunkID:"+number +", Mineral: base");
			});
			async.forEachOf(mins, function(value, key, callback){
				getImage(number, value, function(err,stream){
					console.log("BUILT: ChunkID:"+number +", Mineral: "+value);
				});
			});
		});
	});
}

//when this process completes the name of the generated image file will be passed into the callback
function getImage(chunkID, mineralName, callback){
	var jsonFile = path.join(base, "data", "chunks", "chunk-"+chunkID+".json");
	var imgPath = path.join(base, "data", "chunks", "c-"+chunkID+"_m-"+mineralName+".png");

	fs.exists(imgPath, function(exists) {
		if (exists) {
			console.log("CACHE HIT: Serving prebuilt image");
			var stream = fs.createReadStream(imgPath);
			callback(null, stream);
			return;
		} else {
			console.log("CACHE MISS: Building file before serving");
			fs.readFile(jsonFile, 'utf8', function (err, data) {
				if (err) {
					console.log(err);
					callback(err);
				}

				var json = JSON.parse(data);

				var img = new PNG({
					width: config.chunkWidth,
					height: config.chunkHeight,
					filterType: -1
				});

				for (var y = 0; y < img.height; y++) {
					for (var x = 0; x < img.width; x++) {
						var idx = (img.width * y + x) << 2;
						var current = json.pixels[(img.width * y + x)];

						if(mineralName === "base"){
							img.data[idx] = current.r; 	//red
							img.data[idx+1] = current.g; 	//green
							img.data[idx+2] = current.b;	//blue
						} else {
							img.data[idx] = colours[mineralName].r; 	//red
							img.data[idx+1] = colours[mineralName].g; 	//green
							img.data[idx+2] = colours[mineralName].b;	//blue
						}
						// and reduce opacity 
						var opacity;
						if(mineralName === "base"){
							opacity = 255;
						} else if (current.minerals.hasOwnProperty(mineralName)){
							opacity = current.minerals[mineralName]*255;
						} else {
							opacity = 0;
						}

						img.data[idx+3] = opacity;
					}
				}

				var stream = img.pack();

				stream.pipe(fs.createWriteStream(imgPath));
				callback(null, stream);
			});
		}
	});
}

init();

module.exports = getImage;
