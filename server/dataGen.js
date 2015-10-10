var base = process.env.PWD;

var fs = require("fs");
var path = require("path");
var config = require(path.join(base, "data", "config.json"));

var pixels = [];
var maxX = config.chunkWidth;
var maxY = config.chunkHeight;
var minerals = config.m;

for (var y = 0; y < maxY; y++) {
	for (var x = 0; x < maxX; x++) {
		var layers = {};
		for(var i = 0; i < minerals.length; i++){
			if(Math.random() < 0.5){
				layers[minerals[i]] = Math.random().toPrecision(4);
			}
		}
		pixels.push({
			"r" : Math.round(Math.random()*255),
			"g" : Math.round(Math.random()*255),
			"b" : Math.round(Math.random()*255),
			"m" : layers
		})
	};
};

console.log(JSON.stringify(pixels));

