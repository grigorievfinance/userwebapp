var orderAjaxUrl = "profile/orders/";

function updateFilteredTable(){
    $.ajax({
        type: "GET",
        url: orderAjaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter(){
    $("#filter")[0].reset();
    $.get(orderAjaxUrl, updateTableByData);
}

$.ajaxSetup({
   converters: {
       "text json": function (stringData) {
           var json = JSON.parse(stringData);
           $(json).each(function () {
               this.dateTime = this.dateTime.replace('T', ' ').substr(0, 16);
           });
           return json;
       }
   }
});

$(function () {
   makeEditable(orderAjaxUrl, {
       "columns": [
           {
               "data": "dateTime"
           },
           {
               "data": "description"
           },
           {
               "data": "price"
           },
           {
               "data": "deadline"
           },
           {
               "render": renderEditBtn,
               "defaultContent": "",
               "orderable": false
           },
           {
               "render": renderDeleteBtn,
               "defaultContent": "",
               "orderable": false
           }
       ],
       "order": [
           [
               0,
               "desc"
           ]
       ],
       "createdRow": function (row, data, dataIndex) {
           $(row).attr("data-orderExcess", data.excess);
       },
   }, updateFilteredTable);

    $.datetimepicker.setLocale(localeCode);

    var startDate = $('#startDate');
    var endDate = $('#endDate');
    startDate.datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        formatDate: 'Y-m-d',
        onShow: function (ct) {
            this.setOptions({
                maxDate: endDate.val() ? endDate.val() : false
            })
        }
    });
    endDate.datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        formatDate: 'Y-m-d',
        onShow: function (ct) {
            this.setOptions({
                minDate: startDate.val() ? startDate.val() : false
            })
        }
    });

    var startTime = $('#startTime');
    var endTime = $('#endTime');
    startTime.datetimepicker({
        datepicker: false,
        format: 'H:i',
        onShow: function (ct) {
            this.setOptions({
                maxTime: endTime.val() ? endTime.val() : false
            })
        }
    });
    endTime.datetimepicker({
        datepicker: false,
        format: 'H:i',
        onShow: function (ct) {
            this.setOptions({
                minTime: startTime.val() ? startTime.val() : false
            })
        }
    });

    $('#dateTime').datetimepicker({
        format: 'Y-m-d H:i'
    });
});