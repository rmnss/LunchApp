<!DOCTYPE html>
<html lang="no">
    
    
    <head>
        <title>Kantine App</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="style.css">
        <link rel="icon" href="bilder/coffeecup.png">
        
        <meta charset="utf-8" />
        
    </head>
    
    <body>
        <div class="container">
            
            
        <ul class="nav nav-tabs nav-justified ">
        <li class="nav nav-item">
        <a class="nav nav-link" href="default.php">Meny</a></li>
        <li class="nav nav-item">
        <a class="nav nav-link" href="todaysspecialList.php">Todays Special</a></li>
        <li class="nav nav-item">
        <a class="nav-link" href="allergier.php">Allergier</a></li>
            <li class="nav nav-item">
        <a class="nav nav-link"  href="bildeInput.php">Last opp bilde</a></li>
             <li class="nav nav-item">
        <a class="nav nav-link"  href="qr.php">Endre QR/Passord</a></li> 
        <li class="nav nav-item">
        <a style = "color: #fff; background-color:#ff9d00;" class="nav nav-link active" href="openingHourslist.php">Åpningstider</a></li>
            
            
    </ul>
            <h1>Åpningstider</h1>  
            


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
        <th>#</th>
        <th>Åpningstider</th>
        <th>Dag</th> 
      </tr>
        </thead>
        <tbody id="listeBody">
      
     
        </tbody>
    </table>
  </div>
  

</div>
    


    
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            <?php
            
            
            include 'footer.php'; 
            
            
            ?>

            
            
            
        </div>
    </body>
        
<script src="fillHours.js"></script>
<script src="editableTable.js"></script>

    
    
    
</html>  