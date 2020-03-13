const fs = require('fs');

// Models user activity stack
class UserActivity {
    constructor() {
        this.activity = this.loadActivity('activity.json');
    }

    // pushes userAct on the activity stack
    pushActivity(userAct) {
        this.activity.push(userAct);
        this.save();
    }

    // Pops the top activity from the stack
    popActivity() {
        if (this.activity.length > 0){
            this.activity.pop();
            this.save();
            return true;
        }
        else {
            return false;
        }
    }

    // returns a reference to the top element in the stack
    peekActivity() {
        if (this.activity.length > 0){
            let top = this.activity[this.activity.length - 1];
            return top;
        }
        else {
            return false;
        }
    }

    // Saves the activity stack to activity.json
    save() {
        let data = JSON.stringify(this.activity, null, 2);
        fs.writeFileSync('activity.json', data);
    }

    // Loads the activity data from activity.json
    loadActivity(fileName) {
        let rawJSON = fs.readFileSync(fileName);
        var obj = [];
        if (rawJSON.length <= 0) {
            return obj;
        }
        else {
            obj = JSON.parse(rawJSON);
            return obj;
        }
        
    }
}

module.exports = UserActivity;