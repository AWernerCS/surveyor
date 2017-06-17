$(document).ready(function() {
    var max_fields      = 9;
    var wrapper         = $(".container1");
    var add_button      = $(".add_form_field");

    var x = 1;
    $(add_button).click(function(e){
        e.preventDefault();
        if(x < max_fields){
            x++;
            $(wrapper).append('<div><input type="text" name="myTest[]" /><a href="#" class="delete">Delete</a></div>'); //add input box
        }
  else
  {
  alert('You cannot add more than 10 options.')
  }
    });

    $(wrapper).on("click",".delete", function(e){
        e.preventDefault(); $(this).parent('div').remove(); x--;
    })
});