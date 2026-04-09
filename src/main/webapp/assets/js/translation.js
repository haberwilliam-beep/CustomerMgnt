/**
 * Translation Management JavaScript
 */

$(document).ready(function() {
    'use strict';

    // Search/filter table
    $('#searchInput').on('keyup', function() {
        var value = $(this).val().toLowerCase();
        $('#translationTable tbody tr').filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
        });
    });

    // Reset modal on close
    $('#addModal').on('hidden.bs.modal', function() {
        $('#translationForm')[0].reset();
        $('#translationId').val('');
        $('#modalTitle').text('Add Translation');
    });
});

function editTranslation(id, key, lang, value) {
    $('#translationId').val(id);
    $('#translationKey').val(key);
    $('#translationLang').val(lang);
    $('#translationValue').val(value);
    $('#modalTitle').text('Edit Translation');
    var modal = new bootstrap.Modal(document.getElementById('addModal'));
    modal.show();
}
