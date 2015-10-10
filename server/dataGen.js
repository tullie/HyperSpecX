var fs = require("fs");

var pixels = [];
var maxX = process.argv[2];
var maxY = process.argv[3];
var minerals = ["one", "two", "three"];

for (var y = 0; y < maxY; y++) {
	for (var x = 0; x < maxX; x++) {
		var layers = {};
		for(var i = 0; i < minerals.length; i++){
			layers[minerals[i]] = Math.random();
		}
		pixels.push({
			"r" : Math.round(Math.random()*255),
			"g" : Math.round(Math.random()*255),
			"b" : Math.round(Math.random()*255),
			"minerals" : layers
		})
	};
};

var obj = {"pixels": pixels};

console.log(JSON.stringify(obj));

