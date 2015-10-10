var express = require('express');
var path = require('path');
var config = require('../data/config.json');
var router = express.Router();
var img = require("../imageGen.js");

var base = process.env.PWD;

var fs = require("fs");

router.get('/', function(req, res, next) {
	img(1, 0, function(err, stream){
		if(err){
			res.send(err);
		}
		stream.pipe(res);
	});
});


router.get('/json/', function(req, res, next) {
	res.send(JSON.stringify({"links":["minerals","chunks"]}));
});

router.get('/json/chunks/', function(req, res, next) {
	res.send(JSON.stringify(config));
});

//returns the JSON array of mineral names corresponding to indexes of layers
router.get('/json/minerals/', function(req, res, next) {
	fs.readFile(path.join(base, "data", "minerals.json"), 'utf8', function (err, data) {
		if (err) {
			next(err);
		}

		res.send(data);
	});
});

console.log(img);

router.get('/json/minerals/:ID', function(req, res, next) {
	fs.readFile(path.join(base, "data", "minerals.json"), 'utf8', function (err, data) {
		if(config.minerals[req.params.ID] === undefined) {
			res.status(404);
			res.send(JSON.stringify({"error":{"status":404, "message":"mineral not found"}}));
		}

		res.send(config.minerals[req.params.ID]);
	});
});

router.get('/json/chunks/:CHUNKID/:LAYERID', function(req, res, next) {
	//if the project doesnt exist, go to 404
	if(req.params.CHUNKID < 0 || req.params.CHUNKID > config.numberOfChunks-1 ) {
		res.status(404);
		res.write(JSON.stringify({"error":{"status":404, "message":"invalid chunk id"}}));
		res.end();
		return;
	}

	if(req.params.LAYERID < 0 || req.params.LAYERID > config.minerals.length-1 ) {
		res.status(404);
		res.write(JSON.stringify({"error":{"status":404, "message":"invalid layer id"}}));
		res.end();
		return;
	}

	buildImageFromJSON(path.join(__dirname, req.params.CHUNKID+".json"), function(err, data){
		if (err) {
			res.status(404);
			res.write(JSON.stringify({"error":{"status":404, "message":"file not found"}}));
			res.end();
			return;
		}

		res.write(data);
		res.end();
	});
});


module.exports = router;
