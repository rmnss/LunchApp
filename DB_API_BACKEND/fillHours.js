$(function (){

    /*=========================================================

                    Populerer listen "liste"
                        
=========================================================*/
    
    //Sender forespørsel om data til API'et.
    //Hvis den blir success kalles funksjonen fillTable som populerer den faktiske tabellen
   $.ajax({
       type: 'POST',
       url: 'DB_API_BACKEND.php',
       data: { 'action' : 'getHours' },
       success: function(data) {
           //console.log('success', data); //Enable denne for debuging
           fillTable(data);
       },
       error: function(xhr, desc, err) {
            console.log(xhr);
            console.log("Details: " + desc + "\nError:" + err);
        }
   });
    
    
    
    
    
    //Populerer tabellen
    //Blir kalt opp fra ajax funksjonen sin 'success'
    //data: funksjonen mottar attributtet data fra ajax. Her ligger svaret på spørringen
    function fillTable(data){
        var object = $.parseJSON(data);
        $.each(object, function(index, value){

           
            //#Liste: ID på tabellen som skal fylles
            //id og Navn er kolonnenavn fra databasen
           $("#liste").append(
            '<tr  id="' + object[index]["idÅpningstider"] + '" class="rad">' +
                '<td id = "id">' + object[index]["idÅpningstider"] + '</td>' +
                '<td contenteditable="true" id = "openingHours">' + object[index]["openingHours"] + '</td>' +  
                '<td id = "day">' + object[index]["day"] + '</td>' +  
                
     '<td>' +
               '<span id ="'+ object[index]["idÅpningstider"] +'" class="table-save glyphicon glyphicon-floppy-disk save-row-hours">'+
               '</span>' +
    '</td>' +
     
     '<td>' +
               '<span id = "popsave-'+ object[index]["idÅpningstider"] +'" style = "display:none;background-color:#f1f1f1; color:green;" class="table-save glyphicon glyphicon-ok-sign">'+
               '</span>' +
    '</td>' +
                          
               
            '</tr>');
        });
    }    
    

    
    
});//jQuery slutt
