$(function (){

    /*=========================================================

                    Populerer listen "liste"
                        
=========================================================*/
    
    //Sender forespørsel om data til API'et.
    //Hvis den blir success kalles funksjonen fillTable som populerer den faktiske tabellen
   $.ajax({
       type: 'POST',
       url: 'DB_API_BACKEND.php',
       data: { 'action' : 'getAllergies' },
       success: function(data) {
           //console.log('success', data); //Enable denne for debuging
           fillTable(data);
       },
       error: function(xhr, desc, err) {
            console.log(xhr);
            console.log("Details: " + desc + "\nError:" + err);
           alert("feil i api");
        }
   });
    
        
    
    //fading effekter på tabellen.
    // $("table").animate({height: 800}, 1000);
      //  $("table").animate({width: 1100}, 1150);
        

    
    //Populerer tabellen
    //Blir kalt opp fra ajax funksjonen sin 'success'
    //data: funksjonen mottar attributtet data fra ajax. Her ligger svaret på spørringen
    function fillTable(data){
        var object = $.parseJSON(data);
        $.each(object, function(index, value){

           
            //#Liste: ID på tabellen som skal fylles
            //id og Navn er kolonnenavn fra databasen
           $("#listAllergies").append(
            '<tr  id="' + object[index]["idDrmeny"] + '" class="rad">' +
                '<td>' + object[index]["navn"] + '</td>' +
                '<td>' + object[index]["allergi"] + '</td>' +  
                '<td>' + object[index]["dag"] + '</td>' +  
   
    '<td>' +
               '<span id ="'+ object[index]["idAlergier"] +'" class="table-remove glyphicon glyphicon-remove delete-row-allergies">'+
               '</span>' +
    '</td>' +
               
    
    '<td>' +
               '<span id = "popsave-'+ object[index]["idAlergier"] +'" style = "display:none;background-color:#f1f1f1; color:green;" class="table-save glyphicon glyphicon-ok-sign">'+
               '</span>' +
    '</td>' +
               
            '</tr>');
        });
    }    
    

    
    
});//jQuery slutt
