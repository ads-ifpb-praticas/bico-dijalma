/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Make by Dijalma Silva

/**
 * Lança evento no input[type=file].
 * 
 * @param {type} idFile - ID do input[type=file]
 */
function openLoadFile(idFile) {
    $(idFile).trigger('click');
}
;

/**
 * Altera o nome do botão que faz o papel do input[type=file].
 * 
 * @param idButtonFile - ID do botão que exibe na página
 * @param {type} idFile - ID do input[type=file]
 * @param {type} idImagePreview - ID do img que mostrará imagem
 * @param {type} input - input[type=file] (Input do arquivo selecionado para 
 * upload)
 */
function setNameButtonCallFile(idButtonFile,idFile, idImagePreview, input) {
    var nameFile = $(idFile).val().replace(/^.*\\/, "");
    $(idButtonFile).text(nameFile);
    setImagePreview(idImagePreview, input);
}
;

/**
 * Atualiza a imagem do Html com o arquivo selecionado no upload.
 * 
 * @param {type} idImagePreview - ID do img que mostrará imagem
 * @param {type} input - input[type=file] (Input do arquivo selecionado para 
 * upload)
 */
function setImagePreview(idImagePreview, input) {

    var reader = new FileReader();
    reader.onload = function (e) {
        $(idImagePreview).attr('src', e.target.result);
    };
    reader.readAsDataURL(input.files[0]);
}