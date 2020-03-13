/**
 * This function fetches the repo information for the username 
 * passed into the function and passes this fetched information 
 * to the makeTable and displayIssue functions
 */
async function getUserRepo() {
        const username = document.getElementById("txtUsername").value;
        const url = "https://api.github.com/users/" + username + "/repos"
        const response = await fetch(url)
        const result = await response.json()
    
        makeTable(result);
        displayIssues(result);
}

async function getCommits(results) {
    alert("1");
    var str = '';
    const headers = {
        "Accept" : "application/vnd.github.cloak-preview"
    }
    url = "https://api.github.com/repos/amehlhase316/memoranda/commits";
    const response = await fetch(url, {
        "method" : "GET",
        "headers" : headers
    })
    
    const result = await response.json()
    str += "Name" + results.default_branch;
}

/**
 * This function uses the refresh button that causes the data values 
 * for the entire table to be updated as well as recompute requirement 
 * R2 based on the new values. It also clears the branches area of any 
 * information.
 */

function refresh() {
    getUserRepo(); // updates the table
    document.getElementById('branches').innerHTML = ''; // clears the branches area of any information
}

/**
 * This function is used to display the average issues for all the repos
 * and shows the repo that has the greatest number of issues.
 */
function displayIssues(result) {
    var max = result[0].open_issues_count; // used to display the repository with the most issues
    var indexOfMax = 0;
    var sum = 0; // used to calculate the average issues
    var average = 0; // the average issues for all repositories
    var issueSectionHTML = ''; // Used to populate the innerHTML of the issue section 
    for (var i = 0; i < result.length; i++) {
        // Calculates the sum of all issues
        sum += result[i].open_issues_count;
        // Finds max
        if(result[i].open_issues_count > max)
			{
                max = result[i].open_issues_count;
                indexOfMax = i;
			}
    }
    // Finds the average issue count
    average = (sum / result.length); 
    // Generates the message for issue section
    issueSectionHTML = "The average issue count is " + average + 
    " and the repository with the maximum issue count is " + result[indexOfMax].name;
    // Updates the issue section
    document.getElementById("issueSection").innerHTML = issueSectionHTML;
}

/**
 * this function creates the table depending on the amount of repos the 
 * username is a part of 
 */
function makeTable(result) {
    // Creates the tables headers
    var tableHTML =
    "<table border='1'>" +
        "<tr>" +
            "<th>Repository <br>Name:</th>" +
            "<th>TimeStamps: Created <br>And Updated</th>" +
            "<th>Size</th>" +
            "<th>Number <br>of forks</th>" +
            "<th>Name of <br>open issues</th>" +
            "<th>HTML URL</th>" +
            "<th>List of <br>languages used</th>" +
            "<th>Downloads</th>" +
            "<th>Branches</th>" +
        "</tr>";
        // if the user only has one repo 
    if (result.length == 1) {
        tableHTML += "<tr>";
        tableHTML += "<td>" + result[0].name + "</td>"; // repo name
        tableHTML += "<td>Created At:<br>" + result[0].created_at + // repo created and updated dates
        "<br>Updated At:<br>" + result[0].updated_at + "</td>";
        tableHTML += "<td>" + result[0].size + "</td>";// repo size
        tableHTML += "<td>" + result[0].forks_count + "</td>";// repo fork count
        tableHTML += "<td>" + result[0].open_issues_count + "</td>";// repo open issue count
        tableHTML += "<td>" +"<a href=" + result[0].html_url +">" + result[0].html_url +"</a>" + "</td>";// repo URL

        if (result[0].language) {
            tableHTML += "<td>" + result[0].language + "<br><br>" + // repo language list and URL
            "<a href=" + result[0].languages_url +">" + result[0].languages_url +"</a></td>";
        }
        else {
            tableHTML += "<td>" + "<a href=" + result[0].languages_url +">" // repo language list and URL
            + result[0].languages_url +"</a></td>"; 
           
        }

        if (result[0].has_downloads) {
            tableHTML += "<td>" +"<a href=" + result[0].clone_url +">" + result[0].clone_url +"</a>" + "</td>";// repo download URL
        }
        else {
            tableHTML += "<td>None</td>"; // repo has no download link
        }
        tableHTML += "<td>" +"<button id ='btn' " + i + "onclick='getCommits(" + result[0] + ")'" + ">Get Details</button>" + "</td>"// branch Button
        tableHTML += "</tr>"
    }
    // if the user has more than one repo
    else if (result.length >= 2) {
        for (var i = 0; i < 2; i++) {
            tableHTML += "<tr>";
            tableHTML += "<td>" + result[i].name + "</td>"; // repo name
            tableHTML += "<td>Created At:<br>" + result[i].created_at + // repo created and updated dates
            "<br>Updated At:<br>" + result[i].updated_at + "</td>";
            tableHTML += "<td>" + result[i].size + "</td>";// repo size
            tableHTML += "<td>" + result[i].forks_count + "</td>";// repo fork count
            tableHTML += "<td>" + result[i].open_issues_count + "</td>";// repo open issue count
            tableHTML += "<td>" +"<a href=" + result[i].html_url +">" + result[i].html_url +"</a>" + "</td>";// repo URL
            
            if (result[i].language) {
                tableHTML += "<td>" + result[i].language + "<br><br>" + // repo language list and URL
                "<a href=" + result[i].languages_url +">" + result[i].languages_url +"</a></td>";
            }
            else {
                tableHTML += "<td>" + "<a href=" + result[i].languages_url +">" // repo language list and URL
                + result[i].languages_url +"</a></td>"; 
               
            }

            if (result[i].has_downloads) {
                tableHTML += "<td>" +"<a href=" + result[i].clone_url +">" + result[i].clone_url +"</a>" + "</td>";// repo download URL
            }
            else {
                tableHTML += "<td>None</td>"; // repo has no download link
            }
            tableHTML += "<td>" +"<button id ='btn' " + i + "onclick='getCommits(" + result[i] + ")'" + ">Get Details</button>" + "</td>";// branch Button
            tableHTML += "</tr>"

        }
    }
    tableHTML += "</table>"; // closing tag for table
    document.getElementById("table").innerHTML = tableHTML; // updates the div placeholder for the table
}