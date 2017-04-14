

<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    
    
    
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style.css">


    
</head>
    
<body>
    
    

    
    <div class ="content">
  <div id="table" class="table-editable">
      
    <table class="table table-hover table-responsive " id ="listAllergies">
        <thead>
      <tr>
        <th>navn</th>
        <th>allergi</th>
        <th>dag</th>
      </tr>
        </thead>
        <tbody id="listeBody">
      
      <!-- This is our clonable table line -->
      <tr class="hide">
        <td> new </td>
        <td contenteditable="true">kategori</td>
          <td contenteditable="true">merke</td>
        <td contenteditable="true">type</td>
          <td contenteditable="true">type</td>
        <td contenteditable="true">ansattPris</td>
        <td>
          <span class="table-remove glyphicon glyphicon-remove-allergies"></span>
        </td>
          <td>
               <span id = 101010 class= "table-save glyphicon glyphicon-floppy-disk save-row-menu-new">
               </span>
    </td>
      </tr>
        </tbody>
    </table>
  
  <?php
include "dropdown.php";     
?>
      
      
      
      
      
<button type="button" class="btn btn-primary save-btn">Lagre</button>
      
      
      

</div>
 </div>   

    


    
    
    

    
    
    
</body>
    
    
<script src="editableTable.js"></script>
<script src="fillAllergies.JS"></script>

    
    
    
</html>