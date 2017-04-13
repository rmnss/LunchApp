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
    
    
    
  /*--------------------------------------------
    
            Saving a new menu row
    
    ----------------------------------------------*/
    
    $(document).on('click','.save-row-menu-new',function(){

        $id = this.id;
         alert($id);
            
         
      
var tr = document.getElementById($id);
var td = tr.getElementsByTagName("td");
         
         
        //var idmenu = td[0].innerHTML
        
        alert(td[1]);
        var kategori = td[1].innerHTML
        var merke = td[2].innerHTML
        var type = td[3].innerHTML
        var studentPris = td[4].innerHTML
        var ansattPris = td[5].innerHTML
        
        
       
/*
        
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
            */
           
     
               
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
    

    
    
/*=========================================================

                    delete table row
                        
=========================================================*/
    
    
    $(document).on('click','.delete-row',function(){
        
        
        $id = this.id;
        
        
        //sletter tr taggen. går i hierarki  td -> tr
        var elementDelete = $(this).parent().parent();
        elementDelete.remove();
       
    
           
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
    
    
    
    /*----------------------
    
    Deleting today's special
    
    ------------------------*/
    
     $(document).on('click','.delete-row-special',function(){
        
        
        $id = this.id;
    
           
            $.ajax({
                type: 'POST',
                url: 'DB_API_BACKEND.php',
                data: {action: 'delRowSpecial', id: $id},
                success: function(data) {
                    
                    alert($id);
                    
                   
                },
                error: function(xhr, desc, err) {
                    console.log(xhr);
                    console.log("Details: " + desc + "\nError:" + err);
                }
            });
            
               
    });
    


    
/*==================================================================================================================

change position of table rows not used here, but saved for later project
                        
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

                    exporting table as JSON
                        
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
