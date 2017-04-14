

<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="style.css">


    
</head>
    
<body>
    

 
  
  <ul>
    <li>Trykk på cellene for å redigere, enkelte celler vil ikke være mulig å redigere grunnet sikkerhet</li>
    <li>Click on a cell to edit, some cells may not be changed for security reasons</li> 
  </ul>
  
    

    
    
  <div id="table" class="table-editable">
    <span class="table-add glyphicon glyphicon-plus"></span>
    <table class="table table-hover table-responsive " id ="listAllergies">
        <thead>
      <tr>
        <th>navn</th>
        <th>allergi</th>
        <th>dag</th>
          <th>idDrmeny</th>
          <th>allergier_idAlergier</th>
          <th>allergier_idAlergier</th>
        <th>drmeny_idDRmeny</th>
        <th>idAlergier</th>
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
  
  
  <button id="export-btn" class="btn btn-primary">Export Data</button>
  <p id="export"></p>
</div>
    

    


    
    
    

    
    
    
</body>
    
    
<script src="editableTable.js"></script>
<script src="fillAllergies.JS"></script>

    
    
    
</html>