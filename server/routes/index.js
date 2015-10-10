var express = require('express');
var path = require('path');
var config = require('../data/config.json');
var router = express.Router();

var base = process.env.PWD;

var fs = require("fs");

function getFile(filename, callback){
	
}

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
	// if( === undefined) {
	// 	next();
	// }
	buildImageFromJSON(path.join(__dirname, req.params.ID+".json"), function(err, data){
		if (err) {
			res.status(404);
			res.send(JSON.stringify({"error":{"status":404, "message":"File not found"}}));
		}

		res.send(data);
	});
});

function buildImageFromJSON(filename, callback){
	fs.readFile(filename, 'utf8', function (err, data) {
		if (err) {
			return callback(err);
		}

		callback(err, data);
	});
}




module.exports = router;
