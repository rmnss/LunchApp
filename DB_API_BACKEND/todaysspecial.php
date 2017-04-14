

<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">



    
</head>
    
<body>

<div class="container">
 
  
  <ul>
    <li>Trykk på cellene for å redigere, enkelte celler vil ikke være mulig å redigere grunnet sikkerhet</li> 
    <li>Click on a cell to edit, some cells may not be changed for security reasons</li>
  </ul>
  

    
    
    
  <div id="table" class="table-editable">
      
       <div id="myAlert" class="alert success fade" data-alert="alert">This is the alert.</div>
    <table class="table table-hover table-responsive " id ="liste">
        <thead>
      <tr>
        <th>idDRmeny</th>
        <th>navn</th>
        <th>serveringstid</th>
          <th>studentPris</th>
          <th>ansattPris</th>
          <th>dag</th>
        <th></th>
        <th></th>
      </tr>
        </thead>
        <tbody id="listeBody">
      
      <!-- This is our clonable table line -->
      <tr class="hide">
        <td contenteditable="true">idMenu</td>
        <td contenteditable="true">kategori</td>
          <td contenteditable="true">merke</td>
        <td contenteditable="true">type</td>
          <td contenteditable="true">type</td>
        <td contenteditable="true">ansattPris</td>
        <td>
          <span class="table-remove glyphicon glyphicon-remove"></span>
        </td>
      </tr>
        </tbody>
    </table>
  </div>
  
  <button id="export-btn" class="btn btn-primary">Export Data</button>
  <p id="export"></p>
</div>
    

    



    
    
    

    
    
    
</body>
    
    
<script src="editableTable.js"></script>
<script src="fillSpecial.JS"></script>

    
    
    
</html>