var configDB = require('../config/database.js');
var mysql = require('mysql');
var connection = mysql.createConnection({
	host: 'localhost',
	user: 'root',
	password: 'a4g443fds2A',
	database: 'routes'
});

Object.size = function(obj) {
    var size = 0, key;
    for (key in obj) {
        if (obj.hasOwnProperty(key)) size++;
    }
    return size;
};

function buildDynamicAttractionQuery(values) {
    var size = Object.size(values);
	var conditions = [];
	var index;
	for (index = 0; index < size; index++) {
		if (typeof values[index] !== 'undefined') {
			conditions.push("type = '?'");
		}
	}

	return {
		where: size ?
			conditions.join(' OR ') : conditions,
		values: values
	};
}
module.exports = function(app, passport) {
	// =====================================
	// HOME PAGE (with login links) ========
	// =====================================
	app.get('/', function(req, res) {
		//res.render('index.ejs'); // load the index.ejs file
		res.render('Start.html')
	});

	app.get('/popAttr', function(req, res) {
		var sql = 'SELECT * FROM Attraction ORDER BY rating DESC;';
		connection.query(sql, function(err, results) {
			if(!err){
				res.json(results);
			} else {
				res.json({
					"code" : 50,
					"status" : "Error in connection to database."
				});
			}
		});
	});

	app.get('/recRoute', function(req, res) {
		var sql = 'SELECT name, description, rating, picture FROM StoredRoute ORDER BY rating DESC LIMIT 2;' ;
		connection.query(sql, function(err, results) {
			if(!err){
				res.json(results);
			} else {
				res.json({
					"code" : 50,
					"status" : "Error in connection to database."
				});
			}
		});
	});

	app.get('/makeAttr', function(req, res) {
		var stringify = JSON.stringify(req.query.type);
		var content = JSON.parse(stringify);
		var types = buildDynamicAttractionQuery(content);
		console.log('Where: ' + types.where);
		console.log('Values: ' + types.values);
		var sql = 'SELECT * FROM Attraction WHERE ' + types.where + ' ORDER BY rating DESC;' ;
		console.log(sql);
		connection.query(sql, [types.values], function(err, results) {
			if(!err){
				res.json(results);
			} else {
				res.json({
					"code" : 50,
					"status" : "Error in connection to database."
				});
			}
		});
	});

	// =====================================
	// LOGIN ===============================
	// =====================================
	// show the login form
	app.get('/login', function(req, res) {

		// render the page and pass in any flash data if it exists
		res.render('login.ejs', { message: req.flash('loginMessage') });
	});

	// process the login form
	app.post('/login', passport.authenticate('local-login', {
		successRedirect : '/profile', // redirect to the secure profile section
		failureRedirect : '/login', // redirect back to the signup page if there is an error
		failureFlash : true // allow flash messages
	}));

	// =====================================
	// SIGNUP ==============================
	// =====================================
	// show the signup form
	app.get('/signup', function(req, res) {
		// render the page and pass in any flash data if it exists
		res.render('signup.ejs', { message: req.flash('signupMessage') });
	});

	// process the signup form
	app.post('/signup', passport.authenticate('local-signup', {
		successRedirect : '/profile', // redirect to the secure profile section
		failureRedirect : '/signup', // redirect back to the signup page if there is an error
		failureFlash : true // allow flash messages
	}));

	// =====================================
	// PROFILE SECTION =========================
	// =====================================
	// we will want this protected so you have to be logged in to visit
	// we will use route middleware to verify this (the isLoggedIn function)
	app.get('/profile', isLoggedIn, function(req, res) {
		console.log(req.user);
		res.render('profile.ejs', {
			user : req.user // get the user out of session and pass to template
		});
	});

	// =====================================
	// LOGOUT ==============================
	// =====================================
	app.get('/logout', function(req, res) {
		req.logout();
		res.redirect('/');
	});
};

// route middleware to make sure
function isLoggedIn(req, res, next) {

	// if user is authenticated in the session, carry on
	if (req.isAuthenticated())
		return next();

	// if they aren't redirect them to the home page
	res.redirect('/');
}
