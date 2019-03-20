var languageOptions = {
        pt: "Portuguese",
        en: "English",
        es: "Spanish"
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

function setSecondSelect(value) {
    submitButton.disabled = true;
    changeLanguagesButton.disabled = true;
    secondSelect.options.length = 1;
    for (var index in languageOptions) {
        if (index !== value) {
            secondSelect.options[secondSelect.options.length] = new Option(
                languageOptions[index],
                index);
        }
    }
    secondSelect.selectedIndex = 0;
};

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

    changeLanguagesButton.disabled = false;

    submitButton.disabled = false;
}