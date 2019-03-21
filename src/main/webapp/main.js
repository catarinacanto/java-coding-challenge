var languageOptions = {
        pt: "Portuguese",
        en: "English",
        es: "Spanish",
        it: "Italian",
        de: "German"
    },
    secondSelect = document.getElementById("target_language"),
    firstSelect = document.getElementById("source_language"),
    submitButton = document.getElementById('submit-button'),
    changeLanguagesButton = document.getElementById('change-languages-button');


firstSelect.options.length = 1;
secondSelect.options.length = 1;

function startFirstSelect() {
    for (var index in languageOptions) {
        firstSelect.options[firstSelect.options.length] = new Option(
            languageOptions[index],
            index
        );
    }
};
startFirstSelect();

function setCompatibleLanguages(value) {
    switch (value) {
        case 'pt':
            return ['es', 'de'];
        case 'de':
            return ['pt'];
        default:
            return [];
    }
}

function setSecondSelect(value) {
    var incompatible = setCompatibleLanguages(value);
    disableButtons();
    secondSelect.options.length = 1;
    for (var index in languageOptions) {
        if (index !== value) {
            if (!checkForMatch(incompatible, index)) {
                secondSelect.options[secondSelect.options.length] = new Option(
                    languageOptions[index],
                    index);
            }
        }
    }
    secondSelect.selectedIndex = 0;
};

function checkForMatch(incompatibleLanguages, languageToMatch) {
    for (var i = 0; i < incompatibleLanguages.length; i++) {
        if (languageToMatch == incompatibleLanguages[i]) {
            return true;
        }
    }
    return false;
}

function selectFirstOption() {
    setSecondSelect(firstSelect.value);
};

function changeLanguages() {
    var tempValue = firstSelect.value;
    firstSelect.value = secondSelect.value;
    setSecondSelect(firstSelect.value);
    selectSecondLanguage(tempValue);
}

function selectSecondLanguage(value) {
    secondSelect.value = value;
    enableButtons();
    if (checkForMatch(setCompatibleLanguages(secondSelect.value), firstSelect.value)) {
        disableChangeButton();
    }
}
function disableChangeButton() {
    changeLanguagesButton.disabled = true;
}

function enableButtons() {
    changeLanguagesButton.disabled = false;
    submitButton.disabled = false;
}

function disableButtons() {
    submitButton.disabled = true;
    changeLanguagesButton.disabled = true;
}

function deleteRows() {
    var rows = document.getElementsByTagName("tr"), index;
    for (index = rows.length - 1; index >= 0; index--) {
        rows[index].parentNode.removeChild(rows[index]);
    }
}