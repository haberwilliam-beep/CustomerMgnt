/**
 * i18n JavaScript Helper
 * Loads translations from the server for client-side use
 */

window.I18n = (function() {
    'use strict';

    var messages = {};
    var currentLang = 'en';

    function load(lang, contextPath, callback) {
        currentLang = lang || 'en';
        $.getJSON(contextPath + '/api/translations/' + currentLang, function(response) {
            if (response.success && response.data) {
                response.data.forEach(function(t) {
                    messages[t.key] = t.value;
                });
            }
            if (typeof callback === 'function') {
                callback();
            }
        }).fail(function() {
            console.warn('Failed to load translations for lang:', currentLang);
            if (typeof callback === 'function') {
                callback();
            }
        });
    }

    function get(key, defaultValue) {
        return messages[key] || defaultValue || key;
    }

    function getLang() {
        return currentLang;
    }

    function applyToPage() {
        $('[data-i18n]').each(function() {
            var key = $(this).data('i18n');
            var translation = get(key);
            if (translation) {
                $(this).text(translation);
            }
        });
    }

    return {
        load: load,
        get: get,
        getLang: getLang,
        applyToPage: applyToPage
    };
})();
