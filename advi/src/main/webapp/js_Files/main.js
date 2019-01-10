
class Person{
    constructor(name,vorname,strasse,plz,stadt,land, id){
        this.lastname = name;
        this.firstname = vorname;
        this.street = strasse;
        this.plz = plz;
        this.country = land;
        this.town = stadt;
        this.id = id;
    }
}


var loggedInPerson;
var num;

var personenArray = new Array();
//var tempPersonen =JSON.parse(localStorage.getItem('personenArray'));
var showingFriendList = true;


/*
if(tempPersonen == null){
    personenArray.push(new Person("Dennis","Adler","Rummelsburgerstraße 35a","10315","Berlin","Deutschland","1"));
    personenArray.push(new Person("Eduard","Seiler","Klarastraße 2","12459","Berlin","Deutschland", "2"));
    personenArray.push(new Person("Mueller","Peter","Hauptstrasse 12","11111","Berlin","Deutschland", "3"));
}else{
    personenArray = tempPersonen;
}
updateList();*/

function logIn(){

	//damit das funktioniert - muss ein mysql server mit der passenden Datenbank laufen
  	urlLogin = "http://localhost:8080/advi/login?userId="+document.getElementById("userId").value+"&password="+document.getElementById("password").value;
  	var httpVarLogin = new XMLHttpRequest();
    httpVarLogin.open( "GET", urlLogin, false );
    httpVarLogin.send( null );
   
    if(httpVarLogin.responseText == "No such userId/password combofalse"  || httpVarLogin.responseText == "No such userId/password combotrue" ){
		alert("Versuchen Sie es nochmal!");
    }else{
		alert("Erfolg!");
		loggedInPerson = JSON.parse(httpVarLogin.responseText);
		role ="";
		if(loggedInPerson.isAdmin){role="admin";}
		else{
			role="normalo";
			document.getElementById("addFriendButton").style.display = "none";
			document.getElementById("updateDelete").innerHTML = "";
		}
		document.getElementById("userGreetings").innerHTML = "Hallo "+loggedInPerson.firstname+" "+loggedInPerson.lastname+" - Eingeloggt als ‚"+role+ "‘."
		
		document.getElementById("login").style.display = "none";
    	document.getElementById("mainScreen").style.display = "block";
    	//localStorage.getItem("nameOfFriends");
    	//updateList();
        actualise();
        updateList();
    }

}

function actualise(){
    urlContacts = "http://localhost:8080/advi/getContacts?"
    var httpVarContacts = new XMLHttpRequest();
    httpVarContacts.open( "GET", urlContacts, false );
    httpVarContacts.send( null );
    personenArray = JSON.parse( httpVarContacts.responseText);
}


function showFriends(){
	if(showingFriendList){
		document.getElementById("friendList").style.display = "block";
		document.getElementById("mainScreen").style.display = "none";
		document.getElementById("addFriend").style.display = "none";
	}else{
		showMainScreen();
	}
	showingFriendList = !showingFriendList;
}

function showMainScreen(){
		document.getElementById("friendList").style.display = "none";
		document.getElementById("mainScreen").style.display = "block";
		document.getElementById("updateDelete").style.display = "none";
    	document.getElementById("actualProperties").style.display = "none";
    	document.getElementById("map").style.display = "none";
    	document.getElementById("addFriend").style.display = "none";
}



function addFriend(){
    var msgName = document.getElementById("addName").value;
    var msgVorname = document.getElementById("addVorname").value;
    var msgStreet = document.getElementById("addStreet").value;
    var msgPlz = document.getElementById("addPlz").value;
    var msgTown = document.getElementById("addTown").value;
    var msgLand = document.getElementById("addLand").value;
    var id = personenArray.length;

	var xhttpAddContact = new XMLHttpRequest();
    xhttpAddContact.open("POST", "http://localhost:8080/advi/add", true);
    xhttpAddContact.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
    xhttpAddContact.send("lastname=" + msgName + "&firstname=" + msgVorname + "&street=" + msgStreet + "&plz="+ msgPlz + "&town=" + msgTown + "&country=" + msgLand);
	
	//alert(xhttpAddContact.response);
    //personenArray.push(new Person(msgName, msgVorname, msgStreet, msgTown, msgPlz, msgLand, id));
    actualise();
    updateList();
    showMainScreen();
}

