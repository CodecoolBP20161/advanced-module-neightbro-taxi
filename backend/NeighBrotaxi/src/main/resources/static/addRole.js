/**
 * Created by david on 2/27/17.
 */

$(function(){
    $('#addNewRole').on('submit', function(e){
        e.preventDefault();
        $.ajax({
            url: "/add-role", //this is the submit URL
            type: 'POST',
            data: $('#addNewRole').serialize(),
            success: function(data){
                alert('successfully submitted')
            }
        });
    });
});