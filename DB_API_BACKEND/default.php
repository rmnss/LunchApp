<!DOCTYPE html>
<html lang="no">
    
    <head>
        <title>Kantine App</title>
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
        </ul>
            
              
            <h1>Meny</h1>  
            
            <?php
            
            include 'backend.php';
            
            include 'footer.php'; 
            ?>

            
            
            
        </div>
    </body>
</html>  