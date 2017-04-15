

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
    <table class="table table-hover table-responsive " id ="listeMenu">
        <thead>
      <tr>
        <th>idMenu</th>
        <th>kategori</th>
        <th>merke</th>
          <th>type</th>
          <th>studentPris</th>
          <th>ansattPris</th>
        <th></th>
        <th></th>
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
          <span class="table-remove glyphicon glyphicon-remove"></span>
        </td>
          <td>
               <span id = 101010 class= "table-save glyphicon glyphicon-floppy-disk save-row-menu-new">
               </span>
    </td>
      </tr>
        </tbody>
    </table>
  
  

</div>
    

    


    
    
    

    
    
    
</body>
    
    
<script src="editableTable.js"></script>
<script src="fillMenu.js"></script>

    
    
    
</html>