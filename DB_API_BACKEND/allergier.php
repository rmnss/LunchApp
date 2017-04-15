<!DOCTYPE html>
<html lang="no">
    
    
    <head>
        <title>Kantine App</title>
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
        <a class="nav-link" href="todaysspecialList.php">Todays Special</a></li>
        <li class="nav nav-item">
        <a style = "color: #fff; background-color:#ff9d00;" class="nav nav-link active"  href="allergier.php">Allergier</a></li>
            <li class="nav nav-item">
        <a class="nav nav-link"  href="bildeInput.php">Last opp bilde</a></li>
    </ul>
            <h1>Allergier</h1>  
            
            
    <div class="btn-group" role="group" aria-label="Button group with nested dropdown">  

    
            <?php
            include 'allergiesTable.php';
            include 'footer.php'; 
            ?>

     </div>           
            
            
        </div>
    </body>
</html>  