var base = process.env.PWD;

var fs = require('fs');
var PNG = require('node-png').PNG;
var path = require('path');
var config = require(path.join(base, "data", "config.json"));

//when this process completes the name of the generated image file will be passed into the callback
function getImage(chunkID, mineralID, callback){
	var jsonFile = path.join(base, "data", "chunks", "chunk-"+chunkID+".json");
	console.log(jsonFile);

	var imgPath = path.join(base, "data", "chunks", "c"+chunkID+"m"+mineralID+".png");

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

						img.data[idx] = current.r; 	//red
						img.data[idx+1] = current.g; 	//green
						img.data[idx+2] = current.b;	//blue

						// and reduce opacity 
						opacity = 255;
						if(mineralID > 0){
							opacity = current.minerals[mineralID-1]*255;
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

module.exports = getImage;