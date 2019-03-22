var languageOptions = {
        pt: "Portuguese",
        en: "English",
        es: "Spanish",
        it: "Italian",
        de: "German",
        ru: "Russian",
        ja: "Japanese"

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
        case 'en':
            return ['pt', 'es', 'it', 'de', 'ru', 'ja'];
        default:
            return ['en'];
    }
}

function setSecondSelect(value) {
    var compatible = setCompatibleLanguages(value);
    disableChangeButton();
    secondSelect.options.length = 1;
    for (var index in languageOptions) {
        if (index !== value) {
            if (checkForMatch(compatible, index)) {
                secondSelect.options[secondSelect.options.length] = new Option(
                    languageOptions[index],
                    index);
            }
        }
    }
    secondSelect.selectedIndex = 0;
};

function checkForMatch(compatibleLanguages, languageToMatch) {
    for (var i = 0; i < compatibleLanguages.length; i++) {
        if (languageToMatch == compatibleLanguages[i]) {
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
    disableChangeButton();
    if (checkForMatch(setCompatibleLanguages(secondSelect.value), firstSelect.value)) {
        enableButtons();
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
        var elem = document.getElementById('table-body');
        elem.remove();
        this.remove();
}