

<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">

    
</head>
    
<body>

<div class="container">
 
  
  <ul>
    <li>An editable table that exports a hash array. Dynamically compiles rows from headers</li> 
    <li>Simple / powerful features such as add row, remove row, move row up/down.</li>
  </ul>
  

    
    
    
  <div id="table" class="table-editable">
    <table class="table table-hover table-responsive">
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
        <tbody id="liste">
      
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