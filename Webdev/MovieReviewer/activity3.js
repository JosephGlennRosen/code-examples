/*
 This variable holds the dictionary json information for the application.
 The key array hold prohibited words that will be dynamically replaced with
 values found in the answer array.
 */
var dictionary = {
    "dictionary_name": "default",
    "entries":
    [
        {
            "key": ["stupid", "dumb", "idiot", "unintelligent", "simple-minded", "braindead", "foolish", "unthoughtful"],
            "answer": ["educated", "informed", "schooled"]
        }, {
            "key": ["unattractive", "hideous", "ugly"],
            "answer": ["attractive", "beauteous", "beautiful", "lovely", "pretty", "ravishing"]
        }, {
            "key": ["ambiguous", "cryptic", "dark", "nebulous", "obscure", "unintelligible"],
            "answer": ["obvious", "plain", "unambiguous", "understandable", "unequivocal"]
        }, {
            "key": ["incapable", "incompetent", "inept", "unable", "unfit", "unqualified", "weak", "artless"],
            "answer": ["accomplished", "fit", "adept", "complete", "consummate"]
        }, {
            "key": ["emotionless", "heartless", "unkind", "mean", "selfish", "evil"],
            "answer": ["benevolent", "benignant", "gentle", "kind", "clement"]
        }, {
            "key": ["idle"],
            "answer": ["Can you reply something?", "You have been idle for more than 30 seconds", "Whats the matter with you? Submit something"]
        }
    ]
};

// Is used for managing the idle user interval
var timeoutId = 0;

/* 
 This function tests the user input for a JSON entry 
*/
function detectJson() {
    var review = document.getElementById('user-review').value;
    if(review.includes("{") && review.includes("}")) {
        try {
            // user input is valid JSON 
            newEntry = JSON.parse(review);
        } catch(e) {
            // user input has '{' and '}' but is still invalid JSON
            alert("Invalid JSON! Please enter a valid JSON!");
        }
        // json is well formatted so try to add it to the dictionary
        addEntry(newEntry);

    }
    else if(review.includes("{") == true && review.includes("}") == false) {
        alert("Invalid JSON! Please enter a valid JSON!");
    }
    else if(review.includes("{") == false && review.includes("}") == true) {
        alert("Invalid JSON! Please enter a valid JSON! JSON");
    }
}

/* 
 This function tests the user input for a command 
*/
function detectCommand() {
    var review = document.getElementById('user-review').value;
    var isValid = false;
    if(review.includes("/")) {
        if(review.includes("/clear")) {
            isValid = true;
            clearState();
        }
        else if (review.includes("/search")) {
            isValid = true;
            searchDictionary();
        }
        else if (review.includes("/history")) {
            isValid = true;
            showHistory();
        }
        else if (review.includes("/count")) {
            isValid = true;
            showCount();
        }
        
    }
    return isValid;
}

/* 
 This function shows the number of censored words in the session
*/
function showCount() {
    if (!sessionStorage.getItem("count")) {
        document.getElementById('user-review').value = 
        "Count is empty! enter rude words to increase it";
    }

    else {
        
        document.getElementById('user-review').value = 
        "The number of rude words for this session is: " + Number(sessionStorage.clickcount);
    }

}    
/* 
 This function shows the search history
*/
function showHistory() {
    if (!sessionStorage.getItem("history")) {
        alert("History is empty! You must enter a search to view histroy");
        return;
    }
    var history = sessionStorage.getItem("history");
    var searches = history.split(" ");
    var finalHTML = "";

    finalHTML += "<ol>";
    for (results in searches) {
        finalHTML += "<li>" + searches[results] + "</li>";
    }
    finalHTML += "</ol>";
    document.getElementById('history-content').innerHTML = "Search History: "
    document.getElementById('history-content').innerHTML += finalHTML;
}

/* 
 This function searches the dictionary for the specified key and returns the 
 values. 
*/
function searchDictionary() {
    var review = document.getElementById('user-review').value;
    var arr = review.split(" ");
    var history;

    if (sessionStorage.getItem("history")) {
        history = sessionStorage.getItem("history");
        history += " " + arr[1];
    }
    else {
        history = arr[1];
    }
    sessionStorage.setItem("history", history);

    var result = '';
    for (var i = 0; i < dictionary.entries.length; i++) {
        for (var j = 0; j < dictionary.entries[i].key.length; j++) {
            if (dictionary.entries[i].key[j] == arr[1]) {
                result = stringifyArr(dictionary.entries[i].answer);
                document.getElementById('user-review').value = result;
            }
        }
  }
}

