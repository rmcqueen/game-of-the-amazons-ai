// config/database.js
var knex = require('knex')({
  client: 'mysql',
  connection: {
    host: 'localhost',
    port: '3306',
    user: 'root',
    password: 'a4g443fds2A',
    database: 'routes'
  }
});
var Bookshelf = require('bookshelf')(knex);

module.exports.DB = Bookshelf;