function updateList(){
    document.getElementById("friendTable").innerHTML = "";
    for(var i = 0; i<personenArray.length;i++){
        var button = document.createElement("Button");
        button.setAttribute('id', i);
        button.setAttribute('onclick', "showPerson(this.id)")
        button.innerHTML = personenArray[i].lastname + " " + personenArray[i].firstname;
        document.getElementById("friendTable").appendChild(button);
    }
    document.getElementById("addFriend").style.display = "none";
    //localStorage.setItem('personenArray', JSON.stringify(personenArray));
}

function showPerson(clicked_id){
    num = clicked_id;

    document.getElementById("updateDelete").style.display = "inline-block";
    document.getElementById("actualProperties").style.display = "block";
    initMap();
    document.getElementById("map").style.display = "inline-block";
    document.getElementById("addFriend").style.display = "none";

    document.getElementById("actName").innerText = "Name: " + personenArray[num].lastname;
    document.getElementById("actVorname").innerText = "Vorname: " + personenArray[num].firstname;
    document.getElementById("actAdresse").innerText = "Strasse: " + personenArray[num].street;
    document.getElementById("actPlz").innerText = "Plz: " + personenArray[num].plz;
    document.getElementById("actStadt").innerText = "Stadt: " + personenArray[num].town;
    document.getElementById("actLand").innerText = "Land: " + personenArray[num].country;

}

function updatePerson(){
    var newName = document.getElementById("updName").value;
    var newVorname = document.getElementById("updVorname").value;
    var newStreet = document.getElementById("updStreet").value;
    var newPlz = document.getElementById("updPlz").value;
    var newTown = document.getElementById("updTown").value;
    var newLand = document.getElementById("updLand").value;

    personenArray[num].lastname = newName;
    personenArray[num].firstname = newVorname;
    personenArray[num].street = newStreet;
    personenArray[num].plz = newPlz;
    personenArray[num].town = newTown;
    personenArray[num].country = newLand;
    updateList();

    document.getElementById("updateDelete").style.display = "none";
    document.getElementById("actualProperties").style.display = "none";
    document.getElementById("map").style.display = "none";

    actualise();

}

function deletePerson(){

	var x = new XMLHttpRequest();
	urlDel = "http://localhost:8080/advi/delete?userID="+(personenArray[num].id);
	x.open("DELETE",urlDel, true);
	x.send( null );
	//alert(urlDel);

    var index = personenArray.indexOf(personenArray[num]);
    personenArray.splice(index, 1);
    actualise();
    updateList();
    

    document.getElementById("updateDelete").style.display = "none";
    document.getElementById("actualProperties").style.display = "none";
    document.getElementById("map").style.display = "none";
}

function updateScreen(){
    var er = document.getElementsByClassName("nameButton");
    for(var i=0; i<er.length; i++) {
        er[i].onclick = function () {
            document.getElementById("addFriend").style.display = "none";
            document.getElementById("updateDelete").style.display = "block";
        }
    }
}

function showAddFriend() {
    document.getElementById("addFriend").style.display = "inline-block";
    document.getElementById("updateDelete").style.display = "none";
    document.getElementById("actualProperties").style.display = "none";
}

function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 8,
        center: {lat: 52.520008, lng: 13.404954}
    });
    var geocoder = new google.maps.Geocoder();

    geocodeAddress(geocoder, map);
    //document.getElementById('submit').addEventListener('click', function() {
    //    geocodeAddress(geocoder, map);
    //});
}

function geocodeAddress(geocoder, resultsMap) {
    var address = personenArray[num].street + "." + personenArray[num].plz + "," + personenArray[num].town + "," + personenArray[num].country;
    //var address = document.getElementById('address').value;
    geocoder.geocode({'address': address}, function(results, status) {
        if (status === 'OK') {
            resultsMap.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                map: resultsMap,
                position: results[0].geometry.location
            });
        } else {
            alert('Geocode was not successful for the following reason: ' + status);
        }
    });
}
