/* 
 This function is used to convert the entered amount in USD to 
 Pounds. The function uses ajax to accomplish the conversion
*/
function usdToPounds() {
    var value = document.getElementById("amount-id").value; // this is the user entered value
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var json = JSON.parse(this.responseText);  
        // Updates the displayed value
        document.getElementById("heading").innerHTML = "Current Value is: " +
        json.pounds + " in POUND";
        history();
      }
    };
    xhttp.open("POST", "/pound", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify({value: value}));
}

/* 
 This function is used to convert the entered amount in USD to 
 Euros. The function uses ajax to accomplish the conversion
*/
function usdToEuros() {
    var value = document.getElementById("amount-id").value; // this is the user entered value
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var json = JSON.parse(this.responseText);  
        // Updates the displayed value
        document.getElementById("heading").innerHTML = "Current Value is: " +
        json.euros + " in EURO";
        history();
      }
    };
    xhttp.open("POST", "/euro", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify({value: value}));
}

/* 
 This function is used to remove the most recent user activity 
 from the stack
*/
function pop() {
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          var activity = JSON.parse(this.responseText);
          var currency = "";
          // if true the currency conversion is USD to Pounds
          if (activity.isPounds) {
            currency = "POUND"
          }
          // Else Euros
          else {
              currency = "EURO";
          }
          // if activity is non empty show the activity
          if (activity) {
              document.getElementById("heading").innerHTML = "Current Value is: " +
              activity.currency + " in " + currency;
              document.getElementById("amount-id").value = activity.usd;
          }
          // Else system is at default state
          else {
            document.getElementById("heading").innerHTML = "Current Value is: " +
             "0 in USD";
            document.getElementById("amount-id").value = 0;
          }
        history();
      }
    };
    xhttp.open("GET", "/pop", true);
    xhttp.send(null);
}

/* 
 This function is used to show the user activity that is stored in the 
 stack
*/
function history() {
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var history = this.responseText;
        document.getElementById("history").innerHTML = history;
        isStackEmpty();
      }
    };     
    xhttp.open("GET", "/history", true);
    xhttp.send(null);
}

/* 
 This function is used to reset the the stack and clear the dynamically 
 generated areas of the UI
*/
function reset() {
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
       history();
      }
    };     
    xhttp.open("GET", "/reset", true);
    xhttp.send(null);
}

/* 
 This function is used to check if the stack is empty
*/
function isStackEmpty() {
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var isStackEmpty = JSON.parse(this.responseText);
        document.getElementById("resetBtn").disabled = isStackEmpty.isEmpty;
      }
    };     
    xhttp.open("GET", "/stacksize", true);
    xhttp.send(null);
}

/* 
 This function is used to check if the user has stopped typing for
 input validation
*/
function stoppedTyping(){
    var input = document.getElementById('amount-id').value.length;
    if(input > 0) { 
        document.getElementById('poundBtn').disabled = false; 
        document.getElementById('euroBtn').disabled = false; 
    } else { 
        document.getElementById('poundBtn').disabled = true;
        document.getElementById('euroBtn').disabled = true;
    }
}
