$(function (){

    /*=========================================================

                    Populerer listen "liste"
                        
=========================================================*/
    
    //Sender forespørsel om data til API'et.
    //Hvis den blir success kalles funksjonen fillTable som populerer den faktiske tabellen
   $.ajax({
       type: 'POST',
       url: 'DB_API_BACKEND.php',
       data: { 'action' : 'getToday' },
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
            '<tr  id="' + object[index]["idDRmeny"] + '" class="rad">' +
                '<td id = "id">' + object[index]["idDRmeny"] + '</td>' +
                '<td contenteditable="true" id = "navn">' + object[index]["navn"] + '</td>' +  
                '<td contenteditable="true" id = "serveringstid">' + object[index]["serveringstid"] + '</td>' +  
                '<td contenteditable="true" id = "studentPris">' + object[index]["studentPris"] + '</td>' +  
                '<td contenteditable="true" id = "ansattPris">' + object[index]["ansattPris"] + '</td>' +  
                '<td id = "dag">' + object[index]["dag"] + '</td>' +  
     '<td>' +
               '<span id ="'+ object[index]["idDRmeny"] +'" class="table-save glyphicon glyphicon-floppy-disk save-row">'+
               '</span>' +
    '</td>' +
               
            '</tr>');
        });
    }    
    

    
    
});//jQuery slutt
