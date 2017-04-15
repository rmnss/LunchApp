<!DOCTYPE html>

<html lang="no">
    
    
    
    
    <head>
        <title>Kantine App</title>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="style.css">
        <link rel="icon" href="bilder/coffeecup.png">
        
        <meta charset="utf-8" />
        
    </head>
    
    <body>
       
            
            
    <ul class="nav nav-tabs nav-justified ">
        <li class="nav nav-item">
        <a class="nav nav-link" href="default.php">Meny</a></li>
        <li class="nav nav-item">
        <a class="nav nav-link" href="todaysspecialList.php">Todays Special</a></li>
        <li class="nav nav-item">
        <a class="nav nav-link"  href="allergier.php">Allergier</a></li>
        <li class="nav nav-item">
        <a style = "color: #fff; background-color:#ff9d00;" class="nav nav-link active" href="bildeInput.php">Last opp bilde</a></li>
    </ul>
            
            
        
        
            
           <div class="container">  
               
               
        
                   
	<form action="bildeopplastning.php" method="post" enctype="multipart/form-data">
		<input id = "filbaneid" type="file" name="filbane" />
		<input id = "knapp" class = "last-opp" type="submit" value="last opp fil" name="last_opp" />
	</form>
        
               
               
            
        
        </div>
        
        
    </body>
    <script src="editableTable.js"></script>
</html>