/**
 * Customer Grid JavaScript - jqGrid integration
 */

function initCustomerGrid(contextPath, readOnly) {
    'use strict';

    var gridUrl = contextPath + '/api/customers';
    var editUrl = contextPath + '/api/customers';

    var colModel = [
        {name: 'id', label: 'ID', width: 60, key: true, editable: false, align: 'center'},
        {name: 'nameEn', label: 'Name (EN)', width: 150, editable: !readOnly,
            editrules: {required: true}},
        {name: 'nameAr', label: 'Name (AR)', width: 150, editable: !readOnly},
        {name: 'phone', label: 'Phone', width: 120, editable: !readOnly},
        {name: 'email', label: 'Email', width: 180, editable: !readOnly,
            editrules: {email: true}},
        {name: 'address', label: 'Address', width: 200, editable: !readOnly},
        {name: 'status', label: 'Status', width: 90, editable: !readOnly,
            edittype: 'select',
            editoptions: {value: 'ACTIVE:Active;INACTIVE:Inactive'},
            formatter: function(val) {
                var cls = val === 'ACTIVE' ? 'success' : 'secondary';
                return '<span class="badge bg-' + cls + '">' + val + '</span>';
            }
        },
        {name: 'createdDate', label: 'Created', width: 130, editable: false},
        {name: 'createdBy', label: 'Created By', width: 100, editable: false}
    ];

    if (!readOnly) {
        colModel.push({
            name: 'actions', label: 'Actions', width: 100,
            formatter: function(val, opt, row) {
                return '<button class="btn btn-xs btn-outline-danger" onclick="deleteCustomer(\'' + row.id + '\')"><i class="bi bi-trash"></i></button>';
            },
            editable: false, sortable: false, search: false
        });
    }

    $("#customerGrid").jqGrid({
        url: gridUrl,
        datatype: 'json',
        mtype: 'GET',
        colModel: colModel,
        rowNum: 10,
        rowList: [10, 25, 50, 100],
        pager: '#customerPager',
        sortname: 'id',
        sortorder: 'desc',
        viewrecords: true,
        gridview: true,
        autoencode: true,
        height: 'auto',
        width: '100%',
        caption: readOnly ? 'Customer List' : 'Edit Customers',
        jsonReader: {
            root: 'rows',
            page: 'page',
            total: 'total',
            records: 'records',
            repeatitems: false,
            id: 'id'
        },
        cellEdit: !readOnly,
        cellsubmit: 'remote',
        cellurl: editUrl,
        ajaxCellOptions: {
            type: 'PUT',
            contentType: 'application/json'
        },
        beforeCellSubmit: function(rowdata, rowid, cellidx) {
            return true;
        },
        afterSaveCell: function(rowdata, rowid, res) {
            CMS.showToast('Customer updated successfully', 'success');
        },
        errorCell: function(serror, status) {
            CMS.showToast('Error updating customer: ' + serror, 'danger');
        },
        loadError: function(xhr, status, error) {
            console.error('Grid load error:', error);
        }
    });

    if (!readOnly) {
        $("#customerGrid").navGrid('#customerPager',
            {edit: true, add: true, del: true, search: true, refresh: true},
            {// edit
                url: editUrl,
                mtype: 'PUT',
                closeAfterEdit: true,
                afterSubmit: function() {
                    CMS.showToast('Customer updated', 'success');
                    return [true];
                }
            },
            {// add
                url: editUrl,
                mtype: 'POST',
                closeAfterAdd: true,
                afterSubmit: function() {
                    CMS.showToast('Customer created', 'success');
                    return [true];
                }
            },
            {// delete
                url: editUrl,
                mtype: 'DELETE',
                afterSubmit: function() {
                    CMS.showToast('Customer deleted', 'success');
                    return [true];
                }
            }
        );
    } else {
        $("#customerGrid").navGrid('#customerPager',
            {edit: false, add: false, del: false, search: true, refresh: true}
        );
    }
}

function deleteCustomer(id) {
    if (!CMS.confirmDelete('Are you sure you want to delete this customer?')) return;
    $.ajax({
        url: window.contextPath + '/api/customers/' + id,
        type: 'DELETE',
        success: function(response) {
            CMS.showToast('Customer deleted successfully', 'success');
            $("#customerGrid").trigger('reloadGrid');
        },
        error: function(xhr) {
            CMS.showToast('Error deleting customer', 'danger');
        }
    });
}
