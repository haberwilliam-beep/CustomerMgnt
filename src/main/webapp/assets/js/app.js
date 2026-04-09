/**
 * Customer Management System - Main Application JavaScript
 */

(function($) {
    'use strict';

    // Global CMS namespace
    window.CMS = window.CMS || {};

    // CSRF token setup for AJAX
    CMS.csrfToken = $('meta[name="_csrf"]').attr('content');
    CMS.csrfHeader = $('meta[name="_csrf_header"]').attr('content');

    // Setup AJAX with CSRF
    $.ajaxSetup({
        beforeSend: function(xhr) {
            if (CMS.csrfHeader && CMS.csrfToken) {
                xhr.setRequestHeader(CMS.csrfHeader, CMS.csrfToken);
            }
        }
    });

    // Auto-dismiss alerts after 5 seconds
    CMS.initAlerts = function() {
        setTimeout(function() {
            $('.alert.alert-success').fadeOut('slow');
        }, 5000);
    };

    // Confirm delete
    CMS.confirmDelete = function(message) {
        return confirm(message || 'Are you sure you want to delete this record?');
    };

    // Show loading overlay
    CMS.showLoading = function() {
        if (!$('#loadingOverlay').length) {
            $('body').append(
                '<div id="loadingOverlay" class="loading-overlay">' +
                '<div class="spinner-border text-primary" role="status">' +
                '<span class="visually-hidden">Loading...</span>' +
                '</div></div>'
            );
        }
        $('#loadingOverlay').show();
    };

    // Hide loading overlay
    CMS.hideLoading = function() {
        $('#loadingOverlay').hide();
    };

    // Toast notification
    CMS.showToast = function(message, type) {
        type = type || 'success';
        var toast = $('<div class="toast align-items-center text-white bg-' + type + ' border-0 position-fixed bottom-0 end-0 m-3" role="alert">' +
            '<div class="d-flex">' +
            '<div class="toast-body">' + message + '</div>' +
            '<button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>' +
            '</div></div>');
        $('body').append(toast);
        var bsToast = new bootstrap.Toast(toast[0], { delay: 3000 });
        bsToast.show();
        toast.on('hidden.bs.toast', function() { toast.remove(); });
    };

    // Format date
    CMS.formatDate = function(dateStr) {
        if (!dateStr) return '';
        var d = new Date(dateStr);
        return d.toLocaleDateString() + ' ' + d.toLocaleTimeString();
    };

    // Initialize page
    $(document).ready(function() {
        CMS.initAlerts();

        // Mark active menu item
        var path = window.location.pathname;
        $('.nav-link').each(function() {
            if ($(this).attr('href') && path.includes($(this).attr('href').split('/').pop())) {
                $(this).addClass('active');
            }
        });

        // Initialize tooltips
        $('[data-bs-toggle="tooltip"]').each(function() {
            new bootstrap.Tooltip(this);
        });
    });

})(jQuery);
