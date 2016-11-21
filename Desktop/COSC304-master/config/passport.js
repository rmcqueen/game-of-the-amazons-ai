// Load all the stuff we need
var LocalStrategy = require('passport-local').Strategy;
var bcrypt = require('bcrypt-nodejs');
// Load the user model
var Model = require('../app/models/user');

// Load mysql
var mysql = require('mysql');
var connection = mysql.createConnection({
  host: 'localhost',
  port: '3306',
  user: 'root',
  password: 'a4g443fds2A',
  database: 'routes'
});

// Expose this function to our app using module.exports

module.exports = function(passport) {

  // Passport session setup
  // This is required for persistent login sessions
  // Serialize/Unserialize users

  // Serialize Users for the session
  passport.serializeUser(function(user, done){
    done(null, user);
  });

  // Deserialize User
  passport.deserializeUser(function(user, done){
    //connection.query("SELECT * FROM Users WHERE uname = ?", [user], function(err, rows){
        done(null, user);
      //done(err, rows[0]);
    //});
  });


  // ==========
  // LOCAL SIGNUP
  // ==========
  passport.use('local-signup', new LocalStrategy({
    // Defaults to user/pass. Add in email
    usernameField : 'uname',
    passwordfield : 'password',
    passReqToCallback : true // enables passback of entire request to callback
  },
  function(req, uname, password, done) {
    connection.query("SELECT * FROM User where uname = ?", [uname], function(err, rows){
      if (err)
        return done(err);

      if (rows.length) { // the user exists
        return done(null, false, req.flash('signupMessage', uname + ' is already taken'));
      }
      else {
        var newUser = new Model.User();
        newUser.uname = uname;
        console.log(req, "the req");
        newUser.password = bcrypt.hashSync(password);
        console.log(newUser.uname, " username before store");
        connection.query("INSERT INTO User (uname, password) VALUES (?, ?)", [newUser.uname, newUser.password]);
        return done(null, newUser);
      }
    });
}));

  // ==========
  // Local Login
  // ==========

  passport.use('local-login', new LocalStrategy({
    usernameField: 'uname',
    passwordField: 'password',
    passReqToCallback: true
  },

  function(req, username, password, done) {

    //Find a user whose username is the same as the form's username
    // Check if user already exists
    connection.query("SELECT * FROM User WHERE uname = ?", [username], function(err, rows){
      // If any error, return it
      if (err)
        return done(err);

      // If no user found, return message
      if (!rows.length)
        return done(null, false, req.flash('loginMessage', 'No user found.'));

      // If user is found, but password is wrong
      if (!(rows[0].password == password))
        return done(null, false, req.flash('loginMessage', 'Incorrect Password'));

      // Otherwise, return success
      return done(null, rows[0]);
    });
  }));
};
