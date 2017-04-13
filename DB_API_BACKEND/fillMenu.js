$(function (){

    /*=========================================================

                    Populerer listen "liste"
                        
=========================================================*/
    
    //Sender forespørsel om data til API'et.
    //Hvis den blir success kalles funksjonen fillTable som populerer den faktiske tabellen
   $.ajax({
       type: 'POST',
       url: 'DB_API_BACKEND.php',
       data: { 'action' : 'getMenu' },
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
           $("#listeMenu").append(
            '<tr  id="' + object[index]["idMenu"] + '" class="rad">' +
                '<td>' + object[index]["idMenu"] + '</td>' +
                '<td contenteditable="true">' + object[index]["kategori"] + '</td>' +  
                '<td contenteditable="true">' + object[index]["merke"] + '</td>' +  
                '<td contenteditable="true">' + object[index]["type"] + '</td>' +  
                '<td contenteditable="true">' + object[index]["studentPris"] + '</td>' +  
                '<td contenteditable="true">' + object[index]["ansattPris"] + '</td>' +  
    '<td>' +
               '<span id ="'+ object[index]["idMenu"] +'" class="table-remove glyphicon glyphicon-remove delete-row">'+
               '</span>' +
    '</td>' +
               
    '<td>' +
               '<span id ="'+ object[index]["idMenu"] +'" class="table-save glyphicon glyphicon glyphicon-floppy-disk save-row-menu">'+
               '</span>' +
    '</td>' +
               
            '</tr>');
        });
    }    
    

    
    
});//jQuery slutt
