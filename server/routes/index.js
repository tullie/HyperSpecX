var express = require('express');
var router = express.Router();

router.get('/json/chunks/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/json/minerals', function(req, res, next) {

});

router.get('/json/chunks/:NAME', function(req, res, next) {
	//if the project doesnt exist, go to 404
	if(projects[req.params.NAME] === undefined) {
		next();
	}

	res.render('project', { "isMainPage": false, "content": projects[req.params.NAME] });
});


module.exports = router;
