
function getListProvinsi(idField) {
    $.get("/api/region/get/provinsi").done(function (data) {
        var content = "";
        for (var i = 0; i < data.length; i++) {
            content += '<option value="' + data[i].id + '">' + data[i].nama + '</option>';
        }
        $("#" + idField).html(content).selectpicker('refresh');
    });
}

// =========================================================================================
// (select) id field kota | id provinsi | (untuk edit) id kota | untuk refresh form validate
// =========================================================================================
function getListKota(idField, idProvinsi, kotaValue = null, form = null) {
    $.get("/api/region/get/kota/" + idProvinsi).done(function (data) {
        var content = "";
        for (var i = 0; i < data.length; i++) {
            content += "<option value='" + data[i].id + "'>" + data[i].nama + "</option>";
        }

        $("#" + idField).html(content).selectpicker('refresh');
        $("#" + idField + "_infoPilihProvinsi").addClass("hidden");
        if (kotaValue != null) {
            setRegionSelectValue(idField, kotaValue);
        }
        
        if (form !== null) {
            refreshRegionFormValidate(form);
        }
    });
}

// =================================================================================================
// (select) id field kecamatan | id kota | (untuk edit) id kecamatan | untuk refresh form validate
// =================================================================================================
function getListKecamatan(idField, idKota, kecamatanValue = null, form = null) {
    $.get("/api/region/get/kecamatan/" + idKota).done(function (data) {
        var content = "";
        for (var i = 0; i < data.length; i++) {
            content += "<option value='" + data[i].id + "'>" + data[i].nama + "</option>";
        }

        $("#" + idField).html(content).selectpicker('refresh');
        $("#" + idField + "_infoPilihKota").addClass("hidden");
        if (kecamatanValue != null) {
            setRegionSelectValue(idField, kecamatanValue);
        }
        
        if (form !== null) {
            refreshRegionFormValidate(form);
        }
    });
}

function setRegionSelectValue(id, value){
    $('#' + id).selectpicker('val', value);
}

function refreshRegionFormValidate(idForm){
//     $('#' + idForm).validator('validate');
}