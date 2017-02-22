/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("#saveBtn").on('click', function () {
        var labels = {};
        $.each($("#empdList").children('div'), function (idn, valu) {
            labels[$(valu).children('label').prop('id')] = $(valu).children('select').val()
        });
        $.ajax({
            url: "save",
            data: {empList: JSON.stringify(labels)},
            method: "POST",
            cache: false
        }).done(function (data) {
            alert("success");
        })

    });

});
