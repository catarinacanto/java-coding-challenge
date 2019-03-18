var languageOptions = {
    pt: "Portuguese",
    en: "English",
    es: "Spanish"
};

var secondSelect = document.getElementById("target_language");
secondSelect.options.length = 0;

var firstSelect = document.getElementById("source_language");
firstSelect.options.length = 0;

startFirstSelect = function() {
    for (var index in languageOptions) {
        firstSelect.options[firstSelect.options.length] = new Option(
            languageOptions[index],
            index
        );
    }
};
startFirstSelect();

setSecondSelect = function(value) {
    secondSelect.options.length = 0;
    for (var index in languageOptions) {
        if (index !== value){
            secondSelect.options[secondSelect.options.length] = new Option(
                languageOptions[index],
                index);
        }
    }
};

selectFirstOption = function() {
    console.log(firstSelect.value);
    setSecondSelect(firstSelect.value);
};