/* 
 This function clears the apps state and returns to the default screen 
*/
function clearState() {
    setCookie('name', "", 0);
    document.getElementById('welcome').innerHTML = "Please enter your name";
    document.getElementById('review-content').innerHTML = "";
    document.getElementById('user-review').value = "";
    document.getElementById('user-review').innerHTML = "";
    document.getElementById('name').value = "";
    sessionStorage.removeItem("history");
    sessionStorage.removeItem("count");
    document.getElementById('history-content').innerHTML = "";
    localStorage.clear();
    clearInterval(timeoutId);
}

/* 
 This function tests to see if newEntry.key is one of the keys contained in 
 the dictionary.entries. If it is found its value is appended to the applicable 
 key and alerts the user of the addition. If it is not found, the user is alerted
*/
function addEntry(newEntry) {
    var keyjson = "";
    var valuejson = "";
    var isFound = false;
    /*
     The for in loop is a way to obtain key/value pairs without knowing the
     name of the json key up front. These values are used to search through 
     the dictionary and add the value if the key is already present in the 
     dictionary entries. If the key is not present, alert the user.
    */
    for(var key in newEntry){
        keyjson = key;
        valuejson = newEntry[key];
    }

    for (var i = 0; i < dictionary.entries.length; i++) {
        for (var j = 0; j < dictionary.entries[i].key.length; j++) {
            // Checks if the key is in the dictionary
            if (dictionary.entries[i].key[j] == keyjson) {
                // Value appended to the answer array
                dictionary.entries[i].answer.push(valuejson);
                isFound = true;
                alert("Word added to the dictionary and the dictionary is smarter");
            }
        }
    }
    if (!isFound) {
        alert("Could not find the proper key and the dictionary stays dumb");
    }
    
}

/* 
 This function sets an interval for 30 seconds. The interval message alerts the
 user that they have been idle for 30 seconds. timeoutId is set to the id of the 
 interval so the timer can be reset when the user submits their comment through 
 calling the resetIdTimeout function.  
*/
function setIdleTimeout() {
    timeoutId = setInterval(idMessage, 30000);
}

/* 
 This function alerts the user with a pseudo random message taken from the 
 dictionary.entries answer array pertaining to the idle key. This function 
 is called every 30 seconds unless the user submits their comment.
*/
function idMessage() {
    var upperBound = randomizeAnswer(dictionary.entries[5].answer.length);
    var idleMessage = dictionary.entries[5].answer[upperBound];
    alert(idleMessage);
}

/* 
 This function clears the idle user interval when they submit their comment. 
 The clearing resets the interval timer thus giving the user another 30 seconds
 before the next idle message is alerted.
*/
function resetIdTimeout() {
    clearInterval(timeoutId);
}

/* 
 This function returns a random integer between 0 and arrLength. This 
 random integer is used to return different answers to replace bad words.
*/
function randomizeAnswer(arrLength) {
    return Math.floor(Math.random() * arrLength);
}

/* 
 This function tokenizes the user input from the text area with a space
 as the delimiter. Each token is passed into the parseStr funtion to 
 censor prohibited words.
*/
function replaceMeanWords() {
    // Resets the idle interval so the user does not get an idle message for anothr 30 seconds
    resetIdTimeout();

    // used to handle json input 
    detectJson();

    var review = document.getElementById('user-review').value;
    var arr = review.split(" ");
    for (var i = 0; i < arr.length; i++) {
        arr[i] =  parseStr(arr[i]);
    }
    localStorage.setItem("censoredComment", stringifyArr(arr));

    // used to handle input commands
    var isValid = detectCommand();
    if(!isValid) {
        document.getElementById('user-review').value = stringifyArr(arr);
    }
    
}

/* 
 This function turns arr into one continuous string with each word seperated 
 by a space.
*/
function stringifyArr(arr) {
    var str = '';
    for (var i = 0; i < arr.length; i++) {
        str += arr[i] + " ";
    }
    /*
     Restarts the interval timer after completing a comment submission.
     The idle message will be alerted in 30 seconds if they do not submit
     another comment.
    */
    setIdleTimeout();
    return str;
}

