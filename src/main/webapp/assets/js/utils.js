/**
 * Customer Management System - Utility Functions
 */

window.Utils = (function() {
    'use strict';

    /**
     * Format a date string to display format
     */
    function formatDate(dateStr, format) {
        if (!dateStr) return '';
        var d = new Date(dateStr);
        if (isNaN(d.getTime())) return dateStr;
        return d.toLocaleDateString() + ' ' + d.toLocaleTimeString();
    }

    /**
     * Truncate string to maxLength
     */
    function truncate(str, maxLength) {
        if (!str) return '';
        return str.length > maxLength ? str.substring(0, maxLength) + '...' : str;
    }

    /**
     * Debounce function
     */
    function debounce(func, wait) {
        var timeout;
        return function() {
            var context = this, args = arguments;
            clearTimeout(timeout);
            timeout = setTimeout(function() {
                func.apply(context, args);
            }, wait);
        };
    }

    /**
     * Get URL parameter by name
     */
    function getUrlParam(name) {
        var results = new RegExp('[?&]' + name + '=([^&#]*)').exec(window.location.href);
        return results ? decodeURIComponent(results[1]) : null;
    }

    /**
     * Escape HTML to prevent XSS
     */
    function escapeHtml(str) {
        var div = document.createElement('div');
        div.appendChild(document.createTextNode(str));
        return div.innerHTML;
    }

    /**
     * Serialize form to JSON
     */
    function formToJson(formSelector) {
        var data = {};
        $(formSelector).serializeArray().forEach(function(item) {
            data[item.name] = item.value;
        });
        return data;
    }

    /**
     * Generate a simple UUID
     */
    function uuid() {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = Math.random() * 16 | 0;
            var v = c === 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }

    /**
     * Check if value is empty
     */
    function isEmpty(val) {
        return val === null || val === undefined || val === '';
    }

    return {
        formatDate: formatDate,
        truncate: truncate,
        debounce: debounce,
        getUrlParam: getUrlParam,
        escapeHtml: escapeHtml,
        formToJson: formToJson,
        uuid: uuid,
        isEmpty: isEmpty
    };
})();
