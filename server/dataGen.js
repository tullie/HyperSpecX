var fs = require("fs");

var pixels = [];
var maxX = 10;
var maxY = 10;
var numLayers = 3;

for (var y = 0; y < maxY; y++) {
	for (var x = 0; x < maxX; x++) {
		var layers = [];
		for(var i = 0; i < numLayers; i++){
			layers.push(Math.random());
		}
		pixels.push({
			"x" : x,
			"y" : y,
			"r" : Math.round(Math.random()*255),
			"g" : Math.round(Math.random()*255),
			"b" : Math.round(Math.random()*255),
			"minerals" : layers
		})
	};
};

var obj = {"pixels": pixels};

console.log(obj);

