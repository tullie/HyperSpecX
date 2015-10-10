var express = require('express');
var path = require('path');
var config = require('../data/config.json');
var router = express.Router();

var getImage = require("../imageGen.js").img;
var col = require("../imageGen.js").colours;
var layers = require("../imageGen.js").layers;
var weights = require("../imageGen.js").weights;

var base = process.env.PWD;
function isNumber(obj) { return !isNaN(parseFloat(obj)) }

var fs = require("fs");

router.get('/data/', function(req, res, next) {
	res.send(JSON.stringify({"links":["chunks"]}));
});

router.get('/data/chunks/', function(req, res, next) {
	var obj = config;
	obj.m = layers;
	obj.colours = col;
	obj.weights = weights;
	res.send(JSON.stringify(obj));
});

// //returns the JSON array of mineral names corresponding to indexes of layers
// router.get('/data/minerals/', function(req, res, next) {
// 	fs.readFile(path.join(base, "data", "minerals.json"), 'utf8', function (err, data) {
// 		if (err) {
// 			next(err);
// 		}

// 		res.send(data);
// 	});
// });

// router.get('/data/minerals/:ID', function(req, res, next) {
// 	fs.readFile(path.join(base, "data", "minerals.json"), 'utf8', function (err, data) {
// 		if(config.m[req.params.ID] === undefined) {
// 			res.status(404);
// 			res.send(JSON.stringify({"error":{"status":404, "message":"mineral not found"}}));
// 		}

// 		res.send(config.m[req.params.ID]);
// 	});
// });

router.get('/data/chunks/:CHUNKID', function(req, res, next) {
	//if the project doesnt exist, go to 404
	if(req.params.CHUNKID < 0 || req.params.CHUNKID > config.numberOfChunks-1 ) {
		res.status(404);
		res.write(JSON.stringify({"error":{"status":404, "message":"invalid chunk id"}}));
		res.end();
		return;
	}

	getImage(req.params.CHUNKID, "base", function(err, stream){
		if(err){
			res.send(err);
		}

		stream.pipe(res);
	});
});

router.get('/data/chunks/:CHUNKID/:MINERAL', function(req, res, next) {
	if(!isNumber(req.params.CHUNKID)){
		res.status(404);
		res.write(JSON.stringify({"error":{"status":404, "message":"invalid chunk id"}}));
		res.end();
		return;
	}
	if(req.params.CHUNKID < 0 || req.params.CHUNKID > config.numberOfChunks-1 ) {
		res.status(404);
		res.write(JSON.stringify({"error":{"status":404, "message":"invalid chunk id"}}));
		res.end();
		return;
	}

	if(config.m.indexOf(req.params.MINERAL) == -1 && req.params.MINERAL != "base"){
		res.status(404);
		res.write(JSON.stringify({"error":{"status":404, "message":"invalid mineral name"}}));
		res.end();
		return;
	}

	getImage(req.params.CHUNKID, req.params.MINERAL, function(err, stream){
		if(err){
			res.send(err);
		}

		stream.pipe(res);
	});
});


module.exports = router;
