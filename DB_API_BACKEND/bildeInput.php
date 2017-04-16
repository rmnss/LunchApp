<!DOCTYPE html>

<html lang="no">
    <head>
        <title>Kantine App</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="style.css">
        <link rel="icon" href="bilder/coffeecup.png">
        <meta charset="utf-8"/>
    </head>
    <body>
        <div class = "container">

            <!--NAV-->
            <ul class="nav nav-tabs nav-justified ">
                <li class="nav nav-item">
                <a class="nav nav-link" href="default.php">Meny</a></li>
                <li class="nav nav-item">
                <a class="nav nav-link" href="todaysspecialList.php">Todays Special</a></li>
                <li class="nav nav-item">
                <a class="nav nav-link"  href="allergier.php">Allergier</a></li>
                <li class="nav nav-item">
                <a style = "color: #fff; background-color:#ff9d00;" class="nav nav-link active" href="bildeInput.php">Last opp bilde</a></li>
                   <li class="nav nav-item">
        <a class="nav nav-link"  href="qr.php">Endre QR/Passord</a></li> 
                 <li class="nav nav-item">
        <a class="nav nav-link" href="openingHoursList.php">Åpningstider</a></li>
            </ul>

            <!--Tittel og informativ tekst-->
            <h1>Last opp bilde</h1>
            <ul>
                <li>Last opp bildet du ønsker å representere "Dagens Rett"</li>
                <li>Upload the image you want to represent "Today's Spesial"</li> 
            </ul>

            <!--laster og sender bilde til PHP-->
            <div class="container">
                <form action="bildeopplastning.php" method="post" enctype="multipart/form-data">
                    <div class="col-lg-6 col-sm-6 col-12">
                        <div class="input-group">
                            <label class="input-group-btn">
                                <span class="btn btn-primary knapp">
                                    Velg fil&hellip; <input id = "filbaneid" type="file" name="filbane" style="display: none;" multiple/>  <!--Får ikke printet ut tekst ved siden av browse knappen-->
                                </span>
                            </label>
                            <input type="text" class="form-control" readonly>
                        </div>
                        <input id = "knapp" class = "last-opp btn btn-primary btn-lg btn-block knapp" type="submit" value="last opp fil" name="last_opp" />
                    </div>
            	</form>
            </div>
                 <?php
            
            include 'footer.php'; 
            ?>
        </div>
    </body>
    <script src="editableTable.js"></script>
</html>