<!DOCTYPE html>
<html lang="no">
    
    <head>
        <title>Kantine App</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>    
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="style.css">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="icon" href="bilder/coffeecup.png">
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta charset="utf-8" />
    </head>
    
    <body>
        <div class="container">
            
          
            
        <ul class="nav nav-tabs nav-justified ">
        <li class="nav nav-item">
        <a style = "color: #fff; background-color:#ff9d00;" class="nav nav-link active" href="default.php">Meny</a></li>
        <li class="nav nav nav-item">
        <a class="nav nav-link" href="todaysspecialList.php">Todays Special</a></li>
        <li class="nav nav-item">
        <a class="nav nav-link" href="allergier.php">Allergier</a></li>
        <li class="nav nav-item">
        <a class="nav nav-link"  href="bildeInput.php">Last opp bilde</a></li>
        <li class="nav nav-item">
        <a class="nav nav-link"  href="qr.php">Endre QR/Passord</a></li> 
             <li class="nav nav-item">
        <a class="nav nav-link" href="openingHoursList.php">Åpningstider</a></li>
        </ul>
            
              
            <h1>Meny</h1> 
            
              <ul>
    <li>Trykk på cellene for å redigere, enkelte celler vil ikke være mulig å redigere grunnet sikkerhet</li>
    <li>Click on a cell to edit, some cells may not be changed for security reasons</li> 
  </ul>
  
    

    
    
  <div id="table" class="table-editable">
    <span class="table-add glyphicon glyphicon-plus"></span>
    <table class="table table-hover table-responsive " id ="listeMenu">
        <thead>
      <tr>
        <th>#</th>
        <th>Kategori</th>
        <th>Merke</th>
          <th>Type</th>
          <th>Studentpris</th>
          <th>Ansattpris</th>
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
    
            
            <?php
            
          
            
            include 'footer.php'; 
            ?>

            
            
            
        </div>
    </body>
    <script src="editableTable.js"></script>
<script src="fillMenu.js"></script>

</html>  