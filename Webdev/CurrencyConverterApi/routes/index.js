var express = require('express');
var router = express.Router();
var UserActivity = require('../models/userActivity.js');

// Renders the home page
router.get('/', function(req, res) {
    res.render('index');
  });

// Converts the passed amount of usd to pounds and returns the equal amount in pounds
router.post('/pound', function(req, res) {
  let activity = new UserActivity();
  var ip = req.headers['x-forwarded-for'] || 
     req.connection.remoteAddress || 
     req.socket.remoteAddress ||
     (req.connection.socket ? req.connection.socket.remoteAddress : null);
  var userAgent = req.headers['user-agent']; 
  var usd = parseInt(req.body.value);
  var pounds = usd * 0.78;
  var activityToString = "Operand: " + usd + " was converted from USD to " + pounds + " POUND" +
                     " Ip: " + ip + " User-Details: " + userAgent;
  var jsonActivity = {
    "usd": usd,
    "isPounds": true,
    "currency": pounds,
    "activityToString": activityToString
  };
  activity.pushActivity(jsonActivity);   

  var json = {
    "pounds": pounds
  };
  res.send(json);
}); 

// Converts the passed amount of usd to euros and returns the equal amount in euros
router.post('/euro', function(req, res) {
  let activity = new UserActivity();
  var ip = req.headers['x-forwarded-for'] || 
     req.connection.remoteAddress || 
     req.socket.remoteAddress ||
     (req.connection.socket ? req.connection.socket.remoteAddress : null);
  var userAgent = req.headers['user-agent']; 
  var usd = parseInt(req.body.value);
  var euros = usd * 0.9;
  var activityToString = "Operand: " + usd + " was converted from USD to " + euros + " EURO" +
                     " Ip: " + ip + " User-Details: " + userAgent;
  
  var jsonActivity = {
    "usd": usd,
    "isPounds": false,
    "currency": euros,
    "activityToString": activityToString
  };
  activity.pushActivity(jsonActivity);    

  var json = {
    "euros": euros
  };
  res.send(json);
}); 

// Removes the most recent user action from the stack
router.get('/pop', function(req, res) {
  let activity = new UserActivity();
  activity.popActivity();   
  res.status(200).send(JSON.stringify(activity.peekActivity()));
}); 

// Returns the user activities for the session
router.get('/history', function(req, res) {
  let activityList = new UserActivity();
  var str = '';
  if (activityList.activity.length > 0) {
    for (operands in activityList.activity) {
      str += activityList.activity[operands].activityToString + "<br>";
    }   
  }

  res.status(200).send(str);
});

// empties the stack for the current user session
router.get('/reset', function(req, res) {
  let activityList = new UserActivity();
  activityList.activity.length = 0;
  activityList.save();
  var json = {"message": "Stack was reset"};
  res.status(200).send(JSON.stringify(json));
});

// gets the size of the current user activity stack
router.get('/stacksize', function(req, res) {
  let activityList = new UserActivity();
  var isStackEmpty = {"isEmpty": true};
  if (activityList.activity.length > 0) {
    isStackEmpty.isEmpty = false;
  }

  res.status(200).send(JSON.stringify(isStackEmpty));
});

module.exports = router;
