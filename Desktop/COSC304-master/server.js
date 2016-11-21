// server.js

// set up ======================================================================
// get all the tools we need
var express  = require('express');
var app      = express();
var port     = process.env.PORT || 8080;
var mysql = require('mysql');
var passport = require('passport');
var flash    = require('connect-flash');
var path = require('path');
var morgan       = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser   = require('body-parser');
var session      = require('express-session');

var configDB = require('./config/database.js');
// configuration ===============================================================
var connection = mysql.createConnection(configDB.DB); // connect to our database

require('./config/passport')(passport); // pass passport for configuration
// initialize express app
   app.use(morgan('dev')); // log every request to the console
   app.use(cookieParser()); // read cookies (needed for auth)
   app.use(bodyParser.json()); // get information from html forms
       app.use(bodyParser.urlencoded({ extended: true }));

   app.set('view engine', 'ejs'); // set up ejs for templating
   app.engine('html', require('ejs').renderFile);
   app.use(express.static(path.join(__dirname, 'views')));

   // required for passport
   app.use(session({ secret: 'dank_memes' })); // session secret
   app.use(passport.initialize());
   app.use(passport.session()); // persistent login sessions
   app.use(flash()); // use connect-flash for flash messages stored in session

// routes ======================================================================
require('./app/routes.js')(app, passport); // load our routes and pass in our app and fully configured passport

// launch ======================================================================
app.listen(port);
console.log('The magic happens on port ' + port);
