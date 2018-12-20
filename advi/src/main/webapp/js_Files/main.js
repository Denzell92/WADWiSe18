

class Person{
    constructor(name,vorname,strasse,plz,stadt,land, id){
        this.name = name;
        this.vorname = vorname;
        this.strasse = strasse;
        this.plz = plz;
        this.land = land;
        this.stadt = stadt;
        this.pid = id;
    }
}

var num;
var personenArray = new Array();
var tempPersonen =JSON.parse(localStorage.getItem('personenArray')); 

if(tempPersonen == null){ 
	personenArray.push(new Person("Dennis","Adler","Rummelsburgerstraße 35a","10315","Berlin","Deutschland","1"));
	personenArray.push(new Person("Eduard","Seiler","Klarastraße 2","12459","Berlin","Deutschland", "2"));
	personenArray.push(new Person("Mueller","Peter","Hauptstrasse 12","11111","Berlin","Deutschland", "3"));
	alert("yaya");
}else{
	personenArray = tempPersonen;
	alert("yo");
}
//localStorage.setItem("nameOfFriends",personenArray);


document.getElementById("friendList").style.display = "none";
document.getElementById("addFriend").style.display = "none";
document.getElementById("updateDelete").style.display = "none";
document.getElementById("map").style.display = "none";
document.getElementById("actualProperties").style.display = "none";

function logIn(){
    document.getElementById("lgn").style.display = "none";
    document.getElementById("friendList").style.display = "block";
    localStorage.getItem("nameOfFriends");
    updateList();
}

function addFriend(){
    var msgName = document.getElementById("addName").value;
    var msgVorname = document.getElementById("addVorname").value;
    var msgStreet = document.getElementById("addStreet").value;
    var msgPlz = document.getElementById("addPlz").value;
    var msgTown = document.getElementById("addTown").value;
    var msgLand = document.getElementById("addLand").value;
    var id = personenArray.length;
    personenArray.push(new Person(msgName, msgVorname, msgStreet, msgTown, msgPlz, msgLand, id));
    updateList();
}

function updateList(){
    document.getElementById("friendTable").innerHTML = "";
    for(var i = 0; i<personenArray.length;i++){
        var button = document.createElement("Button");
        button.setAttribute('id', i);
        button.setAttribute('onclick', "showPerson(this.id)")
        button.innerHTML = personenArray[i].name + " " + personenArray[i].vorname;

        document.getElementById("friendTable").appendChild(button);
    }
    document.getElementById("addFriend").style.display = "none";
	localStorage.setItem('personenArray', JSON.stringify(personenArray));
}

function showPerson(clicked_id){
    num = clicked_id;

    document.getElementById("updateDelete").style.display = "block";
    document.getElementById("actualProperties").style.display = "block";
    document.getElementById("addFriend").style.display = "none"

    document.getElementById("actName").innerText = "Name: " + personenArray[num].name;
    document.getElementById("actVorname").innerText = "Vorname: " + personenArray[num].vorname;
    document.getElementById("actAdresse").innerText = "Strasse: " + personenArray[num].strasse;
    document.getElementById("actPlz").innerText = "Plz: " + personenArray[num].plz;
    document.getElementById("actStadt").innerText = "Stadt: " + personenArray[num].stadt;
    document.getElementById("actLand").innerText = "Land: " + personenArray[num].land;

}

function updatePerson(){
    var newName = document.getElementById("updName").value;
    var newVorname = document.getElementById("updVorname").value;
    var newStreet = document.getElementById("updStreet").value;
    var newPlz = document.getElementById("updPlz").value;
    var newTown = document.getElementById("updTown").value;
    var newLand = document.getElementById("updLand").value;

    personenArray[num].name = newName;
    personenArray[num].vorname = newVorname;
    personenArray[num].strasse = newStreet;
    personenArray[num].plz = newPlz;
    personenArray[num].stadt = newTown;
    personenArray[num].land = newLand;
    updateList();

    document.getElementById("updateDelete").style.display = "none";
    document.getElementById("actualProperties").style.display = "none";

}

function deletePerson(){

    var index = personenArray.indexOf(personenArray[num]);
    personenArray.splice(index, 1);
    updateList();

    document.getElementById("updateDelete").style.display = "none";
    document.getElementById("actualProperties").style.display = "none";
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
    document.getElementById("addFriend").style.display = "block"
    document.getElementById("updateDelete").style.display = "none";
    document.getElementById("actualProperties").style.display = "none";
}
