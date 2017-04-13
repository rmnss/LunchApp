<!DOCTYPE html>
<html lang="no">
    
    <head>
        <title>Kantine App</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel= "stylesheet" type="text/css" href="css/style.css" />
        <link rel="icon" href="bilder/coffeecup.png">
        <meta charset="utf-8" />
    </head>
    
    <body>
        <div class="container">
            <?php include 'nav.php'; ?>   
            <h1>Meny</h1>  
            
            <?php
            
            include 'todaysspecial.php';
            
            include 'footer.php'; 
            ?>

            
            
            
        </div>
    </body>
</html>  