/* 
 This function searches the dictionary's prohibited words for a match
 against the argument word. If word is prohibited, replace it with a 
 corresponding antonym from the dictionary. 
*/
function parseStr(word) {
    var upperBound = 0;
    
    for (var i = 0; i < dictionary.entries.length; i++) {
        for (var j = 0; j < dictionary.entries[i].key.length; j++) {
            if (dictionary.entries[i].key[j] == word) {
                upperBound = randomizeAnswer(dictionary.entries[i].answer.length);
                word = dictionary.entries[i].answer[upperBound];
                sessionStorage.clickcount = Number(sessionStorage.clickcount)+1;
                return word;
            }
        }
  }
  return word;
}

/* 
 this function is used to dynamically display the critic's sample review message 
 based upon if the user has already logged in before 
*/
function criticsReview(isNewUser) {
    if (!isNewUser) {
        document.getElementById('review-content').innerHTML = 
        "Sample movie review<br>" + "This is a bunch of sample text" +
        "This is a bunch of sample text This is a bunch of sample text blah blah blah" +
        "This is a bunch of sample text This is a bunch of sample text blah blah blah<br>" +
        "This is a bunch of sample text This is a bunch of sample text blah blah blah" +
        "This is a bunch of sample text This is a bunch of sample text blah blah blah" +
        "This is a bunch of sample text This is a bunch of sample text blah blah blah<br>" +
        "This is a bunch of sample text This is a bunch of sample text blah blah blah" +
        "This is a bunch of sample text This is a bunch of sample text blah blah blah" +
        "This is a bunch of sample text. -Sample critic review";
    }
}

/*
 this function is used to dynamically display the proper welcome message 
 based upon if the user has already logged in before
*/ 
function welcomeMesaage(isNewUser) {
    
    if (localStorage.getItem("name")) {
        document.getElementById('welcome').innerHTML = localStorage.getItem("name") + 
        " Welcome to movie review System! Please enter your comments about the movie";
    }

    else if (isNewUser) {
        document.getElementById('welcome').innerHTML = "Please enter your name";
    } else {
        document.getElementById('welcome').innerHTML = getCookie('name') + 
        " Welcome to movie review System! Please enter your comments about the movie";
    }
}

/* 
 This function sets the name cookie to what the user entered. The cookie
 expires in hours specified by the experation argument. I used the example
 for cookies from the w3schools as a starting point and modified it for my 
 application needs. Example's URL: https://www.w3schools.com/js/js_cookies.asp
*/
function setCookie(name,value,experation) {
    /*
     Starts the interval timer for 30 seconds. The user will be alerted 
     with an idle message every 30 seconds unless they submit a comment.
     Comment submission will reset the interval to prevent an alert for 
     another 30 seconds.
    */
    setIdleTimeout(); 

    var timeNow = new Date();
    // sets the cookie to expire in hours specified by experation
    timeNow.setTime(timeNow.getTime() + (experation * 60 * 60 * 1000));
    var expires = "expires=" + timeNow.toGMTString();
    document.cookie = name + "=" + value + ";" + expires + ";path=/";
    localStorage.setItem("name", value);
    welcomeMesaage(false);
    criticsReview(false);
 }
    
/* 
 This function gets the cookie with the name of the passed argument. 
 Like the setCookie function I used the example for cookies from 
 the w3schools as a starting point and modified it for my application needs. 
 Example's URL: https://www.w3schools.com/js/js_cookies.asp
*/
function getCookie(cookieName) {
    var name = cookieName + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i < ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
  }
  
  /*
   this function checks if the current user has an associated cookie, and calls 
   the welcomeMessage and the alert function. the welcomeMessage will be passed 
   false for returning users, the alert function will be passed a welcome message
   and the user's name. The welcomeMessage function will be passed true otherwise
   false meaning a first time user. The criticsReview function is passed false if 
   the user is a returning user and displays the sample critic's review message.
   Like the setCookie and getCookie functions I used the examples for cookies from 
   the w3schools as a starting point and modified it for my application needs. 
   Example's URL: https://www.w3schools.com/js/js_cookies.asp 
  */
  function checkCookie() {
    var user=getCookie("name");
    
    if (localStorage.getItem("censoredComment")) {
        welcomeMesaage(false);
        criticsReview(false);
        document.getElementById('user-review').value = localStorage.getItem("censoredComment");
    }

    else if (user != "") {
      alert("Welcome again " + user);
      
    /*
     Starts the interval timer for 30 seconds. The user will be alerted 
     with an idle message every 30 seconds unless they submit a comment.
     Comment submission will reset the interval to prevent an alert for 
     another 30 seconds.
    */
    setIdleTimeout();  
    } else {
        welcomeMesaage(true);
  }
}