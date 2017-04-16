$(function (){
    
    

    
    
var $TABLE = $('#table');
var $BTN = $('#export-btn');
var $EXPORT = $('#export');
    

    
/*=========================================================

                    Save edited row
                        
=========================================================*/
    
     $(document).on('click','.save-row',function(){

        $id = this.id;
             
         
         
var tr = document.getElementById($id);
var td = tr.getElementsByTagName("td");
         
         
        var idmenu = td[0].innerHTML
        var navn = td[1].innerHTML
        var serveringstid = td[2].innerHTML
        var studentpris = td[3].innerHTML
        var ansattpris = td[4].innerHTML
        var dag = td[5].innerHTML
        
       
        

    
      // gir respons når raden trykket på
    $("#" + $id).fadeOut("slow");
    $("#" + $id).fadeIn("slow");
         
          $("#popsave-" +$id).fadeIn(20);
          $("#popsave-" +$id).fadeOut(4000);
         
         
         
         
        
           
            $.ajax({
                type: 'POST',
                url: 'DB_API_BACKEND.php',
                data: {action: 'saveRow', idmenu, navn, serveringstid, studentpris, ansattpris, dag},
             
                
                success: function(data) {
                    
                

                  
                    
                    
                   
                },
                error: function(xhr, desc, err) {
                    console.log(xhr);
                    console.log("Details: " + desc + "\nError:" + err);
                }
            });
           
     
               
    });
    
    
    
    /*=========================================================

                    Save edited opening hours
                        
=========================================================*/
    
     $(document).on('click','.save-row-hours',function(){

        $id = this.id;
             
         
         
var tr = document.getElementById($id);
var td = tr.getElementsByTagName("td");
         
         
        var idÅpningstider = td[0].innerHTML
        var openingHours = td[1].innerHTML
        var day = td[2].innerHTML
       
        
 
        

    
      // gir respons når raden trykket på
    $("#" + $id).fadeOut("slow");
    $("#" + $id).fadeIn("slow");
         
          $("#popsave-" +$id).fadeIn(20);
          $("#popsave-" +$id).fadeOut(4000);
         
         
         
         
        
        
            $.ajax({
                type: 'POST',
                url: 'DB_API_BACKEND.php',
                data: {action: 'saveRowHours', idÅpningstider, openingHours, day},
             
                
                success: function(data) {
                    
                

                  
                    
                    
                   
                },
                error: function(xhr, desc, err) {
                    console.log(xhr);
                    console.log("Details: " + desc + "\nError:" + err);
                }
            });
            
           
     
               
    });
    
    
    /*--------------------------------------------
    
                        Saving Allergies
    
    ----------------------------------------------*/
    
     $(document).on('click','.save-row-allergies',function(){

        $id = this.id;
         
   
             
         
         
var tr = document.getElementById($id);
var td = tr.getElementsByTagName("td");
         
         
        var idmenu = td[0].innerHTML
        var navn = td[1].innerHTML
        var serveringstid = td[2].innerHTML
        var studentpris = td[3].innerHTML
        var ansattpris = td[4].innerHTML
        var dag = td[5].innerHTML
        
       
        

    
      // gir respons når raden trykket på
    $("#" + $id).fadeOut("slow");
    $("#" + $id).fadeIn("slow");
         
          $("#popsave-" +$id).fadeIn(20);
          $("#popsave-" +$id).fadeOut(4000);
         
         
         
         
        /*
           
            $.ajax({
                type: 'POST',
                url: 'DB_API_BACKEND.php',
                data: {action: 'saveRow', idmenu, navn, serveringstid, studentpris, ansattpris, dag},
             
                
                success: function(data) {
                    
                

                  
                    
                    
                   
                },
                error: function(xhr, desc, err) {
                    console.log(xhr);
                    console.log("Details: " + desc + "\nError:" + err);
                }
            });
           
     */
               
    });
    
    
    
     /*--------------------------------------------
    
                        Saving Menu
    
    ----------------------------------------------*/
    
    
    
      
     $(document).on('click','.save-row-menu',function(){

        $id = this.id;
           
         
      
var tr = document.getElementById($id);
var td = tr.getElementsByTagName("td");
         
         
        var idmenu = td[0].innerHTML
        var kategori = td[1].innerHTML
        var merke = td[2].innerHTML
        var type = td[3].innerHTML
        var studentPris = td[4].innerHTML
        var ansattPris = td[5].innerHTML
        
        
         
// gir respons når raden trykket på
    $("#" + $id).fadeOut("slow");
    $("#" + $id).fadeIn("slow");
         
          $("#popsave-" +$id).fadeIn(20);
          $("#popsave-" +$id).fadeOut(4000);
        
       
         
         
         
         
         

        
            $.ajax({
                type: 'POST',
                url: 'DB_API_BACKEND.php',
                data: {action: 'saveRowMenu', idmenu, kategori, merke, type, studentPris, ansattPris},
             
                
                success: function(data) {
                    

                    
                   
                },
                error: function(xhr, desc, err) {
                    console.log(xhr);
                    console.log("Details: " + desc + "\nError:" + err);
                }
            });
           
     
               
    });
    
    
    
  /*----------------------------------------------------------------------------------------
    
            Saving  new password for selling coffee card
    
    ------------------------------------------------------------------------------------------*/
    
    $(document).on('click','.btn-save-Password',function(){
 
        
        
        var password = document.getElementById('pw').value;
     
            
         
      

        
        
       
        
            $.ajax({
                type: 'POST',
                url: 'DB_API_BACKEND.php',
                data: {action: 'savePasswordQr', password},
             
                
                success: function(data) {
                    

                    
                   
                },
                error: function(xhr, desc, err) {
                    console.log(xhr);
                    console.log("Details: " + desc + "\nError:" + err);
                }
            });
        
           
     
               
    });
    
    
    
    
    
     /*----------------------------------------------------------------------------------------
    
            Saving new QR-string selling coffee card
    
    ------------------------------------------------------------------------------------------*/
    
    $(document).on('click','.btn-save-SellString',function(){
 
        
        
        var SellString = document.getElementById('coffeecard').value;
     
            

        
            
        
       
        
            $.ajax({
                type: 'POST',
                url: 'DB_API_BACKEND.php',
                data: {action: 'saveSellStringQr', SellString},
             
                
                success: function(data) {
                    

                    
                   
                },
                error: function(xhr, desc, err) {
                    console.log(xhr);
                    console.log("Details: " + desc + "\nError:" + err);
                }
            });
        
           
     
               
    });
    
    
    
      /*----------------------------------------------------------------------------------------
    
            Saving new QR-string buy coffee card
    
    ------------------------------------------------------------------------------------------*/
    
    $(document).on('click','.btn-save-BuyString',function(){
 
        
        
        var BuyString = document.getElementById('buycoffee').value;
     
            

        
            
        
       
        
            $.ajax({
                type: 'POST',
                url: 'DB_API_BACKEND.php',
                data: {action: 'saveBuyStringQr', BuyString},
             
                
                success: function(data) {
                    

                    
                   
                },
                error: function(xhr, desc, err) {
                    console.log(xhr);
                    console.log("Details: " + desc + "\nError:" + err);
                }
            });
        
           
     
               
    });
    
    
    
    

    
    
    

    
/*=========================================================

                    add new table row
                        
=========================================================*/
        

$('.table-add').click(function () {
    
    
//Kloner TD fra klassen "Hide".    
  var $clone = $TABLE.find('tr.hide').clone(true).removeClass('hide table-line');
  $TABLE.find('table').append($clone);
    

    
    
      
      //legger til statiske kategorier for å kunne bruke innerHTML funksjonen i JS.
        
        var kategori = "kategori";
        var merke = "merke";
        var type = "type";
        var studentPris = "1";
        var ansattPris = "1";
    

       

        
            $.ajax({
                type: 'POST',
                url: 'DB_API_BACKEND.php',
                data: {action: 'saveRowMenuCreateRow', kategori, merke, type, studentPris, ansattPris},
             
                
                success: function(data) {
                    
                        window.location.reload();
                    
                   
                },
                error: function(xhr, desc, err) {
                    console.log(xhr);
                    console.log("Details: " + desc + "\nError:" + err);
                }
            });
            
    
    
    
});
    
    
     /*--------------------------------------------
    
            Add alergies
    
    ----------------------------------------------*/
    
    
    
        
    $(document).on('click','.save-btn',function(){
        
        
        //$id = this.id;
        
        
        
        
        $dish = $('#dish :selected').text();
        $allergies = $('#allergies :selected').text();
        
        
         //$id = $(this).children(":selected").attr("id");
        
        //ar id = $(this).find('option:selected').attr('id');
        
        
        var allergier_idAlergier = $('#allergies option:selected').attr('id');
        
        var drmeny_idDRmeny = $('#dish option:selected').attr('id');
        
        
    
                        window.location.reload();
    
           
            $.ajax({
                type: 'POST',
                url: 'DB_API_BACKEND.php',
                data: {action: 'addAllergies',  allergier_idAlergier, drmeny_idDRmeny},
                success: function(data) {
                    
                   
                    
                   
                },
                error: function(xhr, desc, err) {
                    console.log(xhr);
                    console.log("Details: " + desc + "\nError:" + err);
                }
            });
            
            
               
    });
    
    
    
    
       /*--------------------------------------------
    
            Add picture
    
    ----------------------------------------------*/
    
    
    
        
    $(document).on('click','.savepicture-btn',function(){
        
        
        $id = this.id;
        
       

        
        var drmeny_idDRmeny = $('#dish option:selected').attr('id');
         
        
             window.history.back();
         
        
    
                       

           
            $.ajax({
                type: 'POST',
                url: 'DB_API_BACKEND.php',
                data: {action: 'addPicture',  id: $id, drmeny_idDRmeny},
                success: function(data) {
                    
                   
                    
                   
                },
                error: function(xhr, desc, err) {
                    console.log(xhr);
                    console.log("Details: " + desc + "\nError:" + err);
                }
            });
            
            
            
               
    });
    

    
    
/*=========================================================

                    delete table row
                        
=========================================================*/
    
    
    $(document).on('click','.delete-row',function(){
        
        
        $id = this.id;
        
        
        //sletter tr taggen. går i hierarki  td -> tr
        var elementDelete = $(this).parent().parent();
        elementDelete.fadeOut(1000);
      
    
           
            $.ajax({
                type: 'POST',
                url: 'DB_API_BACKEND.php',
                data: {action: 'delRow', id: $id},
                success: function(data) {
                    
                   
                    
                   
                },
                error: function(xhr, desc, err) {
                    console.log(xhr);
                    console.log("Details: " + desc + "\nError:" + err);
                }
            });
            
               
    });
    
    
    
    
    
    
    
    
    /*--------------------------------------------------------------------------------------------------------------
    
    Deleting today's special (NOT IN USE, due to the fact the app only supports one week intervall.
    
    ----------------------------------------------------------------------------------------------------------------*/
    
     $(document).on('click','.delete-row-special',function(){
        
        
        $id = this.id;
    
           
            $.ajax({
                type: 'POST',
                url: 'DB_API_BACKEND.php',
                data: {action: 'delRowSpecial', id: $id},
                success: function(data) {
                    

                    
                    
                   
                },
                error: function(xhr, desc, err) {
                    console.log(xhr);
                    console.log("Details: " + desc + "\nError:" + err);
                }
            });
            
               
    });
    
    
    
/*--------------------------------------------------------------------------------------------------------------
    
    Deleting allergies ( have a relation with todays special.)
    
----------------------------------------------------------------------------------------------------------------*/
     $(document).on('click','.delete-row-allergies',function(){
        
        
        var idAlergier = this.id;
        
        
        //sletter tr taggen. går i hierarki  td -> tr
        var elementDelete = $(this).parent().parent();
         
        //gir respons til brukeren 
        elementDelete.fadeOut(1000);
      
         

         
         
         
         
         
         var idDrmeny = $(this).closest('tr').attr('id');
     
         
           
         
         
         
         
         
    
         
            $.ajax({
                type: 'POST',
                url: 'DB_API_BACKEND.php',
                data: {action: 'delAllergy', idAlergier, idDrmeny},
                success: function(data) {
                    
                   
                    
                   
                },
                error: function(xhr, desc, err) {
                    console.log(xhr);
                    console.log("Details: " + desc + "\nError:" + err);
                }
            });
            
            
            
               
    });
    
    
    

    
    

    
/*==================================================================================================================

change position of table rows not used here, but saved for later project (NOT IN USE, need this for another project)
                        
==================================================================================================================*/    

$('.table-up').click(function () {
  var $row = $(this).parents('tr');
  if ($row.index() === 1) return; // Don't go above the header
  $row.prev().before($row.get(0));
});

$('.table-down').click(function () {
  var $row = $(this).parents('tr');
  $row.next().after($row.get(0));
});

/*==================================================================================================================

                    exporting table as JSON (NOT IN USE, need this for another project)
                        
==================================================================================================================*/
jQuery.fn.pop = [].pop;
jQuery.fn.shift = [].shift;
 
    
$BTN.click(function () {
  var $rows = $TABLE.find('tr:not(:hidden)');
  var headers = [];
  var data = [];
  
  // Get the headers (add special header logic here)
  $($rows.shift()).find('th:not(:empty)').each(function () {
    headers.push($(this).text().toLowerCase());
  });
  
  // Turn all existing rows into a loopable array
  $rows.each(function () {
    var $td = $(this).find('td');
    var h = {};
    
    // Use the headers from earlier to name our hash keys
    headers.forEach(function (header, i) {
      h[header] = $td.eq(i).text();   
    });
    
    data.push(h);
  });
  
  // Output the result
  $EXPORT.text(JSON.stringify(data));
}); 
    
    
});//jQuery slutt
