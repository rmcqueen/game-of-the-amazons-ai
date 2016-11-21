// load packages
var DB = require('../../config/database').DB;
var bcrypt = require('bcrypt-nodejs');


//Define schema for user model
var User = DB.Model.extend({
  tableName: 'User',
  idAttribute: 'uname'
});

// ========= Methods ==========

function generateHash(password) {
  return bcrypt.hashSync(password, bcrypt.getSaltSync(8), null);
}

function checkValid(password){
  return bcrypt.compareSync(password, this.local.password);
}

module.exports = {
  User: User
};
