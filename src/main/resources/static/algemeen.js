function noTypo() {
    var ww1 = document.getElementById('wachtwoord1').value;
    var ww2 = document.getElementById('wachtwoord2').value;
    buttonGreyOut(ww1 != ww2);
    if (ww1 != ww2) {
        document.getElementById('wachtwoord2').focus();
        document.getElementById('noMatch').textContent= "Wachtwoorden komen niet overeen";
    } else {
        document.getElementById('noMatch').textContent= "";
    }
}

function buttonGreyOut(voorwaarde) {
    var button = document.getElementById("submitButton");
    if (voorwaarde) {
        button.disabled = true;
        button.style.cursor="not-allowed";
        button.style.color = "gray";
    } else {
        button.disabled = false;
        button.style.color = "#ffffff";
        button.style.cursor = "pointer";
    }
}

function multiselectZonderCtrl(){
    window.onmousedown = function (e) {
        var el = e.target;
        if (el.tagName.toLowerCase() == 'option' && el.parentNode.hasAttribute('multiple')) {
            e.preventDefault();

            // toggle selection
            if (el.hasAttribute('selected')) el.removeAttribute('selected');
            else el.setAttribute('selected', '');

            // hack to correct buggy behavior
            var select = el.parentNode.cloneNode(true);
            el.parentNode.parentNode.replaceChild(select, el.parentNode);
        }
    }
}

function isSelected() {
    var gebruikerrollen = document.getElementById("gebruikerrollen").getAttribute("value");
    var gesplitsteRollen = gebruikerrollen.split(",");

    var alleRollen = document.getElementById("alleRollen").getAttribute("value");
    var alleRollenGesplitst = alleRollen.split(",");

    for (var i = 0; i < gesplitsteRollen.length; i++) {
        for (var j = 0; j < alleRollenGesplitst.length; j++) {
            if(gesplitsteRollen[i] === alleRollenGesplitst[j]) {
                setTextRollenbox("rollenbox",gesplitsteRollen[i]);
            }
        }
    }

    function setTextRollenbox(idElement, tekstVanElement) {
        var idVanElement = document.getElementById(idElement);
        for (var i = 0; i < idVanElement.options.length; ++i) {
            if (idVanElement.options[i].text.toUpperCase() === tekstVanElement)
                idVanElement.options[i].selected = true;
        }
    }
}

window.onload=function () {
    var deTabel = document.querySelector('#deTabel');
    var incidentOverzicht = document.querySelector('#hetIncident', '#overzicht');
    var beschikbaarheidsweergave;

    if (deTabel) {
        beschikbaarheidsweergave = deTabel;
    } else if (incidentOverzicht) {
        beschikbaarheidsweergave = incidentOverzicht;
    }

    beschikbaarheidsweergave.addEventListener('click', function(e) {
        var td = e.target;
        if (td.tagName === 'TD') {
            e.target.classList.toggle('true');
            e.target.classList.toggle('false');

            if (e.target.classList.contains('true')) {
                td.firstChild.value = 'true';
            } else {
                td.firstChild.value = 'false';
            }
        }
        console.log(td.firstChild.value);
    })
};

